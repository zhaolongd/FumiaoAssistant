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
import com.fumiao.assistant.bean.data.SalesmanStatisticsBean;
import com.fumiao.assistant.mvp.data.DataPresenter;
import com.fumiao.assistant.mvp.data.DataView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DataFragment extends MvpFragment<DataPresenter> implements DataView {
    Unbinder unbinder;
    @BindView(R.id.tv_today_count)
    TextView tvTodayCount;
    @BindView(R.id.tv_today_sum_totals)
    TextView tvTodaySumTotals;
    @BindView(R.id.tv_today_active_merchant)
    TextView tvTodayActiveMerchant;
    @BindView(R.id.tv_yestoday_count)
    TextView tvYestodayCount;
    @BindView(R.id.tv_yestoday_sum_totals)
    TextView tvYestodaySumTotals;
    @BindView(R.id.tv_yestoday_active_merchant)
    TextView tvYestodayActiveMerchant;
    @BindView(R.id.tv_month_count)
    TextView tvMonthCount;
    @BindView(R.id.tv_month_sum_totals)
    TextView tvMonthSumTotals;
    @BindView(R.id.sr_refresh)
    SmartRefreshLayout srRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_data, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        srRefresh.setEnableLoadMore(false);
        srRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getSalesmanStatistics();
            }
        });
        mvpPresenter.getSalesmanStatistics();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected DataPresenter createPresenter() {
        return new DataPresenter(this, getActivity());
    }

    @Override
    public void showSalesmanStatistics(SalesmanStatisticsBean data) {
        srRefresh.finishRefresh(1000);
        if(data != null){
            tvTodayCount.setText(data.getToday_count());
            tvTodaySumTotals.setText(data.getToday_sum_totals());
            tvTodayActiveMerchant.setText(data.getToday_merchant_ids());
            tvYestodayCount.setText(data.getYestoday_count());
            tvYestodaySumTotals.setText(data.getYestoday_sum_totals());
            tvYestodayActiveMerchant.setText(data.getYestoday_merchant_ids());
            tvMonthCount.setText(data.getMonth_count());
            tvMonthSumTotals.setText(data.getMonth_sum_totals());
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
