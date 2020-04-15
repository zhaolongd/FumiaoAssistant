package com.fumiao.core.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import com.fumiao.core.uitls.AppManager;
import com.fumiao.core.uitls.StatusManager;

/**
 * Created by chee on 2018/8/21.
 */

public abstract class CoreActivity extends AppCompatActivity {

    //页面标记 方便跳转
    String acTag = "";
    public Context context;
    private final StatusManager mStatusManager = new StatusManager();

    public void setTag(String tag) {
        this.acTag = tag;
    }

    public String getTag() {
        return acTag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
//        ActivityStackManager.getInstance().onCreated(this);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ActivityStackManager.getInstance().onDestroyed(this);
        AppManager.getAppManager().removeActivity(this);
    }


    public void jumpActivity(Class clazz) {
        jumpActivity(clazz, null);
    }

    public void jumpActivityForResult(Class clazz, int code) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, code);
    }

    public void jumpActivityForResult(Class clazz, Bundle bundle, int code) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, code);
    }

    /**
     * 快捷跳转函数
     *
     * @param clazz
     * @param bundle
     */
    public void jumpActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void onResult(Object data) {
    }

    public abstract void showLoad();

    public abstract void hintLoad();


    /**
     * 和 setContentView 对应的方法
     */
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

    /**
     * 无数据显示空提示
     */
    public void showEmpty(Drawable drawable, CharSequence hint) {
        mStatusManager.showEmpty(getContentView(), drawable,hint);
    }

    public void hideEmpty() {
        mStatusManager.hideLayout();
    }

    /**
     * 无网络显示错误提示
     */
    public void showError() {
        mStatusManager.showError(getContentView());
    }
}
