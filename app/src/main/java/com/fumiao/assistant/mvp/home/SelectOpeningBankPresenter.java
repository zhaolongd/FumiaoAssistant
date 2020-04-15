package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.BankBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by zhaolong on 2019/9/18.
 */
public class SelectOpeningBankPresenter extends BasePresenter<SelectOpeningBankView> {
    public SelectOpeningBankPresenter(SelectOpeningBankView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     * 获取开户银行列表数据
     */
    public void getOpeningBank() {
        OkGo.<BaseResponse<List<BankBean>>>get(OPENING_BANK)
                .execute(new JsonCallback<BaseResponse<List<BankBean>>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<List<BankBean>>> response) {
                        mvpView.showOpeningBankList(response.body().data);
                    }
                });
    }
}
