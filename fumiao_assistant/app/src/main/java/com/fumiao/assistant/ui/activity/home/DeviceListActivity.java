package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.DeviceBean;
import com.fumiao.assistant.bean.home.DeviceListBean;
import com.fumiao.assistant.mvp.home.DeviceListPresenter;
import com.fumiao.assistant.mvp.home.DeviceListView;
import com.fumiao.assistant.ui.adapter.DeviceAdapter;
import com.fumiao.assistant.widget.SpacesItemDecoration;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class DeviceListActivity extends MvpActivity<DeviceListPresenter> implements DeviceListView {

    @BindView(R.id.device_recy)
    RecyclerView deviceRecy;
    @BindView(R.id.refresh_Layout)
    SmartRefreshLayout refreshLayout;
    private DeviceAdapter deviceAdapter;
    private List<DeviceBean> deviceData;
    private String merchant_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list, false);
        init();
    }

    private void init() {
        setTitle("终端列表");
        merchant_id = getIntent().getStringExtra("merchant_id");
        deviceData = new ArrayList<>();
        deviceAdapter = new DeviceAdapter(this, R.layout.item_device_list, deviceData);
        deviceRecy.setLayoutManager(new LinearLayoutManager(this));
        deviceRecy.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(this, 10), Utils.dip2px(this, 10),  0, 0));
        deviceRecy.setAdapter(deviceAdapter);
        refreshLayout.setEnableLoadMore(false);
        deviceAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                DeviceBean dataBean = deviceData.get(position);
                if(dataBean.getDevice_type() == 1){
                    Bundle bundle = new Bundle();
                    bundle.putString("sn", dataBean.getDevice_sn());
                    jumpActivity(DeviceDetailActivity.class, bundle);
                }
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getDeviceList(merchant_id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.getDeviceList(merchant_id);
    }

    @Override
    protected DeviceListPresenter createPresenter() {
        return new DeviceListPresenter(this, this);
    }

    @Override
    public void showDeviceList(DeviceListBean data) {
        refreshLayout.finishRefresh(1000);  //刷新完成
        if(data != null){
            if(data.getMerchant_device() != null){
                deviceData.clear();
                deviceData.addAll(data.getMerchant_device());
            }
            if (deviceData.size() == 0) {
                showEmpty(ContextCompat.getDrawable(this, R.mipmap.device_list_null), "暂无设备哦");
            }else {
                hideEmpty();
            }
            deviceAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void stateChangeOnError() {
        switch (refreshLayout.getState()) {
            case Refreshing:
                refreshLayout.finishRefresh(false);
                break;
//            case Loading:
//                refreshLayout.finishLoadMore(false);
//                break;
            default:
                break;
        }
    }
}
