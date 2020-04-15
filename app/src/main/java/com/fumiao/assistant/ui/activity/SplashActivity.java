package com.fumiao.assistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;

/**
 * Created by zhaolong on 2019/9/11.
 */
public class SplashActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        }
        setContentView(R.layout.activity_splash, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpActivity(MainActivity.class);
                finish();
            }
        }, 1000);
    }
}
