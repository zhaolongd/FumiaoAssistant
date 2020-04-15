package com.fumiao.assistant.mvp.home;

import android.app.Activity;
import com.fumiao.assistant.bean.home.BankCityBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import java.util.List;

public class SelectBankCityPresenter extends BasePresenter<SelectBankCityView> {
    public SelectBankCityPresenter(SelectBankCityView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     * 获取开户银行城市的数据
     */
    public void getBankCity(String province_code) {
        OkGo.<BaseResponse<List<BankCityBean>>>post(BANK_CITY)
                .params("province_num", province_code)
                .execute(new JsonCallback<BaseResponse<List<BankCityBean>>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<List<BankCityBean>>> response) {
                        mvpView.showBankCityList(response.body().data);
                    }
                });
    }
}
