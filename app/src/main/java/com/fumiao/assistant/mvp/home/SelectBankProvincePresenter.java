package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.BankBean;
import com.fumiao.assistant.bean.home.BankProvinceBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

public class SelectBankProvincePresenter extends BasePresenter<SelectBankProvinceView> {
    public SelectBankProvincePresenter(SelectBankProvinceView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     * 获取开户银行省的数据
     */
    public void getBankProvince(String bank_code) {
        OkGo.<BaseResponse<List<BankProvinceBean>>>post(BANK_PROVINCE)
                .params("interbank_code", bank_code)
                .execute(new JsonCallback<BaseResponse<List<BankProvinceBean>>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<List<BankProvinceBean>>> response) {
                        mvpView.showBankProvinceList(response.body().data);
                    }
                });
    }
}
