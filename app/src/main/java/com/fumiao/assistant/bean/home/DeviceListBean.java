package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;
import java.util.List;

public class DeviceListBean extends CoreBean{
    private List<DeviceBean> merchant_device;

    public List<DeviceBean> getMerchant_device() {
        return merchant_device;
    }

    public void setMerchant_device(List<DeviceBean> merchant_device) {
        this.merchant_device = merchant_device;
    }
}
