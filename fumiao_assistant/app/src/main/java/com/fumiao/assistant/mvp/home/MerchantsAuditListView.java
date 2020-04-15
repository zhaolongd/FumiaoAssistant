package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.InComingBean;

import java.util.List;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 16:44
 *
 * @Description:
 */
public interface MerchantsAuditListView {
    void showAuditList(List<InComingBean> detailsBeans);
    void stateChangeOnError();
}
