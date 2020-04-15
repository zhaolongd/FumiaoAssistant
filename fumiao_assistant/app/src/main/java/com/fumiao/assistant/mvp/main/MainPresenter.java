package com.fumiao.assistant.mvp.main;

import android.app.Activity;

import com.fumiao.assistant.mvp.base.BasePresenter;


public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView mvpView, Activity activity) {
        super(mvpView, activity);
    }

}
