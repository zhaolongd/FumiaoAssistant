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
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpFragment;
import com.fumiao.assistant.bean.data.PersonnelBean;
import com.fumiao.assistant.bean.data.PersonnelListBean;
import com.fumiao.assistant.mvp.data.PersonnelListPresenter;
import com.fumiao.assistant.mvp.data.PersonnelListView;
import com.fumiao.assistant.ui.activity.data.PersonnelTransactionsActiviy;
import com.fumiao.assistant.ui.adapter.PersonnelAdapter;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhaolong.
 * Description: 人员列表
 * Date: 2020/3/3 0003 14:44
 */
public class PersonnelListFragment extends MvpFragment<PersonnelListPresenter> implements PersonnelListView {

    Unbinder unbinder;
    @BindView(R.id.ms_sort_type)
    MaterialSpinner msSortType;
    @BindView(R.id.rv_personnel_list)
    RecyclerView rvPersonnelList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    @BindView(R.id.tv_yestoday_active_title)
    TextView tvYestodayActiveTitle;

    private List<PersonnelBean> personnelData;
    private PersonnelAdapter personnelAdapter;
    private int statistics_flag = 1; // 1:商户，2:门店
    private String agency_code;
    int pagesize = 15;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_personnel, container, false);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        statistics_flag = getArguments().getInt("statistics_flag", 1);
        agency_code = getArguments().getString("team_agency_code");
        personnelData = new ArrayList<>();
        personnelAdapter = new PersonnelAdapter(getActivity(), R.layout.item_personnel, personnelData, statistics_flag);
        rvPersonnelList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvPersonnelList.setAdapter(personnelAdapter);
        rvPersonnelList.setLayoutManager(new LinearLayoutManager(getActivity()));
        personnelAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("name", personnelData.get(position).getPeopel().getName());
                bundle.putString("account_id", personnelData.get(position).getPeopel().getId());
                jumpActivity(PersonnelTransactionsActiviy.class, bundle);
            }
        });
        srlRefresh.setEnableLoadMore(true);
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                srlRefresh.setEnableLoadMore(true);
                mvpPresenter.getPersonnelStatistics(agency_code, pagesize, page);
            }
        });
        srlRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mvpPresenter.getPersonnelStatistics(agency_code, pagesize, page);
            }
        });
        mvpPresenter.getPersonnelStatistics(agency_code, pagesize, page);
    }

    @Override
    protected PersonnelListPresenter createPresenter() {
        return new PersonnelListPresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showPersonnelStatisticsList(PersonnelListBean data) {
        srlRefresh.finishRefresh();
        if (data != null) {
            if(page == 1){
                personnelData.clear();
                srlRefresh.finishRefresh(1000);  //刷新完成
            }else {
                srlRefresh.finishLoadMore(true);//加载完成
            }
            if (data.getPeople_list().size() < pagesize){
                srlRefresh.setEnableLoadMore(false);
            }
            personnelData.addAll(data.getPeople_list());

            if (personnelData.size() == 0) {
                showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.list_null), "暂无数据");
            }else {
                hideEmpty();
            }
            personnelAdapter.notifyDataSetChanged();
        }
    }

    public void update(int type) {
        statistics_flag = type;
        if(statistics_flag == 1){
            tvYestodayActiveTitle.setText("昨日活跃商户");
        }else {
            tvYestodayActiveTitle.setText("昨日活跃门店");
        }
        personnelAdapter.update(statistics_flag);
        personnelAdapter.notifyDataSetChanged();
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
