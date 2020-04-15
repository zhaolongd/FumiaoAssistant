package com.fumiao.assistant.mvp.main;

import com.fumiao.assistant.bean.home.LoginBean;
import com.fumiao.assistant.mvp.base.BaseView;
/**
 * Created by zhaolong on 2019/9/12.
 */
public interface LoginView extends BaseView {
    void loginSuccess(LoginBean data);
}
