package com.fumiao.assistant.mvp.home;

import android.app.Activity;
import com.fumiao.assistant.bean.home.DeviceDetailBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.AESUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceDetailPresenter extends BasePresenter<DeviceDetailView> {
    public DeviceDetailPresenter(DeviceDetailView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void sendRelieveCode() {
        OkGo.<BaseResponse>post(RELIEVE_CODE)
//                .params("phone", phone)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.sendRelieveCodeSuccess((String) response.body().data);
                    }
                });
    }

    public void getDeviceDetail(String sn){
        HttpParams httpParams = new HttpParams();
        httpParams.put("sn", sn);
        OkGo.<BaseResponse<DeviceDetailBean>>get(DEVICE_DETAIL)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<DeviceDetailBean>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<DeviceDetailBean>> response) {
                        mvpView.showDeviceDetail(response.body().data);
                    }
                });
    }

    public void unbindDevice(String store_id, String serial_number, String code){
        JSONObject unbindObject = new JSONObject();
        String data = "";
        try {
            unbindObject.put("store_id", store_id);
            unbindObject.put("serial_number", serial_number);
            unbindObject.put("code", code);
            String jsonString = unbindObject.toString();
            data = AESUtils.encrypt(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<BaseResponse>post(UNBIND_DEVICE)
                .params("data", data)
                .execute(new JsonCallback<BaseResponse>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.unbindDeviceSuccess((String) response.body().data);
                    }
                });
    }
}
