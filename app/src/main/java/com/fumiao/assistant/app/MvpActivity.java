package com.fumiao.assistant.app;

import android.os.Bundle;
import com.fumiao.assistant.mvp.base.BasePresenter;


/**
 * Created by chee on 2018/6/4.
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();

        }
    }

}
