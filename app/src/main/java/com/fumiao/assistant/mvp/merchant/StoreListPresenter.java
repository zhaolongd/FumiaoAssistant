package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;
import com.fumiao.assistant.bean.merchant.StoreListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class StoreListPresenter extends BasePresenter<StoreListView>{
    public StoreListPresenter(StoreListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMerchantStoreList(String merchant_id) {
        HttpParams httpParams = new HttpParams();
        OkGo.<BaseResponse<StoreListBean>>post(MERCHANT_STORE)
                .params(httpParams)
                .params("merchant_id", merchant_id)
                .execute(new JsonCallback<BaseResponse<StoreListBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreListBean>> response) {
                        mvpView.showStoreList(response.body().data);
                    }
                    @Override
                    public void onError(Response<BaseResponse<StoreListBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }

    public void getStoreList(String name, int page, int pagesize) {
        HttpParams httpParams = new HttpParams();
        if(name != null && !name.equals("")){
            httpParams.put("name", name);
        }
        httpParams.put("page", page);
        httpParams.put("limit", pagesize);
        OkGo.<BaseResponse<StoreListBean>>post(STORE_LIST)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<StoreListBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreListBean>> response) {
                        mvpView.showStoreList(response.body().data);
                    }
                    @Override
                    public void onError(Response<BaseResponse<StoreListBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}
