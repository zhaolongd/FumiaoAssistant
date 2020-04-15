package com.fumiao.assistant.mvp.home;

import android.app.Activity;
import android.util.Log;

import com.fumiao.assistant.bean.home.InComingBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 16:44
 *
 * @Description:
 */
public class MerchantsAuditListPresenter extends BasePresenter<MerchantsAuditListView> {

    public MerchantsAuditListPresenter(MerchantsAuditListView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     * type: app端
     * status: 状态（0：待进件 1：审核中，2：审核通过，3：审核驳回）
     */
    public void getDetails(String status){
        OkGo.<BaseResponse<List<InComingBean>>>post(INCOMING_LIST)
                .params("type", "app")
                .params("status", status)
                .execute(new JsonCallback<BaseResponse<List<InComingBean>>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<List<InComingBean>>> response) {
                     mvpView.showAuditList(response.body().data);
                    }

                    @Override
                    public void onError(Response<BaseResponse<List<InComingBean>>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }


}
