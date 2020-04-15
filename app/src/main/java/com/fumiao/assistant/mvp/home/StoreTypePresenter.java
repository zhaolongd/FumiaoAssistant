package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.CateListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class StoreTypePresenter extends BasePresenter<StoreTypeView> {

    public StoreTypePresenter(StoreTypeView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getCateList() {
        OkGo.<BaseResponse<CateListBean>>post(CATE_LIST)
                .execute(new JsonCallback<BaseResponse<CateListBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CateListBean>> response) {
                        mvpView.showCateList(response.body().data);
                    }
                });
    }
}
