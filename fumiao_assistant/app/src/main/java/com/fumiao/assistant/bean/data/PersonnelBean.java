package com.fumiao.assistant.bean.data;

import com.fumiao.core.adapter.CoreBean;

/**
 * Created by zhaolong.
 * Description: 人员交易信息
 * Date: 2020/3/4 0004 11:04
 */
public class PersonnelBean extends CoreBean {
    private Peopel peopel;
    private String agency_code;
    private String agency_name;
    private Datas datas;

    public Peopel getPeopel() {
        return peopel;
    }

    public void setPeopel(Peopel peopel) {
        this.peopel = peopel;
    }

    public String getAgency_code() {
        return agency_code;
    }

    public void setAgency_code(String agency_code) {
        this.agency_code = agency_code;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public Datas getDatas() {
        return datas;
    }

    public void setDatas(Datas datas) {
        this.datas = datas;
    }

    public static class Peopel{
        private String id;
        private String agent_id;
        private String agency_code;
        private String mobile;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAgent_id() {
            return agent_id;
        }

        public void setAgent_id(String agent_id) {
            this.agent_id = agent_id;
        }

        public String getAgency_code() {
            return agency_code;
        }

        public void setAgency_code(String agency_code) {
            this.agency_code = agency_code;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Datas{
        private String yesterday_total_amount;
        private String yesterday_total_count;
        private String cumulative_amount;
        private String yesterday_active_merchants;
        private String month_monthly_growth_rate;
        private String yesterday_active_stores;

        public String getYesterday_total_amount() {
            return yesterday_total_amount;
        }

        public void setYesterday_total_amount(String yesterday_total_amount) {
            this.yesterday_total_amount = yesterday_total_amount;
        }

        public String getYesterday_total_count() {
            return yesterday_total_count;
        }

        public void setYesterday_total_count(String yesterday_total_count) {
            this.yesterday_total_count = yesterday_total_count;
        }

        public String getCumulative_amount() {
            return cumulative_amount;
        }

        public void setCumulative_amount(String cumulative_amount) {
            this.cumulative_amount = cumulative_amount;
        }

        public String getYesterday_active_merchants() {
            return yesterday_active_merchants;
        }

        public void setYesterday_active_merchants(String yesterday_active_merchants) {
            this.yesterday_active_merchants = yesterday_active_merchants;
        }

        public String getMonth_monthly_growth_rate() {
            return month_monthly_growth_rate;
        }

        public void setMonth_monthly_growth_rate(String month_monthly_growth_rate) {
            this.month_monthly_growth_rate = month_monthly_growth_rate;
        }

        public String getYesterday_active_stores() {
            return yesterday_active_stores;
        }

        public void setYesterday_active_stores(String yesterday_active_stores) {
            this.yesterday_active_stores = yesterday_active_stores;
        }
    }
}
