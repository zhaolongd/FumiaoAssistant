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
import com.fumiao.assistant.bean.merchant.MerchantBean;
import com.fumiao.assistant.bean.merchant.MerchantListBean;
import com.fumiao.assistant.mvp.merchant.MerchantListPresenter;
import com.fumiao.assistant.mvp.merchant.MerchantListView;
import com.fumiao.assistant.ui.activity.merchant.MerchantDetailsActivity;
import com.fumiao.assistant.ui.adapter.MerchantListAdapter;
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

public class MerchantListFragment extends MvpFragment<MerchantListPresenter> implements MerchantListView {
    Unbinder unbinder;
    @BindView(R.id.rv_merchant_list)
    RecyclerView rvMerchantList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    private String keyWord = "";
    private List<MerchantBean> merchantData;
    private MerchantListAdapter merchantListAdapter;
    int pagesize = 10;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_merchant_list, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        merchantData = new ArrayList<>();
        merchantListAdapter = new MerchantListAdapter(getActivity(), R.layout.item_merchant2, merchantData);
        rvMerchantList.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(getActivity(), 15), Utils.dip2px(getActivity(), 15),  Utils.dip2px(getActivity(), 0), Utils.dip2px(getActivity(), 0)));
        rvMerchantList.setAdapter(merchantListAdapter);
        rvMerchantList.setLayoutManager(new LinearLayoutManager(getActivity()));
        merchantListAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                MerchantBean merchantBean = merchantData.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("merchant_id", merchantBean.getMerchant_id());
                jumpActivity(MerchantDetailsActivity.class, bundle);
            }
        });
        srlRefresh.setEnableLoadMore(true);
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                srlRefresh.setEnableLoadMore(true);
                mvpPresenter.getMerchantList(keyWord, page, pagesize);
            }
        });
        srlRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mvpPresenter.getMerchantList(keyWord, page, pagesize);
            }
        });
        mvpPresenter.getMerchantList("", page, pagesize);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected MerchantListPresenter createPresenter() {
        return new MerchantListPresenter(this, getActivity());
    }

    public void updateSearch(String keyWord){
        this.keyWord = keyWord;
        page = 1;
        srlRefresh.setEnableLoadMore(true);
        mvpPresenter.getMerchantList(keyWord, page, pagesize);
    }

    @Override
    public void showMerchantList(MerchantListBean data) {
        if(data != null){
            if(page == 1){
                merchantData.clear();
                srlRefresh.finishRefresh(1000);  //刷新完成
            }else {
                srlRefresh.finishLoadMore(true);//加载完成
            }
            if (data.getData().size() < pagesize){
                srlRefresh.setEnableLoadMore(false);
            }
            merchantData.addAll(data.getData());
            if (merchantData.size() == 0){
                if(keyWord.equals("")){
                    showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_merchant_list_null), "暂无商户哦");
                }else {
                    showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_merchant_list_null), "商户不存在哦");
                }
            }else {
                hideEmpty();
            }
            merchantListAdapter.notifyDataSetChanged();
        }
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
