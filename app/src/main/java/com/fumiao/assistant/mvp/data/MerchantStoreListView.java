package com.fumiao.assistant.mvp.data;

import com.fumiao.assistant.bean.data.MerchantStatisticsListBean;
import com.fumiao.assistant.bean.data.StoreStatisticsListBean;
import com.fumiao.assistant.mvp.base.BaseView;


/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/13 0013 15:52
 */
public interface MerchantStoreListView extends BaseView {
    void showMerchantStatisticsList(MerchantStatisticsListBean data);
    void showStoreStatisticsList(StoreStatisticsListBean data);
    void stateChangeOnError();
}
