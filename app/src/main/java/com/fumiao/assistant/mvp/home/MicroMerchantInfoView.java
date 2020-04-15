package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.InComingDetailBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BaseView;

/**
 * Created by zhaolong on 2019/9/16.
 */
public interface MicroMerchantInfoView extends BaseView{
    void uploadImageUrl(UploadImageBean data, int code, String originImage);
    void showInComingDetail(InComingDetailBean data);
}
