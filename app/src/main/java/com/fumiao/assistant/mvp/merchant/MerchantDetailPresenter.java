package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;
import com.fumiao.assistant.bean.merchant.MerchantDetailBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class MerchantDetailPresenter extends BasePresenter<MerchantDetailView> {
    public MerchantDetailPresenter(MerchantDetailView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMerchantDetail(String id){
        OkGo.<BaseResponse<MerchantDetailBean>>post(MERCHANT_DETAIL_INFO)
                .params("merchant_id", id)
                .execute(new JsonCallback<BaseResponse<MerchantDetailBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MerchantDetailBean>> response) {
                        mvpView.showMerchantDetail(response.body().data);
                        }
                });
    }
}
