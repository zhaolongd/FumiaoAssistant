package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.BankBean;
import com.fumiao.assistant.mvp.home.SelectOpeningBankPresenter;
import com.fumiao.assistant.mvp.home.SelectOpeningBankView;
import com.fumiao.assistant.ui.adapter.BankAdapter;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by zhaolong on 2019/9/18.
 * 选择开户银行
 */
public class SelectOpeningBankActivity extends MvpActivity<SelectOpeningBankPresenter> implements SelectOpeningBankView {

    @BindView(R.id.rv_bank_list)
    RecyclerView rvBankList;
    BankAdapter bankAdapter;
    List<BankBean> bankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_opening_bank);
        init();
    }

    private void init() {
        setTitle("选择开户行");
        bankList = new ArrayList<>();
        bankAdapter = new BankAdapter(this, R.layout.item_text2, bankList);
        rvBankList.setAdapter(bankAdapter);
        rvBankList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.line));
        rvBankList.addItemDecoration(dividerItemDecoration);
        bankAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                BankBean bankBean = bankList.get(position);
                Intent intent = new Intent();
                intent.putExtra("bank_name", bankBean.getBank_name());
                intent.putExtra("bank_code", bankBean.getInterbank_code());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mvpPresenter.getOpeningBank();
    }

    @Override
    protected SelectOpeningBankPresenter createPresenter() {
        return new SelectOpeningBankPresenter(this, this);
    }

    @Override
    public void showOpeningBankList(List<BankBean> data) {
        if(data != null){
            bankList.addAll(data);
            bankAdapter.notifyDataSetChanged();
        }
    }
}
