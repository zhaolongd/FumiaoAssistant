package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.InComingDetailBean;
import com.fumiao.assistant.bean.home.InComingResultBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface NormalMerchantInComingView extends BaseView{
    void showInComingDetail(InComingDetailBean data);
    void inComingSuccess(InComingResultBean data);
    void rejectInComingSuccess(InComingResultBean data);
}
