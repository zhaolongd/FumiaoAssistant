package com.fumiao.assistant.mvp.merchant;

import android.app.Activity;
import com.fumiao.assistant.mvp.base.BasePresenter;

public class MerchantPresenter extends BasePresenter<MerchantView> {
    public MerchantPresenter(MerchantView mvpView, Activity activity) {
        super(mvpView, activity);
    }
}
