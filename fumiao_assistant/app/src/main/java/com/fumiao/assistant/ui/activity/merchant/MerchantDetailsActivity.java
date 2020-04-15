package com.fumiao.assistant.ui.activity.merchant;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.merchant.MerchantDetailBean;
import com.fumiao.assistant.mvp.merchant.MerchantDetailPresenter;
import com.fumiao.assistant.mvp.merchant.MerchantDetailView;
import com.fumiao.assistant.ui.activity.WebViewActivity;
import com.fumiao.assistant.ui.activity.home.DeviceListActivity;
import butterknife.BindView;
import butterknife.OnClick;


public class MerchantDetailsActivity extends MvpActivity<MerchantDetailPresenter> implements MerchantDetailView {

    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @BindView(R.id.tv_merchant_number)
    TextView tvMerchantNumber;
    @BindView(R.id.tv_legal_person_name)
    TextView tvLegalPersonName;
    @BindView(R.id.tv_legal_person_phone)
    TextView tvLegalPersonPhone;
    @BindView(R.id.tv_merchant_status)
    TextView tvMerchantStatus;
    @BindView(R.id.tv_merchant_type)
    TextView tvMerchantType;
    @BindView(R.id.tv_merchant_area)
    TextView tvMerchantArea;
    @BindView(R.id.tv_merchant_addr)
    TextView tvMerchantAddr;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_salesman)
    TextView tvSalesman;
    @BindView(R.id.tv_organization)
    TextView tvOrganization;
    @BindView(R.id.tv_settlement_type)
    TextView tvSettlementType;
    @BindView(R.id.tv_settlement_name)
    TextView tvSettlementName;
    @BindView(R.id.tv_settlement_account)
    TextView tvSettlementAccount;
    @BindView(R.id.tv_no_working_settlement)
    TextView tvNoWorkingSettlement;
    @BindView(R.id.rl_store_list)
    RelativeLayout rlStoreList;
    @BindView(R.id.rl_activity_list)
    RelativeLayout rlActivityList;
    @BindView(R.id.rl_device_list)
    RelativeLayout rlDeviceList;
    @BindView(R.id.iv_settlement_arrow)
    ImageView ivSettlementArrow;
    @BindView(R.id.rl_settlement_title)
    RelativeLayout rlSettlementTitle;
    @BindView(R.id.ll_settlement_info)
    LinearLayout llSettlementInfo;
    //费率是否显示和隐藏标识
    private boolean isShowSettlement = false;
    private String merchant_id;
    private String merchant_phone;
    private String id; //加密的商户id
    private String user_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_details);
        init();
    }

    @Override
    protected MerchantDetailPresenter createPresenter() {
        return new MerchantDetailPresenter(this, this);
    }

    private void init() {
        setTitle("商户详情");
        merchant_id = getIntent().getStringExtra("merchant_id");
        mvpPresenter.getMerchantDetail(merchant_id);
    }

    @Override
    public void showMerchantDetail(MerchantDetailBean data) {
        if (data != null) {
            id = data.getPwd_merchant_id();
            user_info = data.getUser_info();
            merchant_phone = data.getPhone();
            tvMerchantNumber.setText(data.getMerchant_id());
            tvMerchantName.setText(data.getName());
            tvCreateTime.setText(data.getCreate_time());
            tvMerchantStatus.setText(data.getStatus_name());
            tvMerchantType.setText(data.getMerchant_type_name());
            tvMerchantArea.setText(data.getPca());
            tvMerchantAddr.setText(data.getAddress());
            tvLegalPersonName.setText(data.getActNm());
            tvLegalPersonPhone.setText(data.getPhone());
            tvSettlementAccount.setText(data.getActNo());
            tvSettlementName.setText(data.getActNm());
            tvSalesman.setText(data.getSalesman_name());
            tvNoWorkingSettlement.setText(data.getSettleType_name());
            tvSettlementType.setText(data.getActTyp_name());
        }
    }

    @OnClick({R.id.rl_activity_list, R.id.rl_device_list, R.id.rl_store_list, R.id.rl_settlement_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_activity_list:
                Bundle bundle = new Bundle();
                bundle.putString("WEB_TITLE", "活动列表");
                bundle.putString("WEB_URL", LOWE_RATE_ACTIVITY + id + "&user_info=" + user_info);
                jumpActivity(WebViewActivity.class, bundle);
                break;
            case R.id.rl_device_list:
                Bundle bundle1 = new Bundle();
                bundle1.putString("merchant_id", merchant_id);
                jumpActivity(DeviceListActivity.class, bundle1);
                break;
            case R.id.rl_store_list:
                Bundle bundle2 = new Bundle();
                bundle2.putString("merchant_id", merchant_id);
                bundle2.putString("merchant_phone", merchant_phone);
                jumpActivity(StoreListActivity.class, bundle2);
                break;
            case R.id.rl_settlement_title:
                isShowSettlement = isShowSettlement ? false : true;
                if(isShowSettlement){
                    llSettlementInfo.setVisibility(View.VISIBLE);
                    ivSettlementArrow.setImageResource(R.mipmap.ic_arrow_down);
                }else {
                    llSettlementInfo.setVisibility(View.GONE);
                    ivSettlementArrow.setImageResource(R.mipmap.ic_arrow_up);
                }
                break;
        }
    }
}
