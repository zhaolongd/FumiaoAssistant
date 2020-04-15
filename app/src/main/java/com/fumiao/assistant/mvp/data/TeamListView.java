package com.fumiao.assistant.mvp.data;

import com.fumiao.assistant.bean.data.TeamBean;
import com.fumiao.assistant.mvp.base.BaseView;
import java.util.List;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/3 0003 13:46
 */
public interface TeamListView extends BaseView {
    void showTeamStatisticsList(List<TeamBean> data);
    void stateChangeOnError();
}
