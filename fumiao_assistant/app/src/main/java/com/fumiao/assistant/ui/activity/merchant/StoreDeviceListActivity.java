package com.fumiao.assistant.ui.activity.merchant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.DeviceBean;
import com.fumiao.assistant.bean.merchant.StoreDetailBean;
import com.fumiao.assistant.mvp.merchant.StoreDeviceListPresenter;
import com.fumiao.assistant.mvp.merchant.StoreDeviceListView;
import com.fumiao.assistant.ui.activity.home.DeviceDetailActivity;
import com.fumiao.assistant.ui.adapter.DeviceAdapter;
import com.fumiao.assistant.ui.dialog.DeviceAddDialog;
import com.fumiao.assistant.widget.SpacesItemDecoration;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class StoreDeviceListActivity extends MvpActivity<StoreDeviceListPresenter> implements StoreDeviceListView {

    @BindView(R.id.device_recy)
    RecyclerView deviceRecy;
    @BindView(R.id.refresh_Layout)
    SmartRefreshLayout refreshLayout;
    DeviceAddDialog deviceAddDialog;
    private String store_id;
    private StoreDetailBean storeDetail;
    private DeviceAdapter deviceAdapter;
    private List<DeviceBean> deviceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_device_list, false);
        init();
    }

    private void init() {
        setTitle("终端列表");
        store_id = getIntent().getStringExtra("store_id");
        storeDetail = (StoreDetailBean) getIntent().getSerializableExtra("store_detail");
        deviceAddDialog = new DeviceAddDialog(this);
        deviceAddDialog.setCanceledOnTouchOutside(true);
        setRight("新增设备", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceAddDialog.show(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("device_type", 1);
                        bundle.putString("store_id", store_id);
                        bundle.putSerializable("store_detail", storeDetail);
                        jumpActivity(DeviceAddActivity.class, bundle);
                        deviceAddDialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("device_type", 2);
                        bundle.putString("store_id", store_id);
                        bundle.putSerializable("store_detail", storeDetail);
                        jumpActivity(DeviceAddActivity.class, bundle);
                        deviceAddDialog.dismiss();
                    }
                });
            }
        });
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
                mvpPresenter.getStoreDeviceList(store_id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.getStoreDeviceList(store_id);
    }

    @Override
    protected StoreDeviceListPresenter createPresenter() {
        return new StoreDeviceListPresenter(this, this);
    }

    @Override
    public void showDeviceList(List<DeviceBean> data) {
        refreshLayout.finishRefresh(1000);  //刷新完成
        if(data != null){
            deviceData.clear();
            deviceData.addAll(data);
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
            default:
                break;
        }
    }
}
