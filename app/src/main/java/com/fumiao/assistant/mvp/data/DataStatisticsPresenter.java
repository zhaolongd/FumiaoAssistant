package com.fumiao.assistant.mvp.data;

import android.app.Activity;
import com.fumiao.assistant.bean.data.DataStatisticsBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/12 0012 10:39
 */
public class DataStatisticsPresenter extends BasePresenter<DataStatisticsView> {

    public DataStatisticsPresenter(DataStatisticsView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     *statistics_flag: 1商户，2门店
     *team_agency_code: 团队机构编码
     * account_id:员工账户ID
     */
    public void getDataStatistics(int statistics_flag, String team_agency_code, String account_id){
        HttpParams httpParams = new HttpParams();
        httpParams.put("statistics_flag", statistics_flag);
        if(team_agency_code != null && !team_agency_code.equals("")){
            httpParams.put("team_agency_code", team_agency_code);
        }
        if(account_id != null && !account_id.equals("")){
            httpParams.put("account_id", account_id);
        }
        OkGo.<BaseResponse<DataStatisticsBean>>get(DATA_STATISTICS)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<DataStatisticsBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<DataStatisticsBean>> response) {
                        mvpView.showDataStatistics(response.body().data);
                    }
                    @Override
                    public void onError(Response<BaseResponse<DataStatisticsBean>> response) {
                        super.onError(response);
                        mvpView.stateChangeOnError();
                    }
                });
    }

}
