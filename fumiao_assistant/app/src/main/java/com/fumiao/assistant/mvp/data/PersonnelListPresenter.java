package com.fumiao.assistant.mvp.data;

import android.app.Activity;
import com.fumiao.assistant.bean.data.PersonnelListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/13 0013 14:16
 */
public class PersonnelListPresenter extends BasePresenter<PersonnelListView> {
    public PersonnelListPresenter(PersonnelListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getPersonnelStatistics(String team_agency_code, int pagesize, int page) {
        OkGo.<BaseResponse<PersonnelListBean>>get(PERSONNEL_STATISTICS)
                .params("team_agency_code", team_agency_code)
                .params("pagesize", pagesize)
                .params("page", page)
                .execute(new JsonCallback<BaseResponse<PersonnelListBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<PersonnelListBean>> response) {
                        mvpView.showPersonnelStatisticsList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<PersonnelListBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}
