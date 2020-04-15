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
import com.fumiao.assistant.bean.data.DataStatisticsBean;
import com.fumiao.assistant.mvp.data.DataStatisticsPresenter;
import com.fumiao.assistant.mvp.data.DataStatisticsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhaolong.
 * Description: 数据统计
 * Date: 2020/3/2 0002 15:03
 */
public class DataStatisticsFragment extends MvpFragment<DataStatisticsPresenter> implements DataStatisticsView {
    Unbinder unbinder;
    @BindView(R.id.tv_today_sum_totals)
    TextView tvTodaySumTotals;
    @BindView(R.id.tv_today_sum_totals_compare)
    TextView tvTodaySumTotalsCompare;
    @BindView(R.id.tv_today_count)
    TextView tvTodayCount;
    @BindView(R.id.tv_today_count_compare)
    TextView tvTodayCountCompare;
    @BindView(R.id.tv_today_active)
    TextView tvTodayActive;
    @BindView(R.id.tv_today_active_compare)
    TextView tvTodayActiveCompare;
    @BindView(R.id.tv_yestoday_sum_totals)
    TextView tvYestodaySumTotals;
    @BindView(R.id.tv_yestoday_sum_totals_compare)
    TextView tvYestodaySumTotalsCompare;
    @BindView(R.id.tv_yestoday_sum_totals_per_capita)
    TextView tvYestodaySumTotalsPerCapita;
    @BindView(R.id.tv_yestoday_count)
    TextView tvYestodayCount;
    @BindView(R.id.tv_yestoday_count_compare)
    TextView tvYestodayCountCompare;
    @BindView(R.id.tv_yestoday_count_per_capita)
    TextView tvYestodayCountPerCapita;
    @BindView(R.id.tv_yestoday_active)
    TextView tvYestodayActive;
    @BindView(R.id.tv_yestoday_active_compare)
    TextView tvYestodayActiveCompare;
    @BindView(R.id.tv_yestoday_active_per_capita)
    TextView tvYestodayActivePerCapita;
    @BindView(R.id.tv_month_sum_totals)
    TextView tvMonthSumTotals;
    @BindView(R.id.tv_month_sum_totals_compare)
    TextView tvMonthSumTotalsCompare;
    @BindView(R.id.tv_month_count)
    TextView tvMonthCount;
    @BindView(R.id.tv_month_count_compare)
    TextView tvMonthCountCompare;
    @BindView(R.id.sr_refresh)
    SmartRefreshLayout srRefresh;
    @BindView(R.id.tv_today_active_title)
    TextView tvTodayActiveTitle;
    @BindView(R.id.tv_yestoday_active_title)
    TextView tvYestodayActiveTitle;

    private int statistics_flag = 1; // 1:商户，2:门店
    private String team_agency_code; //团队机构编码
    private String account_id; //员工账户ID

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_data_statistics, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        statistics_flag = getArguments().getInt("statistics_flag", 1);
        team_agency_code = getArguments().getString("team_agency_code");
        account_id = getArguments().getString("account_id");
        srRefresh.setEnableLoadMore(false);
        srRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getDataStatistics(statistics_flag, team_agency_code, account_id);
            }
        });
        mvpPresenter.getDataStatistics(statistics_flag, team_agency_code, account_id);
    }

    public void update(int type) {
        statistics_flag = type;
        mvpPresenter.getDataStatistics(statistics_flag, team_agency_code, account_id);
    }

    @Override
    protected DataStatisticsPresenter createPresenter() {
        return new DataStatisticsPresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showDataStatistics(DataStatisticsBean data) {
        srRefresh.finishRefresh(1000);
        tvTodaySumTotals.setText(data.getToday_total_amount());
        tvTodaySumTotalsCompare.setText(data.getToday_mom_amount());
        tvTodayCount.setText(data.getToday_total_count());
        tvTodayCountCompare.setText(data.getToday_mom_count());

        tvYestodaySumTotals.setText(data.getYesterday_total_amount());
        tvYestodaySumTotalsCompare.setText(data.getToday_mom_amount());
        tvYestodayCount.setText(data.getYesterday_total_count());
        tvYestodayCountCompare.setText(data.getToday_mom_count());

        tvMonthSumTotals.setText(data.getCurrent_month_total_amount());
        tvMonthSumTotalsCompare.setText(data.getCurrent_month_mom_amount());
        tvMonthCount.setText(data.getCurrent_month_total_count());
        tvMonthCountCompare.setText(data.getCurrent_month_mom_count());
        if (statistics_flag == 1) {
            tvTodayActiveTitle.setText("活跃商户");
            tvTodayActive.setText(data.getToday_active_merchants());
            tvTodayActiveCompare.setText(data.getToday_mom_active_merchants());
            tvYestodayActiveTitle.setText("活跃商户");
            tvYestodayActive.setText(data.getYesterday_active_merchants());
            tvYestodayActiveCompare.setText(data.getToday_mom_active_merchants());
        } else {
            tvTodayActiveTitle.setText("活跃门店");
            tvTodayActive.setText(data.getToday_active_stores());
            tvTodayActiveCompare.setText(data.getToday_mom_active_stores());
            tvYestodayActiveTitle.setText("活跃门店");
            tvYestodayActive.setText(data.getYesterday_active_stores());
            tvYestodayActiveCompare.setText(data.getToday_mom_active_stores());
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
