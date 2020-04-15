package com.fumiao.assistant.app;

import com.fumiao.core.app.CoreApp;
import com.fumiao.core.uitls.AppUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.xuexiang.xui.XUI;

/**
 * Created by zhaolong on 2019/9/10.
 */
public class BaseApp extends CoreApp {

    private static final String TAG = "Init";
    @Override
    public void onCreate() {
        super.onCreate();
        initBugly();
        initXUI();
    }

    private void initBugly(){
        // 获取当前包名
        String packageName = getSingle().getPackageName();
        // 获取当前进程名
        String processName = AppUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getSingle());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        Bugly.init(getSingle(), "ed57a79027", isDebug, strategy);
    }

    private void initXUI(){
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
    }
}
