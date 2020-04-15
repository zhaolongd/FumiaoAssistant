package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.BankProvinceBean;
import com.fumiao.assistant.mvp.home.SelectBankProvincePresenter;
import com.fumiao.assistant.mvp.home.SelectBankProvinceView;
import com.fumiao.assistant.ui.adapter.BankProvinceAdapter;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class SelectBankProvinceActivity extends MvpActivity<SelectBankProvincePresenter> implements SelectBankProvinceView {

    @BindView(R.id.rv_bank_province_list)
    RecyclerView rvBankProvinceList;
    BankProvinceAdapter bankProvinceAdapter;
    List<BankProvinceBean> bankProvinceList;
    private String bankCode;
    private boolean isNormalMerchant = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank_province);
        init();
    }

    private void init() {
        setTitle("选择省");
        bankCode = getIntent().getStringExtra("bank_code");
        isNormalMerchant = getIntent().getBooleanExtra("is_normal_merchant", false);
        bankProvinceList = new ArrayList<>();
        bankProvinceAdapter = new BankProvinceAdapter(this, R.layout.item_text2, bankProvinceList);
        rvBankProvinceList.setAdapter(bankProvinceAdapter);
        rvBankProvinceList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.line));
        rvBankProvinceList.addItemDecoration(dividerItemDecoration);
        bankProvinceAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                BankProvinceBean bankProvinceBean = bankProvinceList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("province_name", bankProvinceBean.getProvince_name());
                bundle.putString("province_code", bankProvinceBean.getProvince_num());
                bundle.putBoolean("is_normal_merchant", isNormalMerchant);
                jumpActivity(SelectBankCityActivity.class, bundle);
            }
        });
        mvpPresenter.getBankProvince(bankCode);
    }

    @Override
    protected SelectBankProvincePresenter createPresenter() {
        return new SelectBankProvincePresenter(this, this);
    }

    @Override
    public void showBankProvinceList(List<BankProvinceBean> data) {
        if(data != null){
            bankProvinceList.addAll(data);
            bankProvinceAdapter.notifyDataSetChanged();
        }
    }
}
