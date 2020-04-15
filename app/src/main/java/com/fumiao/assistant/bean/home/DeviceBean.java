package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class DeviceBean extends CoreBean{
    /**
     * "device_type_name": "收款台卡",
     * "remark": "嗯我现在收款设备",
     * "store_name": "嗯我现在",
     * "device_sn": "FM2005054083072",
     * "device_type": 2
     */
    private String device_type_name;
    private String remark;
    private String store_name;
    private String device_sn;
    private int device_type; //1:收款音响 2：收款卡台

    public String getDevice_type_name() {
        return device_type_name;
    }

    public void setDevice_type_name(String device_type_name) {
        this.device_type_name = device_type_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getDevice_sn() {
        return device_sn;
    }

    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }
}
