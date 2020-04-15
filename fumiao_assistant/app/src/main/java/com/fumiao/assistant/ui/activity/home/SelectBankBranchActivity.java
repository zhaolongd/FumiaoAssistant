package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.BankBranchBean;
import com.fumiao.assistant.mvp.home.SelectBankBranchPresenter;
import com.fumiao.assistant.mvp.home.SelectBankBranchView;
import com.fumiao.assistant.ui.adapter.BankBranchAdapter;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class SelectBankBranchActivity extends MvpActivity<SelectBankBranchPresenter> implements SelectBankBranchView {

    @BindView(R.id.rv_bank_branch_list)
    RecyclerView rvBankBranchList;
    BankBranchAdapter bankBranchAdapter;
    List<BankBranchBean> bankBranchList;
    List<BankBranchBean> bankSearchList;
    @BindView(R.id.ed_bank_search)
    EditText edBankSearch;

    private String cityCode;
    private String bankCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank_branch);
        init();
    }

    private void init() {
        setTitle("选择支行名称");
        cityCode = getIntent().getStringExtra("bank_city_code");
        bankCode = getIntent().getStringExtra("bank_code");
        bankBranchList = new ArrayList<>();
        bankSearchList = new ArrayList<>();
        bankBranchAdapter = new BankBranchAdapter(this, R.layout.item_text2, bankBranchList);
        rvBankBranchList.setAdapter(bankBranchAdapter);
        rvBankBranchList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rvBankBranchList.addItemDecoration(dividerItemDecoration);
        bankBranchAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                BankBranchBean bankBranchBean = bankBranchList.get(position);
                Intent intent = new Intent();
                intent.putExtra("bank_branch_name", bankBranchBean.getInterbank_name());
                intent.putExtra("bank_branch_code", bankBranchBean.getInterbank_num());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mvpPresenter.getBankBranch(cityCode, bankCode);
        edBankSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyWord = s.toString();
                bankBranchAdapter.changeText(keyWord);
                bankBranchList.clear();
                if (keyWord == null || keyWord.equals("")) {
                    bankBranchList.addAll(bankSearchList);
                    bankBranchAdapter.notifyDataSetChanged();
                    return;
                }
                for (int i = 0; i < bankSearchList.size(); i++) {
                    if (bankSearchList.get(i).getInterbank_name().contains(keyWord)) {
                        bankBranchList.add(bankSearchList.get(i));
                    }
                }
                bankBranchAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected SelectBankBranchPresenter createPresenter() {
        return new SelectBankBranchPresenter(this, this);
    }

    @Override
    public void showBankBranchList(List<BankBranchBean> data) {
        this.bankSearchList = data;
        if (data != null) {
            bankBranchList.addAll(data);
            bankBranchAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        edBankSearch.setText("");
    }
}
