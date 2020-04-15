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
import com.fumiao.assistant.bean.data.TeamBean;
import com.fumiao.assistant.mvp.data.TeamListPresenter;
import com.fumiao.assistant.mvp.data.TeamListView;
import com.fumiao.assistant.ui.activity.data.TeamTransactionsActivity;
import com.fumiao.assistant.ui.adapter.TeamAdapter;
import com.fumiao.assistant.widget.SpacesItemDecoration;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhaolong.
 * Description: 团队列表
 * Date: 2020/3/2 0002 16:05
 */
public class TeamListFragment extends MvpFragment<TeamListPresenter> implements TeamListView {
    Unbinder unbinder;
    @BindView(R.id.rv_team_list)
    RecyclerView rvTeamList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;

    private List<TeamBean> teamData;
    private TeamAdapter teamAdapter;
    private int statistics_flag = 1; // 1:商户，2:门店

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_team_list, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        statistics_flag = getArguments().getInt("statistics_flag", 1);
        teamData = new ArrayList<>();
        teamAdapter = new TeamAdapter(getActivity(), R.layout.item_team, teamData, statistics_flag);
        rvTeamList.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(getActivity(), 15), Utils.dip2px(getActivity(), 15),  Utils.dip2px(getActivity(), 0), Utils.dip2px(getActivity(), 0)));
        rvTeamList.setAdapter(teamAdapter);
        rvTeamList.setLayoutManager(new LinearLayoutManager(getActivity()));
        teamAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("agency_code", teamData.get(position).getAgency_code());
                bundle.putString("agency_name", teamData.get(position).getAgency_name());
                jumpActivity(TeamTransactionsActivity.class, bundle);
            }
        });
        srlRefresh.setEnableLoadMore(false);
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getTeamSstatistics();
            }
        });
        mvpPresenter.getTeamSstatistics();
    }

    public void update(int type) {
        statistics_flag = type;
        teamAdapter.update(statistics_flag);
        teamAdapter.notifyDataSetChanged();
    }

    @Override
    protected TeamListPresenter createPresenter() {
        return new TeamListPresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showTeamStatisticsList(List<TeamBean> data) {
        srlRefresh.finishRefresh();
        if(data != null){
            teamData.clear();
            teamData.addAll(data);
            if (teamData.size() == 0) {
                showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.list_null), "暂无数据");
            }else {
                hideEmpty();
            }
            teamAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void stateChangeOnError() {
        if (teamData.size() == 0) {
            showEmpty(ContextCompat.getDrawable(getActivity(), R.mipmap.list_null), "暂无数据");
        }else {
            hideEmpty();
        }
        switch (srlRefresh.getState()) {
            case Refreshing:
                srlRefresh.finishRefresh(false);
                break;
            default:

                break;
        }
    }
}
