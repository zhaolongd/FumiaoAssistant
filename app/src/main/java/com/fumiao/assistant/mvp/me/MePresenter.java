package com.fumiao.assistant.mvp.me;

import android.app.Activity;

import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong on 2019/9/11.
 */
public class MePresenter extends BasePresenter<MeView> {
    public MePresenter(MeView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void loginOut(){
        OkGo.<BaseResponse<String>>post(LOGIN_OUT)
                .execute(new JsonCallback<BaseResponse<String>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<String>> response) {
                        mvpView.loginSuccess(response.body().msg);
                    }
                });
    }
}
