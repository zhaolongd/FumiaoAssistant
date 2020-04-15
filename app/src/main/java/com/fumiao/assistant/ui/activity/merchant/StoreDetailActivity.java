package com.fumiao.assistant.ui.activity.merchant;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.merchant.StoreDetailBean;
import com.fumiao.assistant.mvp.merchant.StoreDetailPresenter;
import com.fumiao.assistant.mvp.merchant.StoreDetailView;
import butterknife.BindView;
import butterknife.OnClick;

public class StoreDetailActivity extends MvpActivity<StoreDetailPresenter> implements StoreDetailView {

    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @BindView(R.id.tv_merchant_number)
    TextView tvMerchantNumber;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store_number)
    TextView tvStoreNumber;
    @BindView(R.id.tv_store_status)
    TextView tvStoreStatus;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_contacts)
    TextView tvContacts;
    @BindView(R.id.tv_telephone_number)
    TextView tvTelephoneNumber;
    @BindView(R.id.tv_store_area)
    TextView tvStoreArea;
    @BindView(R.id.tv_store_addr)
    TextView tvStoreAddr;
    @BindView(R.id.tv_salesman)
    TextView tvSalesman;
    @BindView(R.id.rl_device_list)
    RelativeLayout rlDeviceList;
    private String store_id;
    private StoreDetailBean storeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        init();
    }

    private void init() {
        setTitle("门店信息");
        store_id = getIntent().getStringExtra("store_id");
        mvpPresenter.getStoreDetail(store_id);
    }

    @Override
    protected StoreDetailPresenter createPresenter() {
        return new StoreDetailPresenter(this, this);
    }

    @OnClick(R.id.rl_device_list)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString("store_id", store_id);
        bundle.putSerializable("store_detail", storeDetail);
        jumpActivity(StoreDeviceListActivity.class, bundle);
    }

    @Override
    public void showStoreDetail(StoreDetailBean data) {
        if(data != null){
            storeDetail = data;
            tvMerchantName.setText(data.getMerchant_name());
            tvMerchantNumber.setText(data.getMerchant_id());
            tvStoreName.setText(data.getName());
            tvStoreNumber.setText(data.getSerial_number());
            tvStoreStatus.setText(data.getStatus_name());
            tvCreateTime.setText(data.getCreate_time());
            tvContacts.setText(data.getRealname());
            tvTelephoneNumber.setText(data.getPhone());
            tvStoreArea.setText(data.getPca());
            tvStoreAddr.setText(data.getAddress());
            tvSalesman.setText(data.getSalesman_name());
        }
    }
}
