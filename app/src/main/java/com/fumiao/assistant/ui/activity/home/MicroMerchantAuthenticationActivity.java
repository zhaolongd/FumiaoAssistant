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
import android.widget.LinearLayout;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.BankCardOcrBean;
import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.InComingResultBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.home.MicroMerchantAuthenticationPresenter;
import com.fumiao.assistant.mvp.home.MicroMerchantAuthenticationView;
import com.fumiao.assistant.tools.DBManager;
import com.fumiao.assistant.ui.dialog.RejectDialog;
import com.fumiao.core.uitls.AppManager;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.core.uitls.EmojiFilter;
import com.fumiao.core.uitls.FileUtils;
import com.fumiao.core.uitls.ImgUtils;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.uitls.Utils;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.core.widget.KeyEditText;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 11:31
 * 小微商户店主认证
 * @Description:
 */
public class MicroMerchantAuthenticationActivity extends MvpActivity<MicroMerchantAuthenticationPresenter> implements MicroMerchantAuthenticationView {
    @BindView(R.id.card_front)
    ImageView cardFront;
    @BindView(R.id.card_reverse)
    ImageView cardReverse;
    @BindView(R.id.lin_card)
    LinearLayout linCard;
    @BindView(R.id.card_name)
    KeyEditText cardName;
    @BindView(R.id.id_card)
    KeyEditText idCard;
    @BindView(R.id.bank_front)
    ImageView bankFront;
    @BindView(R.id.bank_reverse)
    ImageView bankReverse;
    @BindView(R.id.lin_bank)
    LinearLayout linBank;
    @BindView(R.id.manager_bank_id)
    KeyEditText managerBankId;
    @BindView(R.id.bank)
    ItemArrowLayout bank;
    @BindView(R.id.bank_city)
    ItemArrowLayout bankCity;
    @BindView(R.id.bank_branch)
    ItemArrowLayout bankBranch;
    @BindView(R.id.btn_last_step)
    Button btnLastStep;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_reject_tips)
    LinearLayout llRejectTips;

    final int REQUESTCODE_CARD_FRONT = 2107;
    final int REQUESTCODE_CARD_REVERSE = 2108;
    final int REQUESTCODE_BANK_FRONT = 2109;
    final int REQUESTCODE_BANK_REVERSE = 2110;
    final int SELECT_BANK = 1; //选择开户银行code
    final int SELECT_BANK_BRANCH = 2; //选择开户银行支行

    private String bank_name;
    private String bank_code;
    private String bank_city_name;
    private String bank_city_code;
    private String bank_branch_code;
    private String bank_branch_name;
    private InComing inComing;

    private boolean isRejectInComing = false;
    RejectDialog mRejectDialog;

    // 居民身份证号的组成元素
    String[] IDCARD = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "x", "X", };

    @Override
    protected MicroMerchantAuthenticationPresenter createPresenter() {
        return new MicroMerchantAuthenticationPresenter(this, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        bank_city_name = intent.getStringExtra("city_name");
        bank_city_code = intent.getStringExtra("city_code");
        bankCity.setDes(bank_city_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_merchant_authentication);
        init();
    }

    public void init() {
        setTitle("店主认证");
        cardName.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(10), new EmojiFilter()});
        idCard.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        idCard.getEditText().setKeyListener(DigitsKeyListener.getInstance("1234567890Xx"));
        idCard.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(18), inputFilter}); //限制身份证最多18位
        mRejectDialog = new RejectDialog(this);
        inComing = (InComing) getIntent().getSerializableExtra("incoming");
        isRejectInComing = getIntent().getBooleanExtra("reject_incoming", false);
        updateUI();
        if(isRejectInComing){
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
        }else {
            setRight("保存", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveInComingToDB();
                    Intent intent = new Intent();
                    intent.putExtra("incoming", inComing);
                    setResult(RESULT_OK, intent);
                }
            });
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
                if (idCard.getText().length() < 17) {
                    if ("x".equals(String.valueOf(source.charAt(i))) || "X".equals(String.valueOf(source.charAt(i)))) {
                        return "";
                    }
                }
            }
            return null;
        }
    };

    /**
     * 根据对界面重新赋值
     */
    private void updateUI() {
        if (inComing == null) {
            return;
        }
        cardName.setText(inComing.getActNm());
        idCard.setText(inComing.getStmManIdNo());
        managerBankId.setText(inComing.getActNo());
        bank_branch_code = inComing.getLbnkNo();//开户支行号
        bank_branch_name = inComing.getLbnkNm(); //开户行
        card_front = inComing.getLegalPersonidPositivePic();
        card_reverse = inComing.getLegalPersonidOppositePic();
        bank_front = inComing.getBankCardPositivePic();
        bank_reverse = inComing.getBankCardOppositePic();
        bank_code = inComing.getBankCode();
        bank_name = inComing.getBankName();
        bank_city_code = inComing.getBankCityCode();
        bank_city_name = inComing.getBankCityName();
        card_front_load = inComing.getLegalPersonidPositiveLoadPic();
        card_reverse_load = inComing.getLegalPersonidOppositeLoadPic();
        bank_front_load = inComing.getBankCardPositiveLoadPic();
        bank_reverse_load = inComing.getBankCardOppositeLoadPic();
        bank.setDes(bank_name);
        bankCity.setDes(bank_city_name);
        bankBranch.setDes(bank_branch_name);
        if (card_front_load != null && !card_front_load.equals("")) {
            Glide.with(this).load(card_front_load).into(cardFront);
        }
        if (card_reverse_load != null && !card_reverse_load.equals("")) {
            Glide.with(this).load(card_reverse_load).into(cardReverse);
        }
        if (bank_front_load != null && !bank_front_load.equals("")) {
            Glide.with(this).load(bank_front_load).into(bankFront);
        }
        if (bank_reverse_load != null && !bank_reverse_load.equals("")) {
            Glide.with(this).load(bank_reverse_load).into(bankReverse);
        }
    }

    /**
     * 保存进行数据到数据库
     */
    private void saveInComingToDB() {
        saveInComing();
        DBManager.getInstance(MicroMerchantAuthenticationActivity.this).insertInComing(inComing);
        ToastUtil.show("保存成功");
    }

    /**
     * 保存进行数据
     */
    private void saveInComing() {
        inComing.setMerchant_type("1");
        inComing.setActNm(cardName.getText());  //身份证姓名
        inComing.setStmManIdNo(idCard.getText()); //身份证号
        inComing.setActNo(managerBankId.getText()); //银行卡号
        inComing.setLbnkNo(bank_branch_code); //开户支行号
        inComing.setLbnkNm(bank_branch_name); //开户行
        inComing.setLegalPersonidPositivePic(card_front); //结算人身份证正面
        inComing.setLegalPersonidOppositePic(card_reverse); //结算人身份证反面
        inComing.setBankCardPositivePic(bank_front); //银行卡正面
        inComing.setBankCardOppositePic(bank_reverse); //银行卡反面

        inComing.setLegalPersonidPositiveLoadPic(card_front_load);
        inComing.setLegalPersonidOppositeLoadPic(card_reverse_load);
        inComing.setBankCardPositiveLoadPic(bank_front_load);
        inComing.setBankCardOppositeLoadPic(bank_reverse_load);
        inComing.setBankCode(bank_code);
        inComing.setBankName(bank_name);
        inComing.setBankCityCode(bank_city_code);
        inComing.setBankCityName(bank_city_name);
        inComing.setCreateTime(DateUtils.getStringDate());
    }

    @OnClick({R.id.bank, R.id.bank_city, R.id.bank_branch, R.id.card_front, R.id.card_reverse, R.id.bank_front, R.id.bank_reverse, R.id.btn_submit, R.id.btn_last_step})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bank:
                jumpActivityForResult(SelectOpeningBankActivity.class, SELECT_BANK);
                break;
            case R.id.bank_city:
                if (bank_name == null || bank_name.equals("")) {
                    ToastUtil.show("请选择开户行");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("bank_code", bank_code);
                jumpActivity(SelectBankProvinceActivity.class, bundle);
                break;
            case R.id.bank_branch:
                if (bank_city_name == null || bank_city_name.equals("")) {
                    ToastUtil.show("请选择开户行城市");
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("bank_city_code", bank_city_code);
                bundle2.putString("bank_code", bank_code);
                jumpActivityForResult(SelectBankBranchActivity.class, bundle2, SELECT_BANK_BRANCH);
                break;
            case R.id.card_front:
//                SelectImageUtils.getInsingle(this).showWindow(btnSubmit, REQUESTCODE_CARD_FRONT);
                Intent intent = new Intent(this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUESTCODE_CARD_FRONT);
                break;
            case R.id.card_reverse:
//                SelectImageUtils.getInsingle(this).showWindow(btnSubmit, REQUESTCODE_CARD_REVERSE);
                Intent intent1 = new Intent(this, CameraActivity.class);
                intent1.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent1.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                startActivityForResult(intent1, REQUESTCODE_CARD_REVERSE);
                break;
            case R.id.bank_front:
//                SelectImageUtils.getInsingle(this).showWindow(btnSubmit, REQUESTCODE_BANK_FRONT);
                Intent intent2 = new Intent(this, CameraActivity.class);
                intent2.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent2.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent2, REQUESTCODE_BANK_FRONT);
                break;
            case R.id.bank_reverse:
//                SelectImageUtils.getInsingle(this).showWindow(btnSubmit, REQUESTCODE_BANK_REVERSE);
                Intent intent3 = new Intent(this, CameraActivity.class);
                intent3.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent3.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent3, REQUESTCODE_BANK_REVERSE);
                break;
            case R.id.btn_submit:
                if (card_front_load == null || card_front_load.equals("")) {
                    ToastUtil.show("请上传身份证正面");
                    return;
                }
                if (card_reverse_load == null || card_reverse_load.equals("")) {
                    ToastUtil.show("请上传身份证反面");
                    return;
                }
                if (cardName.isEmpty()) {
                    ToastUtil.show("请输入身份证姓名");
                    return;
                }

                if (idCard.isEmpty()) {
                    ToastUtil.show("请输入身份证号码");
                    return;
                }
                if(!Utils.isIDCard(idCard.getText())){
                    ToastUtil.show("请输入正确的身份证号码");
                    return;
                }
                if (managerBankId.isEmpty()) {
                    ToastUtil.show("请输入银行卡号");
                    return;
                }
                if (bank_name == null || bank_name.equals("")) {
                    ToastUtil.show("请选择开户行");
                    return;
                }
                if (bank_front_load == null || bank_front_load.equals("")) {
                    ToastUtil.show("请上传银行卡正面");
                    return;
                }
                inComing.setActNm(cardName.getText());  //身份证姓名
                inComing.setStmManIdNo(idCard.getText()); //身份证号
                inComing.setActNo(managerBankId.getText()); //银行卡号
                inComing.setLbnkNo(bank_branch_code); //开户支行号
                inComing.setLbnkNm(bank_branch_name); //开户行
                inComing.setLegalPersonidPositivePic(card_front); //结算人身份证正面
                inComing.setLegalPersonidOppositePic(card_reverse); //结算人身份证反面
                inComing.setBankCardPositivePic(bank_front); //银行卡正面
                inComing.setBankCardOppositePic(bank_reverse); //银行卡反面
                if(isRejectInComing){
                    mvpPresenter.rejectInComingPart(inComing);
                }else {
                    mvpPresenter.inComingPart(inComing);
                }
                break;
            case R.id.btn_last_step:
                saveInComing();
                Intent intent4 = new Intent();
                intent4.putExtra("incoming", inComing);
                setResult(RESULT_OK, intent4);
                finish();
                break;
        }
    }

    String card_front = "", card_reverse = "", bank_front = "", bank_reverse = "";

    String card_front_load = "", card_reverse_load = "", bank_front_load = "", bank_reverse_load = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
//            String img_url = "";
//            if (selectList != null && selectList.size() > 0) {
//                img_url = selectList.get(0).getCompressPath();
//                if (img_url == null) {
//                    img_url = selectList.get(0).getPath();
//                }
//            }
//            if (img_url != null && !img_url.equals("")) {
//                img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
//            }
            String img_url = "";
            switch (requestCode) {
                case SELECT_BANK:
                    bank_city_name = "";
                    bank_city_code = "";
                    bank_branch_code = "";
                    bank_branch_name = "";
                    bankCity.setDes("");
                    bankBranch.setDes("");
                    bank_name = data.getStringExtra("bank_name");
                    bank_code = data.getStringExtra("bank_code");
                    bank.setDes(bank_name);
                    break;
                case SELECT_BANK_BRANCH:
                    bank_branch_name = data.getStringExtra("bank_branch_name");
                    bank_branch_code = data.getStringExtra("bank_branch_code");
                    bankBranch.setDes(bank_branch_name);
                    break;
                case REQUESTCODE_CARD_FRONT:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_CARD_FRONT);
                    //OCR识别身份证信息
                    mvpPresenter.ocrIDCardInfo(img_url);
                    break;
                case REQUESTCODE_CARD_REVERSE:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_CARD_REVERSE);
                    break;
                case REQUESTCODE_BANK_FRONT:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_BANK_FRONT);
                    //OCR识别银行卡信息
                    mvpPresenter.ocrBankCardInfo(img_url);
                    break;
                case REQUESTCODE_BANK_REVERSE:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_BANK_REVERSE);
                    break;
            }
        }
    }

    @Override
    public void uploadImageUrl(UploadImageBean data, int code, String originalImage) {
        switch (code) {
            case REQUESTCODE_CARD_FRONT:
                card_front = data.getSrc();
                card_front_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(cardFront);
                break;
            case REQUESTCODE_CARD_REVERSE:
                card_reverse = data.getSrc();
                card_reverse_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(cardReverse);
                break;
            case REQUESTCODE_BANK_FRONT:
                bank_front = data.getSrc();
                bank_front_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(bankFront);
                break;
            case REQUESTCODE_BANK_REVERSE:
                bank_reverse = data.getSrc();
                bank_reverse_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(bankReverse);
                break;
        }
    }

    /**
     * 进件成功
     */
    @Override
    public void inComingSuccess(InComingResultBean data) {
        ToastUtil.show(data.getMsg());
        if (inComing != null) {
            DBManager.getInstance(this).deleteInComing(inComing);
        }
//        int status = data.getIncoming_status();
//        if(status == 1){
//            jumpActivity(InComingResultActivity.class);
//        }else {
            AppManager.getAppManager().backToTag(MAIN_PAGE);
            jumpActivity(MerchantAuditActivity.class);
//        }
    }

    @Override
    public void rejectInComingSuccess(InComingResultBean data) {
        ToastUtil.show(data.getMsg());
        int status = data.getIncoming_status();
//        if(status == 1){
//            Bundle bundle = new Bundle();
//            bundle.putBoolean("reject_incoming", true);
//            jumpActivity(InComingResultActivity.class, bundle);
//        }else {
            AppManager.getAppManager().backToTag(MAIN_PAGE);
            jumpActivity(MerchantAuditActivity.class);
//        }
    }

    @Override
    public void ocrIDCardSuccess(IDCardOcrBean data, String originImage) {
        if (data != null) {
            if (data.getCards() != null && data.getCards().size() > 0) {
                cardName.setText(data.getCards().get(0).getName());
                idCard.setText(data.getCards().get(0).getId_card_number());
            } else {
                ToastUtil.show("身份证图片识别失败");
            }
        } else {
            ToastUtil.show("身份证图片识别失败");
        }
    }

    @Override
    public void ocrBankCardSuccess(BankCardOcrBean data, String originImage) {
        if (data != null) {
            if (data.getBank_cards() != null && data.getBank_cards().size() > 0) {
                managerBankId.setText(data.getBank_cards().get(0).getNumber());
            } else {
                ToastUtil.show("银行卡图片识别失败");
            }
        } else {
            ToastUtil.show("银行卡图片识别失败");
        }
    }
}
