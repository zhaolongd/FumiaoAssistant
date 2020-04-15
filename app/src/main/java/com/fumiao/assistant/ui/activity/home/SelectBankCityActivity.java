package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.BankCityBean;
import com.fumiao.assistant.mvp.home.SelectBankCityPresenter;
import com.fumiao.assistant.mvp.home.SelectBankCityView;
import com.fumiao.assistant.ui.adapter.BankCityAdapter;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class SelectBankCityActivity extends MvpActivity<SelectBankCityPresenter> implements SelectBankCityView {

    @BindView(R.id.rv_bank_city_list)
    RecyclerView rvBankCityList;
    BankCityAdapter bankCityAdapter;
    List<BankCityBean> bankCityList;
    private String provinceCode;
    private String provinceName;
    private boolean isNormalMerchant = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank_city);
        init();
    }

    private void init() {
        setTitle("选择城市");
        provinceCode = getIntent().getStringExtra("province_code");
        provinceName = getIntent().getStringExtra("province_name");
        isNormalMerchant = getIntent().getBooleanExtra("is_normal_merchant", false);
        bankCityList = new ArrayList<>();
        bankCityAdapter = new BankCityAdapter(this, R.layout.item_text2, bankCityList);
        rvBankCityList.setAdapter(bankCityAdapter);
        rvBankCityList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.line));
        rvBankCityList.addItemDecoration(dividerItemDecoration);
        bankCityAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                BankCityBean bankCityBean = bankCityList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("city_name", bankCityBean.getCity_name());
                bundle.putString("city_code", bankCityBean.getCity_num());
                if(isNormalMerchant){
                    jumpActivity(NormalMerchantSettlementActivity.class, bundle);
                }else {
                    jumpActivity(MicroMerchantAuthenticationActivity.class, bundle);
                }
            }
        });
        mvpPresenter.getBankCity(provinceCode);
    }

    @Override
    protected SelectBankCityPresenter createPresenter() {
        return new SelectBankCityPresenter(this, this);
    }

    @Override
    public void showBankCityList(List<BankCityBean> data) {
        if(data != null){
            bankCityList.addAll(data);
            bankCityAdapter.notifyDataSetChanged();
        }
    }
}
