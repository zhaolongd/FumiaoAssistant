package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.SalesmanInfoBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong on 2019/9/11.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(HomeView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getSalesmanInfo(){
        OkGo.<BaseResponse<SalesmanInfoBean>>post(SALESMAN_INFO)
                .execute(new JsonCallback<BaseResponse<SalesmanInfoBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<SalesmanInfoBean>> response) {
                        mvpView.showSalesmanInfo(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<SalesmanInfoBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}
