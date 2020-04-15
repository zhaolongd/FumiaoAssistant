package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.SalesmanInfoBean;
import com.fumiao.assistant.mvp.base.BaseView;
/**
 * Created by zhaolong on 2019/9/11.
 */
public interface HomeView  extends BaseView {
    void showSalesmanInfo(SalesmanInfoBean data);
    void stateChangeOnError();
}
