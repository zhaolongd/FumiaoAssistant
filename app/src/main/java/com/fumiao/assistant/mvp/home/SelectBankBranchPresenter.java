package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.BankBranchBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

public class SelectBankBranchPresenter extends BasePresenter<SelectBankBranchView> {
    public SelectBankBranchPresenter(SelectBankBranchView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     * 获取开户银行支行的数据
     */
    public void getBankBranch(String city_code, String bank_code) {
        OkGo.<BaseResponse<List<BankBranchBean>>>post(BANK_BRANCH)
                .params("city_num", city_code)
                .params("interbank_code", bank_code)
                .execute(new JsonCallback<BaseResponse<List<BankBranchBean>>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<List<BankBranchBean>>> response) {
                        mvpView.showBankBranchList(response.body().data);
                    }
                });
    }
}
