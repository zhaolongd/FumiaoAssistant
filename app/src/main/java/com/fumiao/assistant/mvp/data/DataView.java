package com.fumiao.assistant.mvp.data;

import com.fumiao.assistant.bean.data.SalesmanStatisticsBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface DataView extends BaseView{
    void showSalesmanStatistics(SalesmanStatisticsBean data);
    void stateChangeOnError();
}
