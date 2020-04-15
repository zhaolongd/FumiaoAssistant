package com.fumiao.assistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpFragment;
import com.fumiao.assistant.bean.merchant.StoreBean;
import com.fumiao.assistant.bean.merchant.StoreListBean;
import com.fumiao.assistant.mvp.merchant.StoreListPresenter;
import com.fumiao.assistant.mvp.merchant.StoreListView;
import com.fumiao.assistant.ui.activity.merchant.StoreDetailActivity;
import com.fumiao.assistant.ui.adapter.StoreListAdapter;
import com.fumiao.assistant.widget.SpacesItemDecoration;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StoreListFragment extends MvpFragment<StoreListPresenter> implements StoreListView {
    Unbinder unbinder;
    @BindView(R.id.rv_store_list)
    RecyclerView rvStoreList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    private String keyWord = "";
    private List<StoreBean> storeData;
    private StoreListAdapter storeListAdapter;
    int pagesize = 10;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_store_list, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        storeData = new ArrayList<>();
        storeListAdapter = new StoreListAdapter(getActivity(), R.layout.item_store, storeData);
        rvStoreList.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(getActivity(), 15), Utils.dip2px(getActivity(), 15),  Utils.dip2px(getActivity(), 0), Utils.dip2px(getActivity(), 0)));
        rvStoreList.setAdapter(storeListAdapter);
        rvStoreList.setLayoutManager(new LinearLayoutManager(getActivity()));
        storeListAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                StoreBean storeBean = storeData.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("store_id", storeBean.getId());
                jumpActivity(StoreDetailActivity.class, bundle);
            }
        });
        srlRefresh.setEnableLoadMore(true);
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                srlRefresh.setEnableLoadMore(true);
                mvpPresenter.getStoreList(keyWord, page, pagesize);
            }
        });
        srlRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mvpPresenter.getStoreList(keyWord, page, pagesize);
            }
        });
        mvpPresenter.getStoreList("", page, pagesize);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected StoreListPresenter createPresenter() {
        return new StoreListPresenter(this, getActivity());
    }

    @Override
    public void showStoreList(StoreListBean data) {
        if(data != null){
            if(page == 1){
                storeData.clear();
                srlRefresh.finishRefresh(1000);  //刷新完成
            }else {
                srlRefresh.finishLoadMore(true);//加载完成
            }
            if (data.getData().size() < pagesize){
                srlRefresh.setEnableLoadMore(false);
            }
            storeData.addAll(data.getData());
            if (storeData.size() == 0){
                if(keyWord.equals("")){
                    showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_merchant_list_null), "暂无门店哦");
                }else {
                    showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_merchant_list_null), "门店不存在哦");
                }
            }else {
                hideEmpty();
            }
            storeListAdapter.notifyDataSetChanged();
        }
    }

    public void updateSearch(String keyWord){
        this.keyWord = keyWord;
        page = 1;
        srlRefresh.setEnableLoadMore(true);
        mvpPresenter.getStoreList(keyWord, page, pagesize);
    }

    @Override
    public void stateChangeOnError() {
        switch (srlRefresh.getState()) {
            case Refreshing:
                srlRefresh.finishRefresh(false);
                break;
            case Loading:
                srlRefresh.finishLoadMore(false);
                break;
            default:
                break;
        }
    }
}
