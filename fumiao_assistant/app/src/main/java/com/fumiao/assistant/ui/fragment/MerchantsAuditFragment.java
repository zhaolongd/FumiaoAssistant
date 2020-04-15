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
import com.fumiao.assistant.bean.home.InComingBean;
import com.fumiao.assistant.mvp.home.MerchantsAuditListPresenter;
import com.fumiao.assistant.mvp.home.MerchantsAuditListView;
import com.fumiao.assistant.ui.activity.merchant.MerchantDetailsActivity;
import com.fumiao.assistant.ui.activity.home.MicroMerchantInfoActivity;
import com.fumiao.assistant.ui.activity.home.NormalMerchantInComingActivity;
import com.fumiao.assistant.ui.adapter.MerchantsAuditAdapter;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 16:41
 *
 * @Description:
 */
public class MerchantsAuditFragment extends MvpFragment<MerchantsAuditListPresenter> implements MerchantsAuditListView {
    Unbinder unbinder;
    @BindView(R.id.rv_audit_list)
    RecyclerView statusRecycler;
    @BindView(R.id.rl_audit_manager)
    SmartRefreshLayout rlAuditManager;
    private int merchant_status = 0;//0：待进件 1：审核中，2：审核通过，3：审核驳回
    private MerchantsAuditAdapter mAuditStatusAdapter;
    private List<InComingBean> mDetailsList;
    private List<InComingBean> mDetailsSearchList;

    @Override
    protected MerchantsAuditListPresenter createPresenter() {
        return new MerchantsAuditListPresenter(this, getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_merchants_audit, null);
        Bundle bundle = getArguments();
        merchant_status = bundle.getInt("type");
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        mDetailsList = new ArrayList<>();
        mDetailsSearchList = new ArrayList<>();
        mAuditStatusAdapter = new MerchantsAuditAdapter(getActivity(), R.layout.item_status_audit, mDetailsList);
        statusRecycler.setAdapter(mAuditStatusAdapter);
        statusRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line));
        statusRecycler.addItemDecoration(dividerItemDecoration);
        getDetails(merchant_status);
        rlAuditManager.setEnableLoadMore(false);
        rlAuditManager.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getDetails(merchant_status);
            }
        });

        mAuditStatusAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                InComingBean inComingBean = mDetailsList.get(position);
                Bundle bundle = new Bundle();
                if(inComingBean.getStatus() == 3){
                    bundle.putString("merchant_id", inComingBean.getMerchant_id());
                    if(inComingBean.getMerchant_type() == 1){
                        jumpActivity(MicroMerchantInfoActivity.class, bundle);
                    }else if(inComingBean.getMerchant_type() == 2){
                        jumpActivity(NormalMerchantInComingActivity.class, bundle);
                    }
                }else {
                    bundle.putString("merchant_id", inComingBean.getMerchant_id());
                    jumpActivity(MerchantDetailsActivity.class, bundle);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getDetails(int status) {
        switch (status) {
            case 0:
                mvpPresenter.getDetails("");
                break;
            case 1:
                mvpPresenter.getDetails("1");
                break;
            case 2:
                mvpPresenter.getDetails("3");
                break;
        }

    }

    public void update(String keyWord){
        mAuditStatusAdapter.changeText(keyWord);
        mDetailsList.clear();
        if (keyWord == null || keyWord.equals("")) {
            mDetailsList.addAll(mDetailsSearchList);
            mAuditStatusAdapter.notifyDataSetChanged();
            return;
        }
        for (int i = 0; i < mDetailsSearchList.size(); i++) {
            if (mDetailsSearchList.get(i).getStore_name().contains(keyWord)) {
                mDetailsList.add(mDetailsSearchList.get(i));
            }
        }
        mAuditStatusAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAuditList(List<InComingBean> detailsBeans) {
        rlAuditManager.finishRefresh();  //刷新完成
        if(detailsBeans != null){
            mDetailsSearchList.clear();
            mDetailsSearchList.addAll(detailsBeans);
            mDetailsList.clear();
            mDetailsList.addAll(detailsBeans);
            if (mDetailsList.size() == 0){
                showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_merchant_list_null), "暂无商户哦");
            }else {
                hideEmpty();
            }
            mAuditStatusAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void stateChangeOnError() {
        switch (rlAuditManager.getState()) {
            case Refreshing:
                rlAuditManager.finishRefresh(false);
                break;
            default:
                break;
        }
    }
}
