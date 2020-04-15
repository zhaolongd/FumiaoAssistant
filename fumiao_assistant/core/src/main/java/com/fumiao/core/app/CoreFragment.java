package com.fumiao.core.app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.fumiao.core.uitls.StatusManager;

/**
 * Created by chee on 2018/8/27.
 */
public class CoreFragment extends Fragment {


    private final StatusManager mStatusManager = new StatusManager();

    public void jumpActivity(Class clazz) {
        jumpActivity(clazz, null);
    }

    /**
     * 快捷跳转函数
     *
     * @param clazz
     * @param bundle
     */
    public void jumpActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clazz);
        if (bundle != null) {
            intent.putExtras( bundle);
        }
        startActivity(intent);
    }

    public void jumpActivityForResult(Class clazz, int code) {
        Intent intent = new Intent(getContext(), clazz);
        startActivityForResult(intent, code);
    }

    public void jumpActivityForResult(Class clazz, Bundle bundle, int code) {
        Intent intent = new Intent(getContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, code);
    }


    /**
     * 无数据显示空提示
     */
    public void showEmpty(Drawable drawable, CharSequence hint) {
        mStatusManager.showEmpty(getView(), drawable,hint);
    }

    public void hideEmpty() {
        mStatusManager.hideLayout();
    }

    /**
     * 无网络显示错误提示
     */
    public void showError() {
        mStatusManager.showError(getView());
    }

}
