package com.fumiao.assistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseFragment;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhaolong.
 * Description: 商户门店交易
 * Date: 2020/3/2 0002 14:43
 */
public class TransactionsFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.data_statistics_line)
    TextView dataStatisticsLine;
    @BindView(R.id.team_list_line)
    TextView teamListLine;
    @BindView(R.id.tcv_type)
    TabControlView tcvType;

    private DataStatisticsFragment dataStatisticsFragment;
    private TeamListFragment teamListFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int tabIndex = 1;
    private int statistics_flag = 1; // 1:商户，2:门店

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_transactions, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init(){
        tcvType.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                statistics_flag = Integer.parseInt(value);
                if (dataStatisticsFragment != null) {
                    dataStatisticsFragment.update(statistics_flag);
                }
                if (teamListFragment != null){
                    teamListFragment.update(statistics_flag);
                }
            }
        });
        fragmentManager = getChildFragmentManager();
        setMyTabHost(tabIndex);
    }

    private void setMyTabHost(int index) {
        transaction = fragmentManager.beginTransaction();
        if (dataStatisticsFragment != null) {
            transaction.hide(dataStatisticsFragment);
        }
        if (teamListFragment != null) {
            transaction.hide(teamListFragment);
        }
        clearText();
        tabIndex = index;
        switch (index) {
            case 1:
                dataStatisticsLine.setVisibility(View.VISIBLE);
                if (dataStatisticsFragment == null) {
                    dataStatisticsFragment = new DataStatisticsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("statistics_flag", statistics_flag);
                    dataStatisticsFragment.setArguments(bundle);
                    transaction.add(R.id.content, dataStatisticsFragment);
                } else {
                    transaction.show(dataStatisticsFragment);
                }
                break;
            case 2:
                teamListLine.setVisibility(View.VISIBLE);
                if (teamListFragment == null) {
                    teamListFragment = new TeamListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("statistics_flag", statistics_flag);
                    teamListFragment.setArguments(bundle);
                    transaction.add(R.id.content, teamListFragment);
                } else {
                    transaction.show(teamListFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void clearText() {
        dataStatisticsLine.setVisibility(View.INVISIBLE);
        teamListLine.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.data_statistics, R.id.team_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.data_statistics:
                if (tabIndex != 1) {
                    setMyTabHost(1);
                }
                break;
            case R.id.team_list:
                if (tabIndex != 2) {
                    setMyTabHost(2);
                }
                break;
        }
    }
}
