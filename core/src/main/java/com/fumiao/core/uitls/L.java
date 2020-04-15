package com.fumiao.core.uitls;

import com.orhanobut.logger.Logger;
import com.fumiao.core.app.CoreApp;


/**
 * Created by chee on 2018/5/12.
 * 日志输出控制器
 */
public class L {

    private static String TAG = "OkGo";

    public static void e(String tag, String msg) {
        if (CoreApp.isDebug) {
            Logger.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (CoreApp.isDebug) {
            Logger.e(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (CoreApp.isDebug) {
            Logger.d(tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void i(String msg) {
        if (CoreApp.isDebug) {
            Logger.i(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (CoreApp.isDebug) {
            Logger.w(tag, msg);
        }
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

}
