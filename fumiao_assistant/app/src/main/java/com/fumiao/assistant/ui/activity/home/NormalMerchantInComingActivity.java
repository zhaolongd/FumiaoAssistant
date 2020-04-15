package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.InComingDetailBean;
import com.fumiao.assistant.bean.home.InComingResultBean;
import com.fumiao.assistant.mvp.home.NormalMerchantInComingPresenter;
import com.fumiao.assistant.mvp.home.NormalMerchantInComingView;
import com.fumiao.assistant.tools.DBManager;
import com.fumiao.assistant.ui.dialog.RejectDialog;
import com.fumiao.core.uitls.AppManager;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.core.uitls.ToastUtil;
import butterknife.BindView;
import butterknife.OnClick;

public class NormalMerchantInComingActivity extends MvpActivity<NormalMerchantInComingPresenter> implements NormalMerchantInComingView {

    @BindView(R.id.tv_business_info)
    TextView tvBusinessInfo;
    @BindView(R.id.iv_business_check)
    ImageView ivBusinessCheck;
    @BindView(R.id.rl_business_info)
    RelativeLayout rlBusinessInfo;
    @BindView(R.id.tv_merchant_info)
    TextView tvMerchantInfo;
    @BindView(R.id.iv_merchant_check)
    ImageView ivMerchantCheck;
    @BindView(R.id.rl_merchant_info)
    RelativeLayout rlMerchantInfo;
    @BindView(R.id.tv_settlement_info)
    TextView tvSettlementInfo;
    @BindView(R.id.iv_settlement_check)
    ImageView ivSettlementCheck;
    @BindView(R.id.rl_settlement_info)
    RelativeLayout rlSettlementInfo;
    @BindView(R.id.tv_rate_info)
    TextView tvRateInfo;
    @BindView(R.id.iv_rate_check)
    ImageView ivRateCheck;
    @BindView(R.id.rl_rate_info)
    RelativeLayout rlRateInfo;
    String phone;
    @BindView(R.id.ll_reject_tips)
    LinearLayout llRejectTips;
    @BindView(R.id.btn_save_draft)
    Button btnSaveDraft;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private boolean isBusinessComplete = false;
    private boolean isMerchantComplete = false;
    private boolean isSettlementComplete = false;
//    private boolean isRateComplete = false;
    final int REQUESTCODE_BUSINESS_INCOMINGS = 201; //工商信息页面传递进件数据
    final int REQUESTCODE_MERCHANT_INCOMINGS = 202; //商户信息页面传递进件数据
    final int REQUESTCODE_SETTLEMENT_INCOMINGS = 203; //结算信息页面传递进件数据
//    final int REQUESTCODE_RATE_INCOMINGS = 204; //费率信息页面传递进件数据
    private InComing inComing;
    private String merchant_id;  //进件驳回传入
    private boolean isRejectInComing = false;
    RejectDialog mRejectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_marchant_incoming);
        init();
    }

    @Override
    protected NormalMerchantInComingPresenter createPresenter() {
        return new NormalMerchantInComingPresenter(this, this);
    }

    private void init() {
        setTitle("商户进件");
        mRejectDialog = new RejectDialog(this);
        phone = getIntent().getStringExtra("phone");
        inComing = (InComing) getIntent().getSerializableExtra("incoming");
        merchant_id = getIntent().getStringExtra("merchant_id");
        if (inComing == null) {
            inComing = new InComing();
        }else {
            isBusinessComplete = inComing.getIsBusinessComplete();
            isMerchantComplete = inComing.getIsMerchantComplete();
            isSettlementComplete = inComing.getIsSettlementComplete();
//            isRateComplete = inComing.getIsRateComplete();
            updateCompleteView();
        }
        if (merchant_id != null && !merchant_id.equals("")) {
            isRejectInComing = true;
            mvpPresenter.getInComingDetail(merchant_id);
        }
        if (isRejectInComing) {
            llRejectTips.setVisibility(View.VISIBLE);
            btnSaveDraft.setVisibility(View.GONE);
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
        }

    }

    @OnClick({R.id.rl_business_info, R.id.rl_merchant_info, R.id.rl_settlement_info, R.id.rl_rate_info, R.id.btn_save_draft, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_business_info:
                Bundle bundle = new Bundle();
                bundle.putSerializable("incoming", inComing);
                jumpActivityForResult(NormalMerchantBusinessActivity.class, bundle, REQUESTCODE_BUSINESS_INCOMINGS);
                break;
            case R.id.rl_merchant_info:
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("incoming", inComing);
                jumpActivityForResult(NormalMerchantActivity.class, bundle1, REQUESTCODE_MERCHANT_INCOMINGS);
                break;
            case R.id.rl_settlement_info:
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("incoming", inComing);
                jumpActivityForResult(NormalMerchantSettlementActivity.class, bundle2, REQUESTCODE_SETTLEMENT_INCOMINGS);
                break;
            case R.id.rl_rate_info:
//                Bundle bundle3 = new Bundle();
//                bundle3.putSerializable("incoming", inComing);
//                jumpActivityForResult(NormalMerchantRateActivity.class, bundle3, REQUESTCODE_RATE_INCOMINGS);
                break;
            case R.id.btn_save_draft:
                saveInComingToDB();
                break;
            case R.id.btn_submit:
                if (isBusinessComplete && isMerchantComplete && isSettlementComplete) {
                    if (phone != null && !phone.equals("")) {
                        inComing.setMblNo(phone);
                    }
                    if(isRejectInComing){
                        mvpPresenter.rejectNormalMerchantIncoming(inComing);
                    }else {
                        mvpPresenter.normalMerchantIncoming(inComing);
                    }
                } else {
                    ToastUtil.show("请提交完进件信息");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUESTCODE_BUSINESS_INCOMINGS:
                    inComing = (InComing) data.getSerializableExtra("incoming");
                    isBusinessComplete = true;
                    updateCompleteView();
                    break;
                case REQUESTCODE_MERCHANT_INCOMINGS:
                    inComing = (InComing) data.getSerializableExtra("incoming");
                    isMerchantComplete = true;
                    updateCompleteView();
                    break;
                case REQUESTCODE_SETTLEMENT_INCOMINGS:
                    inComing = (InComing) data.getSerializableExtra("incoming");
                    isSettlementComplete = true;
                    updateCompleteView();
                    break;
//                case REQUESTCODE_RATE_INCOMINGS:
//                    inComing = (InComing) data.getSerializableExtra("incoming");
//                    isRateComplete = true;
//                    updateCompleteView();
//                    break;
            }
        }
    }

    /**
     * 保存进行数据到数据库
     */
    private void saveInComingToDB() {
        if (inComing == null) {
            return;
        }
        if (phone != null && !phone.equals("")) {
            inComing.setId(Long.valueOf(phone)); //手机号作主键id
            inComing.setMblNo(phone);
        }
        inComing.setIsBusinessComplete(isBusinessComplete);
        inComing.setIsMerchantComplete(isMerchantComplete);
        inComing.setIsSettlementComplete(isSettlementComplete);
//        inComing.setIsRateComplete(isRateComplete);
        inComing.setMerchant_type("2");
        inComing.setCreateTime(DateUtils.getStringDate());
        DBManager.getInstance(NormalMerchantInComingActivity.this).insertInComing(inComing);
        ToastUtil.show("保存成功");
    }

    private void updateCompleteView() {
        if (isBusinessComplete) {
            tvBusinessInfo.setTextColor(getResources().getColor(R.color.colorAccent));
            rlBusinessInfo.setBackgroundResource(R.drawable.round_select2_bg);
            ivBusinessCheck.setVisibility(View.VISIBLE);
        } else {
            tvBusinessInfo.setTextColor(getResources().getColor(R.color.font_des));
            rlBusinessInfo.setBackgroundResource(R.drawable.round_gray2_bg);
            ivBusinessCheck.setVisibility(View.INVISIBLE);
        }
        if (isMerchantComplete) {
            tvMerchantInfo.setTextColor(getResources().getColor(R.color.colorAccent));
            rlMerchantInfo.setBackgroundResource(R.drawable.round_select2_bg);
            ivMerchantCheck.setVisibility(View.VISIBLE);
        } else {
            tvMerchantInfo.setTextColor(getResources().getColor(R.color.font_des));
            rlMerchantInfo.setBackgroundResource(R.drawable.round_gray2_bg);
            ivMerchantCheck.setVisibility(View.INVISIBLE);
        }

        if (isSettlementComplete) {
            tvSettlementInfo.setTextColor(getResources().getColor(R.color.colorAccent));
            rlSettlementInfo.setBackgroundResource(R.drawable.round_select2_bg);
            ivSettlementCheck.setVisibility(View.VISIBLE);
        } else {
            tvSettlementInfo.setTextColor(getResources().getColor(R.color.font_des));
            rlSettlementInfo.setBackgroundResource(R.drawable.round_gray2_bg);
            ivSettlementCheck.setVisibility(View.INVISIBLE);
        }

//        if (isRateComplete) {
//            tvRateInfo.setTextColor(getResources().getColor(R.color.colorAccent));
//            rlRateInfo.setBackgroundResource(R.drawable.round_select2_bg);
//            ivRateCheck.setVisibility(View.VISIBLE);
//        } else {
//            tvRateInfo.setTextColor(getResources().getColor(R.color.font_des));
//            rlRateInfo.setBackgroundResource(R.drawable.round_gray2_bg);
//            ivRateCheck.setVisibility(View.INVISIBLE);
//        }

        if (isBusinessComplete && isMerchantComplete && isSettlementComplete) {
            btnSubmit.setBackgroundResource(R.drawable.btn_theme_bg);
        }else {
            btnSubmit.setBackgroundResource(R.drawable.btn_gray_bg);
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
            inComing.setWxQrcodeList(data.getParts_info().getContent().getWxQrcodeList());
            inComing.setAliQrcodeList(data.getParts_info().getContent().getAliQrcodeList());
            inComing.setThousandQrcodeList(data.getParts_info().getContent().getThousandQrcodeList());
            inComing.setHighestQrcodeList(data.getParts_info().getContent().getHighestQrcodeList());
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
            inComing.setBankCardPositivePic(data.getParts_info().getContent().getBankCardPositivePic()); //银行卡正面
            inComing.setBankCardOppositePic(data.getParts_info().getContent().getBankCardOppositePic()); //银行卡反面
            inComing.setBankCardPositiveLoadPic(data.getParts_info().getBankCardPositivePic());
            inComing.setBankCardOppositeLoadPic(data.getParts_info().getBankCardOppositePic());
            inComing.setStoreLoadPic(data.getParts_info().getStorePic());
            inComing.setBusinessPlaceLoadPic(data.getParts_info().getBusinessPlacePic());
            inComing.setInsideSceneLoadPic(data.getParts_info().getInsideScenePic());
            inComing.setFailure_msg(data.getParts_info().getFailure_msg());
            //工商信息
            inComing.setLicensePic(data.getParts_info().getContent().getLicensePic());
            inComing.setLicenseLoadPic(data.getParts_info().getLicensePic());
            inComing.setCprRegNmCn(data.getParts_info().getContent().getCprRegNmCn());
            inComing.setRegistCode(data.getParts_info().getContent().getRegistCode());
            inComing.setMccCd(data.getParts_info().getContent().getMccCd());
            inComing.setMcc_name(data.getParts_info().getContent().getMcc_name());
            inComing.setLegalPersonidPositivePic(data.getParts_info().getContent().getLegalPersonidPositivePic()); //结算人身份证正面
            inComing.setLegalPersonidOppositePic(data.getParts_info().getContent().getLegalPersonidOppositePic()); //结算人身份证反面
            inComing.setLegalPersonidPositiveLoadPic(data.getParts_info().getLegalPersonidPositivePic());
            inComing.setLegalPersonidOppositeLoadPic(data.getParts_info().getLegalPersonidOppositePic());
            inComing.setIdentityName(data.getParts_info().getContent().getIdentityName());
            inComing.setIdentityNo(data.getParts_info().getContent().getIdentityNo());
            //商户信息
            inComing.setHaveLicenceNo(data.getParts_info().getContent().getHaveLicenseNo());
            //结算信息
            inComing.setSettleType(data.getParts_info().getContent().getSettleType());
            inComing.setActTyp(data.getParts_info().getContent().getActTyp());
            inComing.setPeople_type(data.getParts_info().getContent().getPeople_type());
            inComing.setOpeningAccountLicensePic(data.getParts_info().getContent().getOpeningAccountLicensePic());
            inComing.setOpeningAccountLicenseLoadPic(data.getParts_info().getOpeningAccountLicensePic());
            inComing.setSettlePersonIdcardPositive(data.getParts_info().getContent().getSettlePersonIdcardPositive());
            inComing.setSettlePersonIdcardLoadPositive(data.getParts_info().getSettlePersonIdcardPositive());
            inComing.setSettlePersonIdcardOpposite(data.getParts_info().getContent().getSettlePersonIdcardOpposite());
            inComing.setSettlePersonIdcardLoadOpposite(data.getParts_info().getSettlePersonIdcardOpposite());
            inComing.setLetterOfAuthPic(data.getParts_info().getContent().getLetterOfAuthPic());
            inComing.setLetterOfAuthPicLoadPic(data.getParts_info().getLetterOfAuthPic());
            isBusinessComplete = true;
            isMerchantComplete = true;
            isSettlementComplete = true;
//            isRateComplete = true;
            updateCompleteView();
        }
    }

    @Override
    public void inComingSuccess(InComingResultBean data) {
        ToastUtil.show(data.getMsg());
        if (inComing != null) {
            DBManager.getInstance(this).deleteInComing(inComing);
        }
        AppManager.getAppManager().backToTag(MAIN_PAGE);
        jumpActivity(MerchantAuditActivity.class);
    }

    @Override
    public void rejectInComingSuccess(InComingResultBean data) {
        ToastUtil.show(data.getMsg());
        AppManager.getAppManager().backToTag(MAIN_PAGE);
        jumpActivity(MerchantAuditActivity.class);
    }
}
