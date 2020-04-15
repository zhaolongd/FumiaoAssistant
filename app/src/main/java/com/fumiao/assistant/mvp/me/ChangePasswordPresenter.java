package com.fumiao.assistant.mvp.me;

import android.app.Activity;

import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Author: XieBoss
 * Date: 2019/9/17 0017 10:27
 *
 * @Description:
 */
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView> {

    public ChangePasswordPresenter(ChangePasswordView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void changePassword(String admin_password,String new_password,String username){
        OkGo.<BaseResponse<String>>post(CHANGE_PASSWORD)
                .params("admin_password", admin_password)
                .params("new_password", new_password)
                .params("username", username)
                .execute(new JsonCallback<BaseResponse<String>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<String>> response) {
                        mvpView.changeSuccess(response.body().msg);
                    }
                });
    }
}
