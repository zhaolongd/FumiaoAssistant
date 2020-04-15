package com.fumiao.assistant.mvp.me;

import android.app.Activity;

import com.fumiao.assistant.mvp.base.BasePresenter;

/**
 * Author: XieBoss
 * Date: 2019/9/17 0017 14:09
 *
 * @Description:
 */
public class PersonMessagePresenter extends BasePresenter<PersonMessageView> {

    public PersonMessagePresenter(PersonMessageView mvpView, Activity activity) {
        super(mvpView, activity);
    }
}
