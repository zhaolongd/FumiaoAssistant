package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.BankCityBean;
import com.fumiao.assistant.mvp.base.BaseView;
import java.util.List;

public interface SelectBankCityView extends BaseView{
    void showBankCityList(List<BankCityBean> data);
}
