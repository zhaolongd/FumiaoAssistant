package com.fumiao.assistant.mvp.data;

import com.fumiao.assistant.bean.data.DataStatisticsBean;
import com.fumiao.assistant.mvp.base.BaseView;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/12 0012 10:38
 */
public interface DataStatisticsView extends BaseView {
    void showDataStatistics(DataStatisticsBean data);
    void stateChangeOnError();
}
