package com.fumiao.assistant.mvp.merchant;

import com.fumiao.assistant.bean.home.DeviceBean;
import com.fumiao.assistant.mvp.base.BaseView;
import java.util.List;

public interface StoreDeviceListView extends BaseView{
    void showDeviceList(List<DeviceBean> data);
    void stateChangeOnError();
}
