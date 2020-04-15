package com.fumiao.assistant.mvp.home;

import com.fumiao.assistant.bean.home.DeviceListBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface DeviceListView extends BaseView {
    void showDeviceList(DeviceListBean data);
    void stateChangeOnError();
}
