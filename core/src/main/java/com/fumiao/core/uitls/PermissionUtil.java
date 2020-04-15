package com.fumiao.core.uitls;

import android.content.Context;

import com.tbruyelle.rxpermissions2.Permission;
import com.fumiao.core.R;


/**
 * Created by chee on 2018/3/26.
 */

public class PermissionUtil {

    private PermissionUtil() {
    }

    private static PermissionUtil instance;

    public static PermissionUtil getInstance() {
        if (instance == null) {
            instance = new PermissionUtil();
        }
        return instance;
    }

    public void check(Permission permission, Context context, String name, Callback callback) {
        if (permission.granted) {
            callback.onSucceed();
        } else if (permission.shouldShowRequestPermissionRationale) {
            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
        } else {
            ToastUtil.show(context.getString(R.string.not_permission).replace("$$", name));
            //Toast.makeText(context, context.getString(R.string.not_permission).replace("$$", name), Toast.LENGTH_SHORT);
        }
    }

    //回调
    public static interface Callback {
        void onSucceed();
    }

}
