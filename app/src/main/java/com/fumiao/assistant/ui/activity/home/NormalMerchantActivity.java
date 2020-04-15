package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.home.NormalMerchantPresenter;
import com.fumiao.assistant.mvp.home.NormalMerchantView;
import com.fumiao.assistant.tools.SelectAddrModule;
import com.fumiao.core.uitls.Callback;
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

public class NormalMerchantActivity extends MvpActivity<NormalMerchantPresenter> implements NormalMerchantView {

    @BindView(R.id.radio_private_account)
    RadioButton radioPrivateAccount;
    @BindView(R.id.radio_public_account)
    RadioButton radioPublicAccount;
    @BindView(R.id.radio_merchant_type)
    RadioGroup radioMerchantType;
    @BindView(R.id.merchant_name)
    KeyEditText merchantName;
    @BindView(R.id.store_type)
    ItemArrowLayout storeType;
    @BindView(R.id.addr)
    ItemArrowLayout addr;
    @BindView(R.id.addr_detail)
    KeyEditText addrDetail;
    @BindView(R.id.iv_store_head)
    ImageView ivStoreHead;
    @BindView(R.id.iv_store_cashier)
    ImageView ivStoreCashier;
    @BindView(R.id.iv_store_inside)
    ImageView ivStoreInside;
    @BindView(R.id.btn_complete)
    Button btnComplete;

    final int REQUESTCODE_STORE_HEAD = 2101;
    final int REQUESTCODE_STORE_CASHIER = 2102;
    final int REQUESTCODE_STORE_INSIDE = 2103;
    final int STORE_TYPE = 201;

    private InComing inComing;

    String cate_name;
    String cate_id;
    String province = "", province_name = "", city = "", city_name = "", area = "", area_name = "";
    SelectAddrModule selectAddrModule;

    String store_head = "", store_cashier = "", store_inside = "";
    String store_head_load = "", store_cashier_load = "", store_inside_load = "";
    String haveLicenceNo; //02 个体 03企业
    boolean ishaveLicence = false; // true ：企业  false：个体


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_merchant);
        init();
    }

    private void init() {
        setTitle("商户信息");
        radioMerchantType.setOnCheckedChangeListener(radioGrouplisten);
        inComing = (InComing) getIntent().getSerializableExtra("incoming");

        merchantName.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20), new EmojiFilter()});
        addrDetail.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(25), new EmojiFilter()});
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
        updateUI();
    }

    private RadioGroup.OnCheckedChangeListener radioGrouplisten = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_private_account:
                    haveLicenceNo = "02";
                    ishaveLicence = false;
                    break;
                case R.id.radio_public_account:
                    haveLicenceNo = "03";
                    ishaveLicence = true;
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 根据对界面重新赋值
     */
    private void updateUI() {
        if (inComing == null) {
            return;
        }
        haveLicenceNo = inComing.getHaveLicenceNo();
        if(haveLicenceNo != null && haveLicenceNo.equals("03")){
            radioPublicAccount.setChecked(true);
        }else {
            radioPrivateAccount.setChecked(true); //初始默认个体
        }
        merchantName.setText(inComing.getMecDisNm());
        storeType.setDes(inComing.getCate_name());
        cate_id = inComing.getCate_id();
        cate_name = inComing.getCate_name();

        province = inComing.getRegProvCd();
        province_name = inComing.getRegProv();
        if(province_name == null){
            province_name = "";
        }
        city = inComing.getRegCityCd();
        city_name = inComing.getRegCity();
        if(city_name == null){
            city_name= "";
        }
        area = inComing.getRegDistCd();
        area_name = inComing.getRegDist();
        if(area_name == null){
            area_name = "";
        }
        addr.setDes(province_name+ city_name + area_name);
        addrDetail.setText(inComing.getCprRegAddr());
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

    @Override
    protected NormalMerchantPresenter createPresenter() {
        return new NormalMerchantPresenter(this, this);
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
                case STORE_TYPE:
                    cate_name = data.getStringExtra("cate_name");
                    cate_id = data.getStringExtra("cate_id");
                    storeType.setDes(cate_name + "");
                    break;
            }
        }
    }




    @OnClick({R.id.store_type, R.id.addr, R.id.iv_store_head, R.id.iv_store_cashier, R.id.iv_store_inside, R.id.iv_store_other1, R.id.iv_store_other2, R.id.iv_store_other3, R.id.btn_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.iv_store_head:
                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_STORE_HEAD);
                break;
            case R.id.iv_store_cashier:
                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_STORE_CASHIER);
                break;
            case R.id.iv_store_inside:
                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_STORE_INSIDE);
                break;
            case R.id.btn_complete:
                if (merchantName.isEmpty()) {
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
                if (inComing == null) {
                    inComing = new InComing();
                }
                inComing.setCate_id(cate_id);
                inComing.setCate_name(cate_name);
                inComing.setMecDisNm(merchantName.getText()); //商户名称
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
                inComing.setHaveLicenceNo(haveLicenceNo);
                inComing.setIsHaveLicence(ishaveLicence);
                Intent intent = new Intent();
                intent.putExtra("incoming", inComing);
                setResult(RESULT_OK, intent);
                finish();
        }
    }
}
