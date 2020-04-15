package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class StoreAddPresenter extends BasePresenter<StoreAddView>{
    public StoreAddPresenter(StoreAddView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void sendCode(String phone, String merchant_id) {
        OkGo.<BaseResponse>post(ADD_STORE_SEND_CODE)
                .params("phone", phone) //商户手机号
                .params("merchant_id", merchant_id) //商户号
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.sendCodeSuccess((String) response.body().data);
                    }
                });
    }

    /**
     *merchant_id  商户编号
     *phone      联系人手机号
     *name      门店名称
     *real_name    联系人名称
     *code        短信验证码
     *merchant_phone     商户手机号
     *province   省id
     *city    市id
     *area     区id
     *address   详细地址
     */
    public void storeAdd(String merchant_id, String phone, String name, String real_name, String code, String merchant_phone, String province, String city, String area, String province_name, String city_name, String area_name, String address){
        OkGo.<BaseResponse>post(ADD_STORE)
                .params("merchant_id", merchant_id)
                .params("phone", phone)
                .params("name", name)
                .params("real_name", real_name)
                .params("code", code)
                .params("merchant_phone", merchant_phone)
                .params("province", province)
                .params("city", city)
                .params("area", area)
                .params("province_name", province_name)
                .params("city_name", city_name)
                .params("area_name", area_name)
                .params("address", address)
                .execute(new JsonCallback<BaseResponse>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.storeAddSuccess((String) response.body().data);
                    }
                });
    }
}
