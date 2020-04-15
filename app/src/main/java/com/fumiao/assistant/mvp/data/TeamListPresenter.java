package com.fumiao.assistant.mvp.data;

import android.app.Activity;
import com.fumiao.assistant.bean.data.TeamBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import java.util.List;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/3 0003 13:46
 */
public class TeamListPresenter extends BasePresenter<TeamListView> {
    public TeamListPresenter(TeamListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getTeamSstatistics() {
        OkGo.<BaseResponse<List<TeamBean>>>get(TEAM_STATISTICS)
                .execute(new JsonCallback<BaseResponse<List<TeamBean>>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<List<TeamBean>>> response) {
                        mvpView.showTeamStatisticsList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<List<TeamBean>>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}