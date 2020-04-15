package com.fumiao.assistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpFragment;
import com.fumiao.assistant.bean.data.MerchantStatisticsListBean;
import com.fumiao.assistant.bean.data.MerchantStoreStatisticsBean;
import com.fumiao.assistant.bean.data.StoreStatisticsListBean;
import com.fumiao.assistant.mvp.data.MerchantStoreListPresenter;
import com.fumiao.assistant.mvp.data.MerchantStoreListView;
import com.fumiao.assistant.ui.adapter.MerchantStoreStatisticsAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/13 0013 15:30
 */
public class MerchantStoreListFragment extends MvpFragment<MerchantStoreListPresenter> implements MerchantStoreListView {

    Unbinder unbinder;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;

    private List<MerchantStoreStatisticsBean> mData;
    private MerchantStoreStatisticsAdapter mAdapter;
    private int statistics_flag = 1; // 1:商户，2:门店
    private String account_id;

    int pagesize = 15;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_merchant_store_list, container, false);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        statistics_flag = getArguments().getInt("statistics_flag", 1);
        account_id = getArguments().getString("account_id");
        mData = new ArrayList<>();
        mAdapter = new MerchantStoreStatisticsAdapter(getActivity(), R.layout.item_merchant_store_statistics, mData);
        rvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvList.setAdapter(mAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        srlRefresh.setEnableLoadMore(true);
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                srlRefresh.setEnableLoadMore(true);
                if (statistics_flag == 1){
                    mvpPresenter.getMerchantStatistics(account_id, pagesize, page);
                }else {
                    mvpPresenter.getStoreStatistics(account_id, pagesize, page);
                }
            }
        });

        srlRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (statistics_flag == 1){
                    mvpPresenter.getMerchantStatistics(account_id, pagesize, page);
                }else {
                    mvpPresenter.getStoreStatistics(account_id, pagesize, page);
                }
            }
        });
        if (statistics_flag == 1){
            mvpPresenter.getMerchantStatistics(account_id, pagesize, page);
        }else {
            mvpPresenter.getStoreStatistics(account_id, pagesize, page);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected MerchantStoreListPresenter createPresenter() {
        return new MerchantStoreListPresenter(this, getActivity());
    }

    @Override
    public void showMerchantStatisticsList(MerchantStatisticsListBean data) {
        srlRefresh.finishRefresh();
        if (data != null) {
            if(page == 1){
                mData.clear();
                srlRefresh.finishRefresh(1000);  //刷新完成
            }else {
                srlRefresh.finishLoadMore(true);//加载完成
            }
            if (data.getMerchant_list().size() < pagesize){
                srlRefresh.setEnableLoadMore(false);
            }
            mData.addAll(data.getMerchant_list());

            if (mData.size() == 0) {
                showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.list_null), "暂无数据");
            }else {
                hideEmpty();
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showStoreStatisticsList(StoreStatisticsListBean data) {
        srlRefresh.finishRefresh();
        if (data != null) {
            if(page == 1){
                mData.clear();
                srlRefresh.finishRefresh(1000);  //刷新完成
            }else {
                srlRefresh.finishLoadMore(true);//加载完成
            }
            if (data.getStore_list().size() < pagesize){
                srlRefresh.setEnableLoadMore(false);
            }
            mData.addAll(data.getStore_list());

            if (mData.size() == 0) {
                showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.list_null), "暂无数据");
            }else {
                hideEmpty();
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    public void update(int type) {
        statistics_flag = type;
        page = 1;
        srlRefresh.setEnableLoadMore(true);
        if (statistics_flag == 1){
            mvpPresenter.getMerchantStatistics(account_id, pagesize, page);
        }else {
            mvpPresenter.getStoreStatistics(account_id, pagesize, page);
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
