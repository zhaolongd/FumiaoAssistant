package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.DeviceDetailBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface DeviceDetailView  extends BaseView{
    void sendRelieveCodeSuccess(String msg);
    void showDeviceDetail(DeviceDetailBean data);
    void unbindDeviceSuccess(String msg);
}
