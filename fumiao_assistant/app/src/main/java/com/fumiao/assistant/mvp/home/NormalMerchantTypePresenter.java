package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.CateListBean;
import com.fumiao.assistant.bean.home.MerchantTypeListBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Author: XieBoss
 * Date: 2019/10/11 0011 18:41
 *
 * @Description:
 */
public class NormalMerchantTypePresenter extends BasePresenter<NormalMerchantTypeView> {

    public NormalMerchantTypePresenter(NormalMerchantTypeView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMerchantList() {
        OkGo.<BaseResponse<MerchantTypeListBean>>post(NORMAL_MERCHANT_LIST)
                .execute(new JsonCallback<BaseResponse<MerchantTypeListBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MerchantTypeListBean>> response) {
                        mvpView.showMerchantList(response.body().data);
                    }
                });
    }
}
