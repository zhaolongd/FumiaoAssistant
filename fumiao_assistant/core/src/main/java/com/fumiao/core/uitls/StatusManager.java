package com.fumiao.core.uitls;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.fumiao.core.R;
import com.fumiao.core.widget.HintLayout;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/04/18
 *    desc   : 界面状态管理类
 */
public final class StatusManager {

    /** 提示布局 */
    private HintLayout mHintLayout;


    /**
     * 显示空提示
     */
    public void showEmpty(View view,Drawable drawable, CharSequence hint) {
        showLayout(view,drawable,hint);
    }

    /**
     * 显示错误提示
     */
    public void showError(View view) {
        // 判断当前网络是否可用
        if (isNetworkAvailable(view.getContext())) {
            showLayout(view, R.drawable.icon_hint_request, R.string.listview_header_hint_normal);
        } else {
            showLayout(view, R.drawable.icon_hint_nerwork, R.string.hint_layout_error_network);
        }
    }

    /**
     * 显示自定义提示
     */

    public void showLayout(View view, @DrawableRes int drawableId, @StringRes int stringId) {
        showLayout(view, ContextCompat.getDrawable(view.getContext(), drawableId), view.getResources().getString(stringId));
    }

    public void hideLayout(){
        if(mHintLayout != null && mHintLayout.isShow()){
            mHintLayout.hide();
        }
    }

    public void showLayout(View view, Drawable drawable, CharSequence hint) {

        if (mHintLayout == null) {

            if (view instanceof HintLayout) {
                mHintLayout = (HintLayout) view;
            } else if (view instanceof ViewGroup) {
                mHintLayout = findHintLayout((ViewGroup) view);
            }

            if (mHintLayout == null) {
                // 必须在布局中定义一个 HintLayout
                throw new IllegalStateException("You didn't add this HintLayout to your layout");
            }
        }
        mHintLayout.show();
        mHintLayout.setIcon(drawable);
        mHintLayout.setHint(hint);
    }

    /**
     * 智能获取布局中的 HintLayout 对象
     */
    private static HintLayout findHintLayout(ViewGroup group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View view = group.getChildAt(i);
            if ((view instanceof HintLayout)) {
                return (HintLayout) view;
            } else if (view instanceof ViewGroup) {
                HintLayout layout = findHintLayout((ViewGroup) view);
                if (layout != null) {
                    return layout;
                }
            }
        }
        return null;
    }

    /**
     * 判断网络功能是否可用
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    private static boolean isNetworkAvailable(Context context){
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}