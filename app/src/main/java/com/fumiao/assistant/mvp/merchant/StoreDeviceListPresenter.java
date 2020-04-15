package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;

import com.fumiao.assistant.bean.home.DeviceBean;
import com.fumiao.assistant.bean.home.DeviceListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

public class StoreDeviceListPresenter extends BasePresenter<StoreDeviceListView>{
    public StoreDeviceListPresenter(StoreDeviceListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getStoreDeviceList(String store_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        OkGo.<BaseResponse<List<DeviceBean>>>post(STORE_DEVICE_LIST)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<List<DeviceBean>>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<List<DeviceBean>>> response) {
                        mvpView.showDeviceList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<List<DeviceBean>>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}
