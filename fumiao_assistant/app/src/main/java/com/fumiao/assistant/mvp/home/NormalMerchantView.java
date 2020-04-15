package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface NormalMerchantView extends BaseView{
    void uploadImageUrl(UploadImageBean data, int code, String originImage);
}
