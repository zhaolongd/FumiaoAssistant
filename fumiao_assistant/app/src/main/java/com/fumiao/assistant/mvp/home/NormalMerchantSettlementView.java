package com.fumiao.assistant.mvp.home;
import com.fumiao.assistant.bean.home.BankCardOcrBean;
import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BaseView;

/**
 * Author: XieBoss
 * Date: 2019/10/8 0008 17:00
 *
 * @Description:
 */
public interface NormalMerchantSettlementView extends BaseView{
    void uploadImageUrl(UploadImageBean data, int code, String originalImage);
    void ocrIDCardSuccess(IDCardOcrBean data, String originImage);
    void ocrBankCardSuccess(BankCardOcrBean data, String originImage);
}
