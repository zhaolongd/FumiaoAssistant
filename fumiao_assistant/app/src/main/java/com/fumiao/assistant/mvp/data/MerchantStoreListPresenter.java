package com.fumiao.assistant.mvp.data;

import android.app.Activity;

import com.fumiao.assistant.bean.data.MerchantStatisticsListBean;
import com.fumiao.assistant.bean.data.StoreStatisticsListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/13 0013 15:54
 */
public class MerchantStoreListPresenter extends BasePresenter<MerchantStoreListView> {
    public MerchantStoreListPresenter(MerchantStoreListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMerchantStatistics(String account_id, int pagesize, int page) {
        OkGo.<BaseResponse<MerchantStatisticsListBean>>get(MERCHANT_STATISTICS)
                .params("account_id", account_id)
                .params("pagesize", pagesize)
                .params("page", page)
                .execute(new JsonCallback<BaseResponse<MerchantStatisticsListBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MerchantStatisticsListBean>> response) {
                        mvpView.showMerchantStatisticsList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<MerchantStatisticsListBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }

    public void getStoreStatistics(String account_id, int pagesize, int page) {
        OkGo.<BaseResponse<StoreStatisticsListBean>>get(STORE_STATISTICS)
                .params("account_id", account_id)
                .params("pagesize", pagesize)
                .params("page", page)
                .execute(new JsonCallback<BaseResponse<StoreStatisticsListBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreStatisticsListBean>> response) {
                        mvpView.showStoreStatisticsList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<StoreStatisticsListBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }
}
