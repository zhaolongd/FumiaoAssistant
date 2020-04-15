package com.fumiao.assistant.ui.activity.data;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import com.fumiao.assistant.ui.fragment.DataStatisticsFragment;
import com.fumiao.assistant.ui.fragment.PersonnelListFragment;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhaolong.
 * Description: 团队交易数据
 * Date: 2020/3/3 0003 14:33
 */
public class TeamTransactionsActivity extends BaseActivity {

    @BindView(R.id.tcv_type)
    TabControlView tcvType;
    @BindView(R.id.data_statistics_line)
    TextView dataStatisticsLine;
    @BindView(R.id.personnel_list_line)
    TextView personnelListLine;

    private DataStatisticsFragment dataStatisticsFragment;
    private PersonnelListFragment personnelListFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int tabIndex = 1;
    private int statistics_flag = 1; // 1:商户，2:门店
    private String agency_code;
    private String agency_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_transactions, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tcvType.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                statistics_flag = Integer.parseInt(value);
                if (dataStatisticsFragment != null) {
                    dataStatisticsFragment.update(statistics_flag);
                }
                if(personnelListFragment != null){
                    personnelListFragment.update(statistics_flag);
                }
            }
        });
        agency_code = getIntent().getStringExtra("agency_code");
        agency_name = getIntent().getStringExtra("agency_name");
        setTitle(agency_name);
        fragmentManager = getSupportFragmentManager();
        setMyTabHost(tabIndex);
    }

    private void setMyTabHost(int index) {
        transaction = fragmentManager.beginTransaction();
        if (dataStatisticsFragment != null) {
            transaction.hide(dataStatisticsFragment);
        }
        if (personnelListFragment != null) {
            transaction.hide(personnelListFragment);
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
                    bundle.putString("team_agency_code", agency_code);
                    dataStatisticsFragment.setArguments(bundle);
                    transaction.add(R.id.content, dataStatisticsFragment);
                } else {
                    transaction.show(dataStatisticsFragment);
                }
                break;
            case 2:
                personnelListLine.setVisibility(View.VISIBLE);
                if (personnelListFragment == null) {
                    personnelListFragment = new PersonnelListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("statistics_flag", statistics_flag);
                    bundle.putString("team_agency_code", agency_code);
                    personnelListFragment.setArguments(bundle);
                    transaction.add(R.id.content, personnelListFragment);
                } else {
                    transaction.show(personnelListFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void clearText() {
        dataStatisticsLine.setVisibility(View.INVISIBLE);
        personnelListLine.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.data_statistics, R.id.personnel_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.data_statistics:
                if (tabIndex != 1) {
                    setMyTabHost(1);
                }
                break;
            case R.id.personnel_list:
                if (tabIndex != 2) {
                    setMyTabHost(2);
                }
                break;
        }
    }
}
