package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.BankProvinceBean;
import com.fumiao.assistant.mvp.base.BaseView;
import java.util.List;

public interface SelectBankProvinceView extends BaseView{
    void showBankProvinceList(List<BankProvinceBean> data);
}
