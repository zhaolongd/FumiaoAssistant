package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.BankCardOcrBean;
import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.InComingResultBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BaseView;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 11:32
 *
 * @Description:
 */
public interface MicroMerchantAuthenticationView extends BaseView{
    void uploadImageUrl(UploadImageBean data, int code, String originalImage);
    void inComingSuccess(InComingResultBean data);
    void rejectInComingSuccess(InComingResultBean data);
    void ocrIDCardSuccess(IDCardOcrBean data, String originImage);
    void ocrBankCardSuccess(BankCardOcrBean data, String originImage);
}
