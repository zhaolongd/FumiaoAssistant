package com.fumiao.assistant.ui.activity.data;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import com.fumiao.assistant.ui.fragment.DataStatisticsFragment;
import com.fumiao.assistant.ui.fragment.MerchantStoreListFragment;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhaolong.
 * Description: 人员交易数据
 * Date: 2020/3/13 0013 15:24
 */
public class PersonnelTransactionsActiviy extends BaseActivity {

    @BindView(R.id.tcv_type)
    TabControlView tcvType;
    @BindView(R.id.data_statistics_title)
    TextView dataStatisticsTitle;
    @BindView(R.id.data_statistics_line)
    TextView dataStatisticsLine;
    @BindView(R.id.data_statistics)
    RelativeLayout dataStatistics;
    @BindView(R.id.merchant_store_list_title)
    TextView merchantStoreListTitle;
    @BindView(R.id.merchant_store_list_line)
    TextView merchantStoreListLine;
    @BindView(R.id.merchant_store_list)
    RelativeLayout merchantStoreList;
    @BindView(R.id.content)
    FrameLayout content;

    private DataStatisticsFragment dataStatisticsFragment;
    private MerchantStoreListFragment merchantStoreListFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int tabIndex = 1;
    private int statistics_flag = 1; // 1:商户，2:门店
    private String name;
    private String account_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel_transactions, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tcvType.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                statistics_flag = Integer.parseInt(value);
                if(statistics_flag == 1){
                    merchantStoreListTitle.setText("商户列表");
                }else{
                    merchantStoreListTitle.setText("门店列表");
                }
                if (dataStatisticsFragment != null) {
                    dataStatisticsFragment.update(statistics_flag);
                }
                if (merchantStoreListFragment != null) {
                    merchantStoreListFragment.update(statistics_flag);
                }
            }
        });
        name = getIntent().getStringExtra("name");
        account_id = getIntent().getStringExtra("account_id");
        setTitle(name);
        fragmentManager = getSupportFragmentManager();
        setMyTabHost(tabIndex);
    }

    private void setMyTabHost(int index) {
        transaction = fragmentManager.beginTransaction();
        if (dataStatisticsFragment != null) {
            transaction.hide(dataStatisticsFragment);
        }
        if (merchantStoreListFragment != null) {
            transaction.hide(merchantStoreListFragment);
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
                    bundle.putString("account_id", account_id);
                    dataStatisticsFragment.setArguments(bundle);
                    transaction.add(R.id.content, dataStatisticsFragment);
                } else {
                    transaction.show(dataStatisticsFragment);
                }
                break;
            case 2:
                merchantStoreListLine.setVisibility(View.VISIBLE);
                if (merchantStoreListFragment == null) {
                    merchantStoreListFragment = new MerchantStoreListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("statistics_flag", statistics_flag);
                    bundle.putString("account_id", account_id);
                    merchantStoreListFragment.setArguments(bundle);
                    transaction.add(R.id.content, merchantStoreListFragment);
                } else {
                    transaction.show(merchantStoreListFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void clearText() {
        dataStatisticsLine.setVisibility(View.INVISIBLE);
        merchantStoreListLine.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.data_statistics, R.id.merchant_store_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.data_statistics:
                if (tabIndex != 1) {
                    setMyTabHost(1);
                }
                break;
            case R.id.merchant_store_list:
                if (tabIndex != 2) {
                    setMyTabHost(2);
                }
                break;
        }
    }

}
