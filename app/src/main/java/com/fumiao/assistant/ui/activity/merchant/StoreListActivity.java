package com.fumiao.assistant.ui.activity.merchant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.merchant.StoreBean;
import com.fumiao.assistant.bean.merchant.StoreListBean;
import com.fumiao.assistant.mvp.merchant.StoreListPresenter;
import com.fumiao.assistant.mvp.merchant.StoreListView;
import com.fumiao.assistant.ui.adapter.StoreListAdapter;
import com.fumiao.assistant.widget.SpacesItemDecoration;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class StoreListActivity extends MvpActivity<StoreListPresenter> implements StoreListView {

    @BindView(R.id.rv_store_list)
    RecyclerView rvStoreList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    private List<StoreBean> storeData;
    private StoreListAdapter storeListAdapter;
    private String merchant_id;
    private String merchant_phone; //商户的手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list, false);
        init();
    }

    private void init() {
        merchant_id = getIntent().getStringExtra("merchant_id");
        merchant_phone = getIntent().getStringExtra("merchant_phone");
        setTitle("门店列表");
        setRight("新增门店", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("merchant_phone", merchant_phone);
                bundle.putString("merchant_id", merchant_id);
                jumpActivity(StoreAddActivity.class, bundle);
            }
        });
        storeData = new ArrayList<>();
        storeListAdapter = new StoreListAdapter(this, R.layout.item_store, storeData);
        rvStoreList.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(this, 15), Utils.dip2px(this, 15),  Utils.dip2px(this, 0), Utils.dip2px(this, 0)));
        rvStoreList.setAdapter(storeListAdapter);
        rvStoreList.setLayoutManager(new LinearLayoutManager(this));
        srlRefresh.setEnableLoadMore(false);
        storeListAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                StoreBean storeBean = storeData.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("store_id", storeBean.getId());
                jumpActivity(StoreDetailActivity.class, bundle);
            }
        });
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getMerchantStoreList(merchant_id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.getMerchantStoreList(merchant_id);
    }

    @Override
    protected StoreListPresenter createPresenter() {
        return new StoreListPresenter(this, this);
    }

    @Override
    public void showStoreList(StoreListBean data) {
        srlRefresh.finishRefresh(1000);  //刷新完成
        if(data != null){
            storeData.clear();
            storeData.addAll(data.getData());
            if (storeData.size() == 0){
                showEmpty(ContextCompat.getDrawable(this, R.mipmap.ic_merchant_list_null), "暂无门店哦");
            }else {
                hideEmpty();
            }
            storeListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void stateChangeOnError() {
        switch (srlRefresh.getState()) {
            case Refreshing:
                srlRefresh.finishRefresh(false);
                break;
            default:
                break;
        }
    }
}
