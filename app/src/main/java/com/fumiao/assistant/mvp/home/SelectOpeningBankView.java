package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.BankBean;
import com.fumiao.assistant.mvp.base.BaseView;

import java.util.List;

/**
 * Created by zhaolong on 2019/9/18.
 */
public interface SelectOpeningBankView extends BaseView{
    void showOpeningBankList(List<BankBean> data);
}
