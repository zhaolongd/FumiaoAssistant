package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.home.NormalMerchantBusinessPresenter;
import com.fumiao.assistant.mvp.home.NormalMerchantBusinessView;
import com.fumiao.core.uitls.FileUtils;
import com.fumiao.core.uitls.ImgUtils;
import com.fumiao.core.uitls.SelectImageUtils;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.core.widget.KeyEditText;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class NormalMerchantBusinessActivity extends MvpActivity<NormalMerchantBusinessPresenter> implements NormalMerchantBusinessView {

    @BindView(R.id.iv_business_license)
    ImageView ivBusinessLicense;
    @BindView(R.id.business_license_name)
    KeyEditText businessLicenseName;
    @BindView(R.id.business_license_code)
    KeyEditText businessLicenseCode; //注册登记号
    @BindView(R.id.business_category)
    ItemArrowLayout businessCategory;
    @BindView(R.id.legal_person_name)
    KeyEditText legalPersonName;
    @BindView(R.id.document_type)
    KeyEditText documentType;
    @BindView(R.id.id_card_no)
    KeyEditText idCardNo;
    @BindView(R.id.btn_complete)
    Button btnComplete;
    @BindView(R.id.iv_legal_person_front)
    ImageView ivLegalPersonFront;
    @BindView(R.id.iv_legal_person_back)
    ImageView ivLegalPersonBack;

    final int REQUESTCODE_BUSINESS_LICENSE = 2101;
    final int REQUESTCODE_MERCHANT_TYPE = 2102;
    final int REQUESTCODE_ID_CARD_FRONT = 2103;
    final int REQUESTCODE_ID_CARD_BACK = 2104;
    String business_license = "", business_license_load = "";
    // 居民身份证号的组成元素
    String[] IDCARD = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "x", "X",};

    private InComing inComing;
    String mcc_name = "", mcc_code = "";
    String id_card_front = "", id_card_back = "";
    String id_card_front_load = "", id_card_back_load = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_merchant_business);
        init();
    }

    private void init() {
        setTitle("工商信息");
        documentType.setText("身份证");
        documentType.getEditText().setFocusable(false);
        documentType.getEditText().setFocusableInTouchMode(false);
        idCardNo.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        idCardNo.getEditText().setKeyListener(DigitsKeyListener.getInstance("1234567890Xx"));
        idCardNo.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(18), inputFilter}); //限制身份证最多18位
        inComing = (InComing) getIntent().getSerializableExtra("incoming");
        updateUI();
    }

    /**
     * 根据对界面重新赋值
     */
    private void updateUI() {
        if (inComing == null) {
            return;
        }
        business_license = inComing.getLicensePic();
        business_license_load = inComing.getLicenseLoadPic();
        if (business_license_load != null && !business_license_load.equals("")) {
            Glide.with(this).load(business_license_load).into(ivBusinessLicense);
        }
        businessLicenseName.setText(inComing.getCprRegNmCn());
        businessLicenseCode.setText(inComing.getRegistCode());
        mcc_name = inComing.getMcc_name();
        mcc_code = inComing.getMccCd();
        businessCategory.setDes(mcc_name);
        legalPersonName.setText(inComing.getIdentityName());
        idCardNo.setText(inComing.getIdentityNo());
        id_card_front = inComing.getLegalPersonidPositivePic();
        id_card_back = inComing.getLegalPersonidOppositePic();
        id_card_front_load = inComing.getLegalPersonidPositiveLoadPic();
        id_card_back_load = inComing.getLegalPersonidOppositeLoadPic();

        if (id_card_front_load != null && !id_card_front_load.equals("")) {
            Glide.with(this).load(id_card_front_load).into(ivLegalPersonFront);
        }
        if (id_card_back_load != null && !id_card_back_load.equals("")) {
            Glide.with(this).load(id_card_back_load).into(ivLegalPersonBack);
        }
    }


    @OnClick({R.id.business_category, R.id.iv_legal_person_front, R.id.iv_legal_person_back, R.id.btn_complete, R.id.iv_business_license})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_business_license:
                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_BUSINESS_LICENSE);
                break;
            case R.id.iv_legal_person_front:
//                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_ID_CARD_FRONT);
                Intent intent = new Intent(NormalMerchantBusinessActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUESTCODE_ID_CARD_FRONT);
                break;
            case R.id.iv_legal_person_back:
//                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_ID_CARD_BACK);
                Intent intent1 = new Intent(NormalMerchantBusinessActivity.this, CameraActivity.class);
                intent1.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent1.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                startActivityForResult(intent1, REQUESTCODE_ID_CARD_BACK);
                break;
            case R.id.business_category:
                jumpActivityForResult(NormalMerchantTypeActivity.class, REQUESTCODE_MERCHANT_TYPE);
                break;
            case R.id.btn_complete:
                if (business_license_load == null || business_license_load.equals("")) {
                    ToastUtil.show("请上传营业执照照片");
                    return;
                }
                if (businessLicenseName.isEmpty()) {
                    ToastUtil.show("请输入营业执照名称");
                    return;
                }
                if (businessLicenseCode.isEmpty()) {
                    ToastUtil.show("请输入注册登记号");
                    return;
                }
                if (mcc_name == null || mcc_name.equals("")) {
                    ToastUtil.show("请选择工商类别");
                    return;
                }
                if (id_card_front_load == null || id_card_front_load.equals("")) {
                    ToastUtil.show("请上传法人身份证正面照片");
                    return;
                }
                if (id_card_back_load == null || id_card_back_load.equals("")) {
                    ToastUtil.show("请上传法人身份证反面照片");
                    return;
                }
                if (legalPersonName.isEmpty()) {
                    ToastUtil.show("请输入法人名字");
                    return;
                }
                if (idCardNo.isEmpty()) {
                    ToastUtil.show("请输入法人身份证号");
                    return;
                }
                inComing.setLicensePic(business_license);
                inComing.setLicenseLoadPic(business_license_load);
                inComing.setCprRegNmCn(businessLicenseName.getText());
                inComing.setRegistCode(businessLicenseCode.getText());
                inComing.setMcc_name(mcc_name);
                inComing.setMccCd(mcc_code);
                inComing.setLegalPersonidPositivePic(id_card_front); //结算人身份证正面
                inComing.setLegalPersonidOppositePic(id_card_back); //结算人身份证反面
                inComing.setLegalPersonidPositiveLoadPic(id_card_front_load); //结算人身份证正面
                inComing.setLegalPersonidOppositeLoadPic(id_card_back_load); //结算人身份证反面
                inComing.setIdentityName(legalPersonName.getText());
                inComing.setIdentityNo(idCardNo.getText());
                Intent intent2 = new Intent();
                intent2.putExtra("incoming", inComing);
                setResult(RESULT_OK, intent2);
                finish();
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
            if (img_url != null && !img_url.equals("")) {
                img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
            }
            switch (requestCode) {
                case REQUESTCODE_BUSINESS_LICENSE:
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_BUSINESS_LICENSE);
                    break;
                case REQUESTCODE_ID_CARD_FRONT:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_ID_CARD_FRONT);
                    //OCR识别身份证信息
                    mvpPresenter.ocrIDCardInfo(img_url);
                    break;
                case REQUESTCODE_ID_CARD_BACK:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_ID_CARD_BACK);
                    break;
                case REQUESTCODE_MERCHANT_TYPE:
                    mcc_code = data.getStringExtra("mcc_code");
                    mcc_name = data.getStringExtra("mcc_name");
                    businessCategory.setDes(mcc_name);
                    break;
            }
        }
    }

    final List<String> idCardList = Arrays.asList(IDCARD);
    InputFilter inputFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            // 返回空字符串，就代表匹配不成功，返回null代表匹配成功
            for (int i = 0; i < source.toString().length(); i++) {
                if (!idCardList.contains(String.valueOf(source.charAt(i)))) {
                    return "";
                }
                if (idCardNo.getText().length() < 17) {
                    if ("x".equals(String.valueOf(source.charAt(i))) || "X".equals(String.valueOf(source.charAt(i)))) {
                        return "";
                    }
                }
            }
            return null;
        }
    };

    @Override
    protected NormalMerchantBusinessPresenter createPresenter() {
        return new NormalMerchantBusinessPresenter(this, this);
    }

    @Override
    public void uploadImageUrl(UploadImageBean data, int code, String originalImage) {
        switch (code) {
            case REQUESTCODE_BUSINESS_LICENSE:
                business_license = data.getSrc();
                business_license_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivBusinessLicense);
                break;
            case REQUESTCODE_ID_CARD_FRONT:
                id_card_front = data.getSrc();
                id_card_front_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivLegalPersonFront);
                break;
            case REQUESTCODE_ID_CARD_BACK:
                id_card_back = data.getSrc();
                id_card_back_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivLegalPersonBack);
                break;
        }
    }

    @Override
    public void ocrIDCardSuccess(IDCardOcrBean data, String originImage) {

        if (data != null) {
            if (data.getCards() != null && data.getCards().size() > 0) {
                legalPersonName.setText(data.getCards().get(0).getName());
                idCardNo.setText(data.getCards().get(0).getId_card_number());
            } else {
                ToastUtil.show("身份证图片识别失败");
            }
        } else {
            ToastUtil.show("身份证图片识别失败");
        }
    }
}
