package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class BankProvinceBean extends CoreBean{
    private String province_num;
    private String province_name;

    public String getProvince_num() {
        return province_num;
    }

    public void setProvince_num(String province_num) {
        this.province_num = province_num;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
}
