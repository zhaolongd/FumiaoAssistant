package com.fumiao.assistant.config;

public interface KeyConfig {
    String IS_LOGIN = "is_login"; //标识是否登录状态
    String USER_PHONE = "user_phone";//登录手机号码
    int LOGIN_SUCCESS = 2001;//登录成功
    int LOGIN_EXIT = 2002; //退出登录
    String MERCHANT_INCOMING_PAGE = "com.fumiao.assistant.ui.activity.home.MerchantInComingActivity";
    String MERCHANT_AUDIT_PAGE = "com.fumiao.assistant.ui.activity.home.MerchantAuditActivity";
    String MAIN_PAGE = "com.fumiao.assistant.ui.activity.MainActivity";
}
