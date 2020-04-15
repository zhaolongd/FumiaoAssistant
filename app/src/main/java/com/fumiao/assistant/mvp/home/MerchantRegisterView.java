package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.mvp.base.BaseView;
/**
 * Created by zhaolong on 2019/9/12.
 */
public interface MerchantRegisterView extends BaseView {
    void sendVerificationCodeSuccess(String msg);
    void checkPhoneSuccess();
}
