package com.fumiao.core.uitls;

import android.view.Gravity;
import com.hjq.toast.ToastUtils;


/**
 * Created by zhaolong on 2019/12/17.
 */

public class ToastUtil {

    public static void show(String message) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.show(message);
    }
}
