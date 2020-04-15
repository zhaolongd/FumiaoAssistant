package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class SalesmanInfoBean extends CoreBean{
    private String incoming_parts_count;
    private String total_sum;
    private String total_count;

    public String getIncoming_parts_count() {
        return incoming_parts_count;
    }

    public void setIncoming_parts_count(String incoming_parts_count) {
        this.incoming_parts_count = incoming_parts_count;
    }

    public String getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(String total_sum) {
        this.total_sum = total_sum;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
}
