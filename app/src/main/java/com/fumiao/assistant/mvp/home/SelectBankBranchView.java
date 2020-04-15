package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.BankBranchBean;
import com.fumiao.assistant.mvp.base.BaseView;

import java.util.List;

public interface SelectBankBranchView extends BaseView{
    void showBankBranchList(List<BankBranchBean> data);
}
