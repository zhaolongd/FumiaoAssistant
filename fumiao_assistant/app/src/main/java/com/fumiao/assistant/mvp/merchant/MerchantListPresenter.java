package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;
import com.fumiao.assistant.bean.merchant.MerchantListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class MerchantListPresenter extends BasePresenter<MerchantListView> {
    public MerchantListPresenter(MerchantListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     *name:搜索的关键字
     */
    public void getMerchantList(String name, int page, int pagesize){
        HttpParams httpParams = new HttpParams();
        if(name != null && !name.equals("")){
            httpParams.put("name", name);
        }
        httpParams.put("page", page);
        httpParams.put("limit", pagesize);
        OkGo.<BaseResponse<MerchantListBean>>post(MERCHANT_LIST)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<MerchantListBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MerchantListBean>> response) {
                        mvpView.showMerchantList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<MerchantListBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}
