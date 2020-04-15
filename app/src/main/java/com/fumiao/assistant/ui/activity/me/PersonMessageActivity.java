package com.fumiao.assistant.ui.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.mvp.me.PersonMessagePresenter;
import com.fumiao.assistant.mvp.me.PersonMessageView;

/**
 * Author: XieBoss
 * Date: 2019/9/17 0017 14:07
 *
 * @Description:
 */
public class PersonMessageActivity extends MvpActivity<PersonMessagePresenter> implements PersonMessageView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        init();
    }

    @Override
    protected PersonMessagePresenter createPresenter() {
        return new PersonMessagePresenter(this,this);
    }

    private void init() {
        setTitle("个人中心");
    }


}
