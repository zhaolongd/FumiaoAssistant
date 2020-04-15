package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;
import com.fumiao.assistant.bean.merchant.StoreDetailBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class StoreDetailPresenter extends BasePresenter<StoreDetailView>{
    public StoreDetailPresenter(StoreDetailView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getStoreDetail(String store_id){
        OkGo.<BaseResponse<StoreDetailBean>>post(STORE_DETAIL)
                .params("store_id", store_id)
                .execute(new JsonCallback<BaseResponse<StoreDetailBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreDetailBean>> response) {
                        mvpView.showStoreDetail(response.body().data);
                    }
                });
    }
}
