package com.fumiao.assistant.mvp.home;

import android.app.Activity;
import com.fumiao.assistant.bean.home.DeviceListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;


public class DeviceListPresenter  extends BasePresenter<DeviceListView> {

    public DeviceListPresenter(DeviceListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getDeviceList(String merchant_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("merchant_id", merchant_id);
        OkGo.<BaseResponse<DeviceListBean>>post(DEVICE_LIST)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<DeviceListBean>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<DeviceListBean>> response) {
                        mvpView.showDeviceList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<DeviceListBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }

}
