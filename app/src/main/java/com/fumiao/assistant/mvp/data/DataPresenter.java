package com.fumiao.assistant.mvp.data;

import android.app.Activity;
import com.fumiao.assistant.bean.data.SalesmanStatisticsBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class DataPresenter extends BasePresenter<DataView>{
    public DataPresenter(DataView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getSalesmanStatistics(){
        OkGo.<BaseResponse<SalesmanStatisticsBean>>post(SALESMAN_STATISTICS)
                .execute(new JsonCallback<BaseResponse<SalesmanStatisticsBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<SalesmanStatisticsBean>> response) {
                        mvpView.showSalesmanStatistics(response.body().data);
                    }
                    @Override
                    public void onError(Response<BaseResponse<SalesmanStatisticsBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}
