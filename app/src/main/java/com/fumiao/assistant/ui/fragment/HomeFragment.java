package com.fumiao.assistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpFragment;
import com.fumiao.assistant.bean.home.SalesmanInfoBean;
import com.fumiao.assistant.mvp.home.HomePresenter;
import com.fumiao.assistant.mvp.home.HomeView;
import com.fumiao.assistant.ui.activity.home.MerchantAuditActivity;
import com.fumiao.assistant.ui.activity.home.MerchantInComingActivity;
import com.fumiao.core.uitls.SPUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView {
    Unbinder unbinder;
    @BindView(R.id.tv_total_sum)
    TextView tvTotalSum;
    @BindView(R.id.tv_incoming_parts_count)
    TextView tvIncomingPartsCount;
    @BindView(R.id.sr_refresh)
    SmartRefreshLayout srRefresh;
    @BindView(R.id.tv_total_count)
    TextView tvTotalCount;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this, getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        srRefresh.setEnableLoadMore(false);
        srRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getSalesmanInfo();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_collect_merchant_data, R.id.btn_manager_merchant_review})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_collect_merchant_data:
                jumpActivity(MerchantInComingActivity.class);
                break;
            case R.id.btn_manager_merchant_review:
                jumpActivity(MerchantAuditActivity.class);
                break;
        }
    }

    private void getSalesmanInfo() {
        String account = SPUtil.getInstance().getString(USER_PHONE);
        if (account != null && !account.equals("")) {
            mvpPresenter.getSalesmanInfo();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getSalesmanInfo();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getSalesmanInfo();
        }
    }

    @Override
    public void showSalesmanInfo(SalesmanInfoBean data) {
        srRefresh.finishRefresh(1000);
        if(data!= null){
            tvTotalSum.setText("￥" + data.getTotal_sum());
            tvIncomingPartsCount.setText(data.getIncoming_parts_count()+ "个");
            tvTotalCount.setText(data.getTotal_count());
        }
    }

    @Override
    public void stateChangeOnError() {
        switch (srRefresh.getState()) {
            case Refreshing:
                srRefresh.finishRefresh(false);
                break;
            default:
                break;
        }
    }
}
