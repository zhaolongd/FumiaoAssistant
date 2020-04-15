package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class BankCityBean extends CoreBean{
    private String city_num;
    private String city_name;

    public String getCity_num() {
        return city_num;
    }

    public void setCity_num(String city_num) {
        this.city_num = city_num;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
