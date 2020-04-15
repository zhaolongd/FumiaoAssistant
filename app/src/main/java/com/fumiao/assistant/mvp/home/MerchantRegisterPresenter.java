package com.fumiao.assistant.mvp.home;

import android.app.Activity;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong on 2019/9/12.
 */
public class MerchantRegisterPresenter extends BasePresenter<MerchantRegisterView> {

    public MerchantRegisterPresenter(MerchantRegisterView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getVerificationCode(String phone) {
        OkGo.<BaseResponse>post(GET_PHONE_CODE)
                .params("phone", phone)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.sendVerificationCodeSuccess((String) response.body().data);
                    }
                });
    }

    public void checkPhoneCode(String phone, String code) {
        OkGo.<BaseResponse>post(CHECK_PHONE_CODE)
                .params("phone", phone)
                .params("code", code)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.checkPhoneSuccess();
                    }
                });
    }

}
