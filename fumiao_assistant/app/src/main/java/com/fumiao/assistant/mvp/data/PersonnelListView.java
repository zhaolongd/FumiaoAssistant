package com.fumiao.assistant.mvp.data;

import com.fumiao.assistant.bean.data.PersonnelListBean;
import com.fumiao.assistant.mvp.base.BaseView;
/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/13 0013 14:14
 */
public interface PersonnelListView extends BaseView {
    void showPersonnelStatisticsList(PersonnelListBean data);
    void stateChangeOnError();
}
