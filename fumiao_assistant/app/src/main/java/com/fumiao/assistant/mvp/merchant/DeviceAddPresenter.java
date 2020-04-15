package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class DeviceAddPresenter extends BasePresenter<DeviceAddView>{
    public DeviceAddPresenter(DeviceAddView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    //绑定扫描二维码
    public void activationQrcode(String data){
        OkGo.<BaseResponse>post(ACTIVATION_CODE)
                .params("data", data)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.activationQrcodeSuccess();
                    }
                });
    }
}
