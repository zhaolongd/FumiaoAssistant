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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.BankCardOcrBean;
import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.home.NormalMerchantSettlementPresenter;
import com.fumiao.assistant.mvp.home.NormalMerchantSettlementView;
import com.fumiao.core.uitls.FileUtils;
import com.fumiao.core.uitls.ImgUtils;
import com.fumiao.core.uitls.SelectImageUtils;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.uitls.Utils;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.core.widget.KeyEditText;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: XieBoss
 * Date: 2019/10/8 0008 16:49
 *
 * @Description:
 */
public class NormalMerchantSettlementActivity extends MvpActivity<NormalMerchantSettlementPresenter> implements NormalMerchantSettlementView {

    @BindView(R.id.radio_legal_person)
    RadioButton radioLegalPerson;
    @BindView(R.id.radio_no_legal_person)
    RadioButton radioNoLegalPerson;
    @BindView(R.id.radio_person)
    RadioGroup radioPerson;
    @BindView(R.id.lin_card)
    LinearLayout linCard;
    @BindView(R.id.ll_no_legal_person)
    LinearLayout llNoLegalPerson;
    @BindView(R.id.lin_bank)
    LinearLayout linBank;
    @BindView(R.id.ll_legal_person)
    LinearLayout llLegalPerson;
    @BindView(R.id.ll_private_account)
    LinearLayout llPrivateAccount;
    @BindView(R.id.public_account_name)
    KeyEditText publicAccountName;
    @BindView(R.id.iv_open_bank)
    ImageView ivOpenBank;
    @BindView(R.id.ll_public_account)
    LinearLayout llPublicAccount;
    @BindView(R.id.settlement_bank_id)
    KeyEditText settlementBankId;
    @BindView(R.id.bank)
    ItemArrowLayout bank;
    @BindView(R.id.bank_city)
    ItemArrowLayout bankCity;
    @BindView(R.id.bank_branch)
    ItemArrowLayout bankBranch;

    @BindView(R.id.btn_complete)
    Button btnComplete;
    @BindView(R.id.iv_id_card_front)
    ImageView ivIdCardFront;
    @BindView(R.id.iv_id_card_back)
    ImageView ivIdCardBack;
    @BindView(R.id.iv_bank_card_front)
    ImageView ivBankCardFront;
    @BindView(R.id.iv_bank_card_back)
    ImageView ivBankCardBack;
    @BindView(R.id.id_card_name)
    KeyEditText idCardName;
    @BindView(R.id.id_card_number)
    KeyEditText idCardNumber;
    @BindView(R.id.ll_settlement_person_radio)
    LinearLayout llSettlementPersonRadio;
    @BindView(R.id.iv_authorization_letter)
    ImageView ivAuthorizationLetter; //对私非法人授权函
    @BindView(R.id.radio_public_account)
    RadioButton radioPublicAccount;
    @BindView(R.id.radio_private_account)
    RadioButton radioPrivateAccount;
    @BindView(R.id.radio_settlement_type)
    RadioGroup radioSettlementType;
    @BindView(R.id.radio_no_work_day)
    RadioButton radioNoWorkDay;
    @BindView(R.id.radio_work_day)
    RadioButton radioWorkDay;
    @BindView(R.id.radio_no_work_day_settlement)
    RadioGroup radioNoWorkDaySettlement;

    private InComing inComing;
    final int REQUESTCODE_ID_CARD_FRONT = 2107;
    final int REQUESTCODE_ID_CARD_BACK = 2108;
    final int REQUESTCODE_BANK_CARD_FRONT = 2109;
    final int REQUESTCODE_BANK_CARD_BACK = 2110;
    final int REQUESTCODE_OPEN_BANK = 2111;
    final int REQUESTCODE_AUTHORIZATION_LETTER = 2112;
    final int SELECT_BANK = 1; //选择开户银行code
    final int SELECT_BANK_BRANCH = 2; //选择开户银行支行

    private String bank_name;
    private String bank_code;
    private String bank_city_name;
    private String bank_city_code;
    private String bank_branch_code;
    private String bank_branch_name;
    String id_card_front = "", id_card_back = "", bank_card_front = "", bank_card_back = "", open_bank, authorization_letter;
    String id_card_front_load = "", id_card_back_load = "", bank_card_front_load = "", bank_card_back_load, open_bank_load = "", authorization_letter_load;
    // 居民身份证号的组成元素
    String[] IDCARD = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "x", "X",};

    boolean isPublicAccountSettlement = true; // true ：对公结算  false：对私结算
    boolean isLegalPerson = true; //结算人 true:法人 false：非法人
    boolean isNoWorkDaySettlement = true; //非工作日


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
        setContentView(R.layout.activity_normal_merchant_settlement);
        init();
    }

    @Override
    protected NormalMerchantSettlementPresenter createPresenter() {
        return new NormalMerchantSettlementPresenter(this, this);
    }

    private void init() {
        setTitle("结算信息");
        radioPerson.setOnCheckedChangeListener(radioPersonTypeListen);
        radioSettlementType.setOnCheckedChangeListener(radioSettlementTypelisten);
        radioNoWorkDaySettlement.setOnCheckedChangeListener(radioSettlementDayListen);
        idCardNumber.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        idCardNumber.getEditText().setKeyListener(DigitsKeyListener.getInstance("1234567890Xx"));
        idCardNumber.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(18), inputFilter}); //限制身份证最多18位
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
        settlementBankId.setText(inComing.getActNo());
        bank_branch_code = inComing.getLbnkNo();//开户支行号
        bank_branch_name = inComing.getLbnkNm(); //开户行
        bank_code = inComing.getBankCode();
        bank_name = inComing.getBankName();
        bank_city_code = inComing.getBankCityCode();
        bank_city_name = inComing.getBankCityName();
        bank.setDes(bank_name);
        bankCity.setDes(bank_city_name);
        bankBranch.setDes(bank_branch_name);

        id_card_front = inComing.getSettlePersonIdcardPositive();
        id_card_back = inComing.getSettlePersonIdcardOpposite();
        bank_card_front = inComing.getBankCardPositivePic();
        bank_card_back = inComing.getBankCardOppositePic();
        id_card_front_load = inComing.getSettlePersonIdcardLoadPositive();
        id_card_back_load = inComing.getSettlePersonIdcardLoadOpposite();
        bank_card_front_load = inComing.getBankCardPositiveLoadPic();
        bank_card_back_load = inComing.getBankCardOppositeLoadPic();
        open_bank = inComing.getOpeningAccountLicensePic();
        open_bank_load = inComing.getOpeningAccountLicenseLoadPic();
        authorization_letter = inComing.getLetterOfAuthPic();
        authorization_letter_load = inComing.getLetterOfAuthPicLoadPic();

        if(inComing.getActTyp() == null){
            radioPublicAccount.setChecked(true); //默认对公结算
        }else if (inComing.getActTyp().equals("00")){
            //对公结算
            radioPublicAccount.setChecked(true);
            publicAccountName.setText(inComing.getActNm());
        }else if(inComing.getActTyp().equals("01")){
            //对私结算
            radioPrivateAccount.setChecked(true);
            if(inComing.getPeople_type() ==2){
                //非法人
                radioNoLegalPerson.setChecked(true);
                idCardName.setText(inComing.getActNm());
                idCardNumber.setText(inComing.getStmManIdNo());
            }if(inComing.getPeople_type() ==1){
                //法人 结算人身份证需要清空
                id_card_front = "";
                id_card_back = "";
                id_card_front_load = "";
                id_card_back_load = "";
            }
        }else {
            radioPublicAccount.setChecked(true); //默认对公结算
        }

       if (id_card_front_load != null && !id_card_front_load.equals("")) {
            Glide.with(this).load(id_card_front_load).into(ivIdCardFront);
        }
        if (id_card_back_load != null && !id_card_back_load.equals("")) {
            Glide.with(this).load(id_card_back_load).into(ivIdCardBack);
        }
        if (bank_card_front_load != null && !bank_card_front_load.equals("")) {
            Glide.with(this).load(bank_card_front_load).into(ivBankCardFront);
        }
        if (bank_card_back_load != null && !bank_card_back_load.equals("")) {
            Glide.with(this).load(bank_card_back_load).into(ivBankCardBack);
        }
        if(open_bank_load != null && !open_bank_load.equals("")){
            Glide.with(this).load(open_bank_load).into(ivOpenBank);
        }
        if(authorization_letter_load != null && !authorization_letter_load.equals("")){
            Glide.with(this).load(authorization_letter_load).into(ivAuthorizationLetter);
        }
        if(inComing.getSettleType() == null){
            radioNoWorkDay.setChecked(true);
        } else if(inComing.getSettleType().equals("04")){
            radioNoWorkDay.setChecked(true);
        }else if(inComing.getSettleType().equals("03")){
            radioWorkDay.setChecked(true);
        }else {
            radioNoWorkDay.setChecked(true);
        }
    }

    private RadioGroup.OnCheckedChangeListener radioSettlementDayListen = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_no_work_day:
                    isNoWorkDaySettlement = true;
                    inComing.setSettleType("04");
                    break;
                case R.id.radio_work_day:
                    isNoWorkDaySettlement = false;
                    inComing.setSettleType("03");
                    break;
                default:
                    break;
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener radioSettlementTypelisten = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_public_account:
                    isPublicAccountSettlement = true;
                    llPublicAccount.setVisibility(View.VISIBLE);
                    llSettlementPersonRadio.setVisibility(View.GONE);
                    llNoLegalPerson.setVisibility(View.GONE);
                    llLegalPerson.setVisibility(View.GONE);
                    break;
                case R.id.radio_private_account:
                    isPublicAccountSettlement = false;
                    llSettlementPersonRadio.setVisibility(View.VISIBLE);
                    llPublicAccount.setVisibility(View.GONE);
                    radioLegalPerson.setChecked(true);
                    isLegalPerson = true;
                    llLegalPerson.setVisibility(View.VISIBLE);
                    llNoLegalPerson.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener radioPersonTypeListen = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_legal_person:
                    isLegalPerson = true;
                    llLegalPerson.setVisibility(View.VISIBLE);
                    llNoLegalPerson.setVisibility(View.GONE);
                    break;
                case R.id.radio_no_legal_person:
                    isLegalPerson = false;
                    llNoLegalPerson.setVisibility(View.VISIBLE);
                    llLegalPerson.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    };

    @OnClick({R.id.iv_id_card_front, R.id.iv_id_card_back, R.id.iv_bank_card_front, R.id.iv_bank_card_back, R.id.iv_open_bank, R.id.iv_authorization_letter, R.id.bank, R.id.bank_city, R.id.bank_branch, R.id.btn_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_id_card_front:
                Intent intent = new Intent(NormalMerchantSettlementActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUESTCODE_ID_CARD_FRONT);
                break;
            case R.id.iv_id_card_back:
                Intent intent1 = new Intent(NormalMerchantSettlementActivity.this, CameraActivity.class);
                intent1.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent1.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                startActivityForResult(intent1, REQUESTCODE_ID_CARD_BACK);
                break;
            case R.id.iv_bank_card_front:
                Intent intent2 = new Intent(NormalMerchantSettlementActivity.this, CameraActivity.class);
                intent2.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent2.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent2, REQUESTCODE_BANK_CARD_FRONT);
                break;
            case R.id.iv_bank_card_back:
                Intent intent3 = new Intent(NormalMerchantSettlementActivity.this, CameraActivity.class);
                intent3.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent3.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent3, REQUESTCODE_BANK_CARD_BACK);
                break;
            case R.id.iv_open_bank:
                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_OPEN_BANK);
                break;
            case R.id.iv_authorization_letter:
                SelectImageUtils.getInsingle(this).showWindow(btnComplete, REQUESTCODE_AUTHORIZATION_LETTER);
                break;
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
                bundle.putBoolean("is_normal_merchant", true);
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
            case R.id.btn_complete:
                if (isPublicAccountSettlement) {
                    //对公结算
                    if (publicAccountName.isEmpty()) {
                        ToastUtil.show("请输入账号名");
                        return;
                    }
                    if (open_bank_load == null || open_bank_load.equals("")) {
                        ToastUtil.show("请上传开户许可证");
                        return;
                    }
                    inComing.setActTyp("00");
                    inComing.setActNm(publicAccountName.getText());
                    inComing.setOpeningAccountLicensePic(open_bank);
                    inComing.setOpeningAccountLicenseLoadPic(open_bank_load);
                } else {
                    //对私结算
                    if (isLegalPerson) {
                        inComing.setPeople_type(1);
                    } else {
                        //对私非法人结算：身份证 银行卡 授权函
                        inComing.setPeople_type(2);
                        if (id_card_front_load == null || id_card_front_load.equals("")) {
                            ToastUtil.show("请上传身份证正面");
                            return;
                        }
                        if (id_card_back_load == null || id_card_back_load.equals("")) {
                            ToastUtil.show("请上传身份证反面");
                            return;
                        }
                        //对私法人结算：银行卡
                        if (idCardName.isEmpty()) {
                            ToastUtil.show("请输入身份证姓名");
                            return;
                        }
                        if (idCardNumber.isEmpty()) {
                            ToastUtil.show("请输入身份证号码");
                            return;
                        }
                        if (!Utils.isIDCard(idCardNumber.getText())) {
                            ToastUtil.show("请输入正确的身份证号码");
                            return;
                        }
                        if (authorization_letter_load == null || authorization_letter_load.equals("")) {
                            ToastUtil.show("请上传非法人授权函");
                            return;
                        }
                        inComing.setSettlePersonIdcardPositive(id_card_front); //结算人身份证正面
                        inComing.setSettlePersonIdcardOpposite(id_card_back); //结算人身份证反面
                        inComing.setSettlePersonIdcardLoadPositive(id_card_front_load); //结算人身份证正面
                        inComing.setSettlePersonIdcardLoadOpposite(id_card_back_load); //结算人身份证反面
                        inComing.setActNm(idCardName.getText());  //身份证姓名
                        inComing.setStmManIdNo(idCardNumber.getText()); //身份证号
                        inComing.setLetterOfAuthPic(authorization_letter);
                        inComing.setLetterOfAuthPicLoadPic(authorization_letter_load);
                    }
                    if (bank_card_front_load == null || bank_card_front_load.equals("")) {
                        ToastUtil.show("请上传银行卡正面");
                        return;
                    }
//                    if (bank_card_back_load == null || bank_card_back_load.equals("")) {
//                        ToastUtil.show("请上传银行卡反面");
//                        return;
//                    }
                    inComing.setBankCardPositivePic(bank_card_front); //银行卡正面
                    inComing.setBankCardOppositePic(bank_card_back); //银行卡反面
                    inComing.setBankCardPositiveLoadPic(bank_card_front_load); //银行卡正面
                    inComing.setBankCardOppositeLoadPic(bank_card_back_load); //银行卡反面
                    inComing.setActTyp("01");
                }
                if (settlementBankId.isEmpty()) {
                    ToastUtil.show("请输入银行卡号");
                    return;
                }
                if (bank_name == null || bank_name.equals("")) {
                    ToastUtil.show("请选择开户行");
                    return;
                }
                inComing.setActNo(settlementBankId.getText()); //银行卡号
                inComing.setBankCode(bank_code);
                inComing.setBankName(bank_name);
                inComing.setBankCityCode(bank_city_code);
                inComing.setBankCityName(bank_city_name);
                inComing.setLbnkNo(bank_branch_code); //开户支行号
                inComing.setLbnkNm(bank_branch_name); //开户行
                Intent intent4 = new Intent();
                intent4.putExtra("incoming", inComing);
                setResult(RESULT_OK, intent4);
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
                case REQUESTCODE_BANK_CARD_FRONT:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_BANK_CARD_FRONT);
                    //OCR识别银行卡信息
                    mvpPresenter.ocrBankCardInfo(img_url);
                    break;
                case REQUESTCODE_BANK_CARD_BACK:
                    img_url = FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath();
                    if (img_url != null && !img_url.equals("")) {
                        img_url = ImgUtils.compressImage(img_url, 2048); //需要压缩到2M以内
                    }
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_BANK_CARD_BACK);
                    break;
                case REQUESTCODE_OPEN_BANK:
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_OPEN_BANK);
                    break;
                case REQUESTCODE_AUTHORIZATION_LETTER:
                    mvpPresenter.uploadImage(img_url, REQUESTCODE_AUTHORIZATION_LETTER);
                    break;
            }
        }
    }

    @Override
    public void uploadImageUrl(UploadImageBean data, int code, String originalImage) {
        switch (code) {
            case REQUESTCODE_ID_CARD_FRONT:
                id_card_front = data.getSrc();
                id_card_front_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivIdCardFront);
                break;
            case REQUESTCODE_ID_CARD_BACK:
                id_card_back = data.getSrc();
                id_card_back_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivIdCardBack);
                break;
            case REQUESTCODE_BANK_CARD_FRONT:
                bank_card_front = data.getSrc();
                bank_card_front_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivBankCardFront);
                break;
            case REQUESTCODE_BANK_CARD_BACK:
                bank_card_back = data.getSrc();
                bank_card_back_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivBankCardBack);
                break;
            case REQUESTCODE_OPEN_BANK:
                open_bank = data.getSrc();
                open_bank_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivOpenBank);
                break;
            case REQUESTCODE_AUTHORIZATION_LETTER:
                authorization_letter = data.getSrc();
                authorization_letter_load = data.getComplete_src();
                Glide.with(this).load(new File(originalImage)).into(ivAuthorizationLetter);
        }
    }

    @Override
    public void ocrIDCardSuccess(IDCardOcrBean data, String originImage) {
        if (data != null) {
            if (data.getCards() != null && data.getCards().size() > 0) {
                idCardName.setText(data.getCards().get(0).getName());
                idCardNumber.setText(data.getCards().get(0).getId_card_number());
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
                settlementBankId.setText(data.getBank_cards().get(0).getNumber());
            } else {
                ToastUtil.show("银行卡图片识别失败");
            }
        } else {
            ToastUtil.show("银行卡图片识别失败");
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
                if (idCardNumber.getText().length() < 17) {
                    if ("x".equals(String.valueOf(source.charAt(i))) || "X".equals(String.valueOf(source.charAt(i)))) {
                        return "";
                    }
                }
            }
            return null;
        }
    };
}
