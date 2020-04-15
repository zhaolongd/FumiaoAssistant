package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.InComingDetailBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.home.MicroMerchantInfoView;
import com.fumiao.assistant.mvp.home.MicroMerchantInfoPresenter;
import com.fumiao.assistant.tools.DBManager;
import com.fumiao.assistant.tools.SelectAddrModule;
import com.fumiao.assistant.ui.dialog.RejectDialog;
import com.fumiao.core.uitls.Callback;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.core.uitls.EmojiFilter;
import com.fumiao.core.uitls.ImgUtils;
import com.fumiao.core.uitls.SelectImageUtils;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.core.widget.KeyEditText;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhaolong on 2019/9/16.
 * 小微店铺信息填写和编辑
 */
public class MicroMerchantInfoActivity extends MvpActivity<MicroMerchantInfoPresenter> implements MicroMerchantInfoView {

    @BindView(R.id.store_name)
    KeyEditText storeName;
    @BindView(R.id.store_type)
    ItemArrowLayout storeType;
    @BindView(R.id.addr)
    ItemArrowLayout addr;
    @BindView(R.id.addr_detail)
    KeyEditText addrDetail;
//    @BindView(R.id.wechat_rate)
//    KeyEditText wechatRate;
//    @BindView(R.id.alipay_rate)
//    KeyEditText alipayRate;
//    @BindView(R.id.unionpay_rate)
//    KeyEditText unionpayRate;
//    @BindView(R.id.large_unionpay_rate)
//    KeyEditText largeUnionpayRate;
    @BindView(R.id.iv_store_head)
    ImageView ivStoreHead;
    @BindView(R.id.iv_store_cashier)
    ImageView ivStoreCashier;
    @BindView(R.id.iv_store_inside)
    ImageView ivStoreInside;
    @BindView(R.id.iv_store_other1)
    ImageView ivStoreOther1;
    @BindView(R.id.iv_store_other2)
    ImageView ivStoreOther2;
    @BindView(R.id.iv_store_other3)
    ImageView ivStoreOther3;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;

    final int REQUESTCODE_STORE_HEAD = 2101;
    final int REQUESTCODE_STORE_CASHIER = 2102;
    final int REQUESTCODE_STORE_INSIDE = 2103;
    final int REQUESTCODE_SOTRE_OTHER1 = 2104;
    final int REQUESTCODE_SOTRE_OTHER2 = 2105;
    final int REQUESTCODE_SOTRE_OTHER3 = 2106;
    final int STORE_TYPE = 201;
    final int REQUESTCODE_INCOMINGS = 202; //页面传递进件数据

    String cate_name;
    String cate_id;
    String province = "", province_name = "", city = "", city_name = "", area = "", area_name = "";
    SelectAddrModule selectAddrModule;
    String phone;
    @BindView(R.id.ll_reject_tips)
    LinearLayout llRejectTips;
    private InComing inComing;
    RejectDialog mRejectDialog;


    private String merchant_id;  //进件驳回传入
    private boolean isRejectInComing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_merchant_info);
        init();
    }

    private void init() {
        setTitle("店铺信息");
        storeName.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20), new EmojiFilter()});
        addrDetail.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(25), new EmojiFilter()});
        mRejectDialog = new RejectDialog(this);
        phone = getIntent().getStringExtra("phone");
        inComing = (InComing) getIntent().getSerializableExtra("incoming");
        merchant_id = getIntent().getStringExtra("merchant_id");
        if (merchant_id != null && !merchant_id.equals("")) {
            isRejectInComing = true;
            mvpPresenter.getInComingDetail(merchant_id);
        }

        if (isRejectInComing) {
            llRejectTips.setVisibility(View.VISIBLE);
            setRight("驳回原因", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRejectDialog.show(inComing.getFailure_msg(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRejectDialog.dismiss();
                        }
                    });
                }
            });
        } else {
            updateUI();
            setRight("保存", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveInComingToDB();
                }
            });
        }

        selectAddrModule = new SelectAddrModule(this, new Callback<String>() {
            @Override
            public void onSuccess(String[] s) {
                province_name = s[0];
                province = s[1];
                city_name = s[2];
                city = s[3];
                area_name = s[4];
                area = s[5];
                addr.setDes(province_name + city_name + area_name);
            }
        });
//        filterRate(wechatRate.getEditText());
//        filterRate(alipayRate.getEditText());
//        filterRate(unionpayRate.getEditText());
//        filterRate(largeUnionpayRate.getEditText());
    }

    /**
     * 根据对界面重新赋值
     */
    private void updateUI() {
        if (inComing == null) {
            return;
        }
        phone = inComing.getMblNo();
        if (phone != null && !phone.equals("")) {
            inComing.setId(Long.valueOf(phone)); //手机号作主键id
        }
        storeName.setText(inComing.getMecDisNm());
        storeType.setDes(inComing.getCate_name());
        cate_id = inComing.getCate_id();
        cate_name = inComing.getCate_name();
        addr.setDes(inComing.getRegProv() + inComing.getRegCity() + inComing.getRegDist());
        province = inComing.getRegProvCd();
        province_name = inComing.getRegProv();
        city = inComing.getRegCityCd();
        city_name = inComing.getRegCity();
        area = inComing.getRegDistCd();
        area_name = inComing.getRegDist();
        addrDetail.setText(inComing.getCprRegAddr());
//        wechatRate.setText(inComing.getWxQrcodeList());
//        alipayRate.setText(inComing.getAliQrcodeList());
//        unionpayRate.setText(inComing.getThousandQrcodeList());
//        largeUnionpayRate.setText(inComing.getHighestQrcodeList());
        store_head = inComing.getStorePic();
        store_head_load = inComing.getStoreLoadPic();
        store_cashier = inComing.getBusinessPlacePic();
        store_cashier_load = inComing.getBusinessPlaceLoadPic();
        store_inside = inComing.getInsideScenePic();
        store_inside_load = inComing.getInsideSceneLoadPic();
        if (store_head_load != null && !store_head_load.equals("")) {
            Glide.with(this).load(store_head_load).into(ivStoreHead);
        }
        if (store_cashier_load != null && !store_cashier_load.equals("")) {
            Glide.with(this).load(store_cashier_load).into(ivStoreCashier);
        }
        if (store_inside_load != null && !store_inside_load.equals("")) {
            Glide.with(this).load(store_inside_load).into(ivStoreInside);
        }
    }

    /**
     * 保存进行数据到数据库
     */
    private void saveInComingToDB() {
        if (storeName.isEmpty()) {
            ToastUtil.show("请输入门店名称");
            return;
        }
        saveInComing();
        DBManager.getInstance(MicroMerchantInfoActivity.this).insertInComing(inComing);
        ToastUtil.show("保存成功");
    }

    /**
     * 保存进行数据
     */
    private void saveInComing() {
        if (inComing == null) {
            inComing = new InComing();
        }
        if (phone != null && !phone.equals("")) {
            inComing.setId(Long.valueOf(phone)); //手机号作主键id
        }
        inComing.setMerchant_type("1");
        inComing.setCate_id(cate_id);
        inComing.setCate_name(cate_name);
        inComing.setMecDisNm(storeName.getText()); //商户名称
        inComing.setMblNo(phone);
//        inComing.setWxQrcodeList(wechatRate.getText());
//        inComing.setAliQrcodeList(alipayRate.getText());
//        inComing.setThousandQrcodeList(unionpayRate.getText());
//        inComing.setHighestQrcodeList(largeUnionpayRate.getText());
        inComing.setRegProvCd(province);
        inComing.setRegCityCd(city);
        inComing.setRegDistCd(area);
        inComing.setCprRegAddr(addrDetail.getText());
        inComing.setStorePic(store_head);
        inComing.setBusinessPlacePic(store_cashier);
        inComing.setInsideScenePic(store_inside);
        inComing.setRegProv(province_name);
        inComing.setRegCity(city_name);
        inComing.setRegDist(area_name);
        inComing.setStoreLoadPic(store_head_load);
        inComing.setBusinessPlaceLoadPic(store_cashier_load);
        inComing.setInsideSceneLoadPic(store_inside_load);
        inComing.setCreateTime(DateUtils.getStringDate());
    }

    /**
     * 过滤只能输入小数点两位数字
     */
//    private void filterRate(EditText editText) {
//
//       editText.setFilters(new InputFilter[]{new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//
//               if (dest.toString().length() == 0) {
//                   if(source.length() == 1){
//                       if(source.equals(".")){
//                           return "0.";
//                       }else if(source.equals("0")){
//                           return source;
//                       }else {
//                           return "0." + source;
//                       }
//                   }
//                }
//                if (dest.toString().contains(".")) {
//                    int index = dest.toString().indexOf(".");
//                    int length = dest.toString().substring(index).length();
//                    if (length == 3) {
//                        return "";
//                    }
//                }
//                //以0开头不允许输入连续的0如00，000
//                if (dest.toString().startsWith("0")){
//                   if(dest.toString().length() == 1 && !source.equals(".")){
//                       return "";
//                   }
//                }
//                return null;
//            }
//        }});
//    }

    @Override
    protected MicroMerchantInfoPresenter createPresenter() {
        return new MicroMerchantInfoPresenter(this, this);
    }

    @OnClick({R.id.iv_store_head, R.id.iv_store_cashier, R.id.iv_store_inside, R.id.iv_store_other1, R.id.iv_store_other2, R.id.iv_store_other3, R.id.btn_next_step, R.id.store_type, R.id.addr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_store_head:
                SelectImageUtils.getInsingle(this).showWindow(btnNextStep, REQUESTCODE_STORE_HEAD);
                break;
            case R.id.iv_store_cashier:
                SelectImageUtils.getInsingle(this).showWindow(btnNextStep, REQUESTCODE_STORE_CASHIER);
                break;
            case R.id.iv_store_inside:
                SelectImageUtils.getInsingle(this).showWindow(btnNextStep, REQUESTCODE_STORE_INSIDE);
                break;
            case R.id.iv_store_other1:
                SelectImageUtils.getInsingle(this).showWindow(btnNextStep, REQUESTCODE_SOTRE_OTHER1);
                break;
            case R.id.iv_store_other2:
                SelectImageUtils.getInsingle(this).showWindow(btnNextStep, REQUESTCODE_SOTRE_OTHER2);
                break;
            case R.id.iv_store_other3:
                SelectImageUtils.getInsingle(this).showWindow(btnNextStep, REQUESTCODE_SOTRE_OTHER3);
                break;
            case R.id.btn_next_step:
                if (storeName.isEmpty()) {
                    ToastUtil.show("请输入门店名称");
                    return;
                }
                if (cate_name == null || cate_name.equals("")) {
                    ToastUtil.show("请选择主营类型");
                    return;
                }
                if (province == null || province.equals("")) {
                    ToastUtil.show("请填选择地区");
                    return;
                }
                if (addrDetail.isEmpty()) {
                    ToastUtil.show("请输入门店门店地址");
                    return;
                }
                if (store_head == null || store_head.equals("")) {
                    ToastUtil.show("请上传门头照片");
                    return;
                }
                if (store_cashier == null || store_cashier.equals("")) {
                    ToastUtil.show("请上传收银台照片");
                    return;
                }
                if (store_inside == null || store_inside.equals("")) {
                    ToastUtil.show("请上传内景照片");
                    return;
                }
//                if (wechatRate.isEmpty()) {
//                    ToastUtil.show("请输入微信费率");
//                    return;
//                }
//                if (Float.parseFloat(wechatRate.getText()) < 0.3 || Float.parseFloat(wechatRate.getText()) > 1) {
//                    ToastUtil.show("请输入微信费率：0.3～1.00");
//                    return;
//                }
//                if (alipayRate.isEmpty()) {
//                    ToastUtil.show("请输入支付宝费率");
//                    return;
//                }
//                if (Float.parseFloat(alipayRate.getText()) < 0.3 || Float.parseFloat(alipayRate.getText()) > 1) {
//                    ToastUtil.show("请输入支付宝费率：0.3～1.00");
//                    return;
//                }
//                if (unionpayRate.isEmpty()) {
//                    ToastUtil.show("请输入银联0-1000元费率");
//                    return;
//                }
//                if (Float.parseFloat(unionpayRate.getText()) < 0.3 || Float.parseFloat(unionpayRate.getText()) > 1) {
//                    ToastUtil.show("请输入银联0-1000元费率：0.3～1.00");
//                    return;
//                }
//                if (largeUnionpayRate.isEmpty()) {
//                    ToastUtil.show("请输入银联1000元以上费率");
//                    return;
//                }
//                if (Float.parseFloat(largeUnionpayRate.getText()) < 0.6 || Float.parseFloat(largeUnionpayRate.getText()) > 1) {
//                    ToastUtil.show("请输入银联1000元以上费率：0.6～1.00");
//                    return;
//                }
                if (inComing == null) {
                    inComing = new InComing();
                }
                inComing.setCate_id(cate_id);
                inComing.setCate_name(cate_name);
                inComing.setMecDisNm(storeName.getText()); //商户名称
                inComing.setMblNo(phone);
//                inComing.setWxQrcodeList(wechatRate.getText());
//                inComing.setAliQrcodeList(alipayRate.getText());
//                inComing.setThousandQrcodeList(unionpayRate.getText());
//                inComing.setHighestQrcodeList(largeUnionpayRate.getText());
                inComing.setRegProvCd(province);
                inComing.setRegCityCd(city);
                inComing.setRegDistCd(area);
                inComing.setCprRegAddr(addrDetail.getText());
                inComing.setStorePic(store_head);
                inComing.setBusinessPlacePic(store_cashier);
                inComing.setInsideScenePic(store_inside);
                inComing.setRegProv(province_name);
                inComing.setRegCity(city_name);
                inComing.setRegDist(area_name);
                inComing.setStoreLoadPic(store_head_load);
                inComing.setBusinessPlaceLoadPic(store_cashier_load);
                inComing.setInsideSceneLoadPic(store_inside_load);
                Bundle bundle = new Bundle();
                bundle.putSerializable("incoming", inComing);
                if(isRejectInComing){
                    bundle.putBoolean("reject_incoming", isRejectInComing);
                }
                jumpActivityForResult(MicroMerchantAuthenticationActivity.class, bundle, REQUESTCODE_INCOMINGS);
                break;
            case R.id.store_type:
                if (cate_id != null && !"".equals(cate_id)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("cate_id", cate_id);
                    jumpActivityForResult(StoreTypeActivity.class, bundle2, STORE_TYPE);
                } else {
                    jumpActivityForResult(StoreTypeActivity.class, STORE_TYPE);
                }
                break;
            case R.id.addr:
                selectAddrModule.showPickerView(new Callback<String>() {
                    @Override
                    public void onSuccess(String[] s) {
                        province_name = s[0];
                        province = s[1];
                        city_name = s[2];
                        city = s[3];
                        area_name = s[4];
                        area = s[5];
                        addr.setDes(province_name + city_name + area_name);
                    }
                }, addr, (RelativeLayout) findViewById(R.id.rootview));
                break;
        }
    }

    String store_head = "", store_cashier = "", store_inside = "", store_other1 = "", store_other2 = "", store_other3 = "";
    String store_head_load = "", store_cashier_load = "", store_inside_load = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            String img_url = "";
            if (selectList != null && selectList.size() > 0) {
                img_url = selectList.get(0).getCompressPath();
                if (img_url == null) {
                    img_url = selectList.get(0).getPath();
                }
            }
            if(img_url != null && !img_url.equals("")){
                img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
            }
            switch (requestCode) {
                case REQUESTCODE_STORE_HEAD:
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_STORE_HEAD);
                    break;
                case REQUESTCODE_STORE_CASHIER:
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_STORE_CASHIER);
                    break;
                case REQUESTCODE_STORE_INSIDE:
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_STORE_INSIDE);
                    break;
                case REQUESTCODE_SOTRE_OTHER1:
                    Glide.with(this).load(new File(img_url)).into(ivStoreOther1);
                    break;
                case REQUESTCODE_SOTRE_OTHER2:
                    Glide.with(this).load(new File(img_url)).into(ivStoreOther2);
                    break;
                case REQUESTCODE_SOTRE_OTHER3:
                    Glide.with(this).load(new File(img_url)).into(ivStoreOther3);
                    break;
                case STORE_TYPE:
                    cate_name = data.getStringExtra("cate_name");
                    cate_id = data.getStringExtra("cate_id");
                    storeType.setDes(cate_name + "");
                    break;
                case REQUESTCODE_INCOMINGS:
                    inComing =  (InComing) data.getSerializableExtra("incoming");
                    break;
            }
        }
    }

    @Override
    public void uploadImageUrl(UploadImageBean data, int code, String originImage) {
        switch (code) {
            case REQUESTCODE_STORE_HEAD:
                store_head = data.getSrc();
                store_head_load = data.getComplete_src();
                Glide.with(this).load(new File(originImage)).into(ivStoreHead);
                break;
            case REQUESTCODE_STORE_CASHIER:
                store_cashier = data.getSrc();
                store_cashier_load = data.getComplete_src();
                Glide.with(this).load(new File(originImage)).into(ivStoreCashier);
                break;
            case REQUESTCODE_STORE_INSIDE:
                store_inside = data.getSrc();
                store_inside_load = data.getComplete_src();
                Glide.with(this).load(new File(originImage)).into(ivStoreInside);
                break;
        }
    }

    @Override
    public void showInComingDetail(InComingDetailBean data) {
        if (data != null) {
            if (inComing == null) {
                inComing = new InComing();
            }
            inComing.setMerchant_id(data.getParts_info().getMerchant_id());
            inComing.setMember_id(data.getMember_info().getId());
            inComing.setMerchant_name(data.getStore_info().getName());
            inComing.setStatus(data.getParts_info().getStatus());
            inComing.setIncoming_parts_id(data.getParts_info().getId());
            inComing.setStore_id(data.getParts_info().getStore_id());
            inComing.setCate_id(data.getStore_info().getCate_id());
            inComing.setCate_name(data.getStore_info().getCate_name());
            inComing.setMecDisNm(data.getParts_info().getContent().getMecDisNm()); //商户名称
            inComing.setMblNo(data.getParts_info().getContent().getMblNo());
//            inComing.setWxQrcodeList(data.getParts_info().getContent().getWxQrcodeList());
//            inComing.setAliQrcodeList(data.getParts_info().getContent().getAliQrcodeList());
//            inComing.setThousandQrcodeList(data.getParts_info().getContent().getThousandQrcodeList());
//            inComing.setHighestQrcodeList(data.getParts_info().getContent().getHighestQrcodeList());
            inComing.setRegProvCd(data.getParts_info().getContent().getRegProvCd());
            inComing.setRegCityCd(data.getParts_info().getContent().getRegCityCd());
            inComing.setRegDistCd(data.getParts_info().getContent().getRegDistCd());
            inComing.setCprRegAddr(data.getParts_info().getContent().getCprRegAddr());
            inComing.setStorePic(data.getParts_info().getContent().getStorePic());
            inComing.setBusinessPlacePic(data.getParts_info().getContent().getBusinessPlacePic());
            inComing.setInsideScenePic(data.getParts_info().getContent().getInsideScenePic());
            inComing.setRegProv(data.getStore_info().getProvince_name());
            inComing.setRegCity(data.getStore_info().getCity_name());
            inComing.setRegDist(data.getStore_info().getArea_name());
            inComing.setActNm(data.getParts_info().getContent().getActNm());  //身份证姓名
            inComing.setStmManIdNo(data.getParts_info().getContent().getStmManIdNo()); //身份证号
            inComing.setActNo(data.getParts_info().getContent().getActNo()); //银行卡号
            inComing.setLbnkNo(data.getParts_info().getContent().getLbnkNo()); //开户支行号
            inComing.setLbnkNm(data.getParts_info().getContent().getLbnkNm()); //开户行
            inComing.setBankCode(data.getParts_info().getBank_info().getInterbank_code());
            inComing.setBankName(data.getParts_info().getBank_info().getBank_name());
            inComing.setBankCityName(data.getParts_info().getBank_info().getCity_name());
            inComing.setBankCityCode(data.getParts_info().getBank_info().getCity_num());
            inComing.setLegalPersonidPositivePic(data.getParts_info().getContent().getLegalPersonidPositivePic()); //结算人身份证正面
            inComing.setLegalPersonidOppositePic(data.getParts_info().getContent().getLegalPersonidOppositePic()); //结算人身份证反面
            inComing.setBankCardPositivePic(data.getParts_info().getContent().getBankCardPositivePic()); //银行卡正面
            inComing.setBankCardOppositePic(data.getParts_info().getContent().getBankCardOppositePic()); //银行卡反面
            inComing.setLegalPersonidPositiveLoadPic(data.getParts_info().getLegalPersonidPositivePic());
            inComing.setLegalPersonidOppositeLoadPic(data.getParts_info().getLegalPersonidOppositePic());
            inComing.setBankCardPositiveLoadPic(data.getParts_info().getBankCardPositivePic());
            inComing.setBankCardOppositeLoadPic(data.getParts_info().getBankCardOppositePic());
            inComing.setStoreLoadPic(data.getParts_info().getStorePic());
            inComing.setBusinessPlaceLoadPic(data.getParts_info().getBusinessPlacePic());
            inComing.setInsideSceneLoadPic(data.getParts_info().getInsideScenePic());
            inComing.setFailure_msg(data.getParts_info().getFailure_msg());
            updateUI();
        }
    }
}
