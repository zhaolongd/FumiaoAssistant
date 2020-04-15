package com.fumiao.assistant.mvp.main;

import android.app.Activity;
import com.fumiao.assistant.bean.home.LoginBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong on 2019/9/12.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void login(String username, String password) {
        OkGo.<BaseResponse<LoginBean>>post(LOGIN)
                .params("username", username)
                .params("password", password)
                .execute(new JsonCallback<BaseResponse<LoginBean>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<LoginBean>> response) {
                        mvpView.loginSuccess(response.body().data);
                    }
                });
    }
}
