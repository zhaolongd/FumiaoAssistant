package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface NormalMerchantBusinessView  extends BaseView{
    void uploadImageUrl(UploadImageBean data, int code, String originalImage);
    void ocrIDCardSuccess(IDCardOcrBean data, String originImage);
}
