package com.fumiao.assistant.bean.data;

import com.fumiao.core.adapter.CoreBean;

/**
 * Created by zhaolong.
 * Description: 团队数据
 * Date: 2020/3/3 0003 11:05
 */
public class TeamBean extends CoreBean {
    private String agency_code;
    private String agency_name;
    private String peoples;
    private Datas datas;

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

    public String getPeoples() {
        return peoples;
    }

    public void setPeoples(String peoples) {
        this.peoples = peoples;
    }

    public Datas getDatas() {
        return datas;
    }

    public void setDatas(Datas datas) {
        this.datas = datas;
    }

    public static class Datas{
        private String yesterday_total_amount;
        private String yesterday_mom_amount;
        private String yesterday_per_capita_amount;
        private String yesterday_active_merchants;
        private String yesterday_mom_active_merchants;
        private String yesterday_per_capita_active_merchants;
        private String yesterday_active_stores;
        private String yesterday_mom_active_stores;
        private String yesterday_per_capita_active_stores;

        public String getYesterday_total_amount() {
            return yesterday_total_amount;
        }

        public void setYesterday_total_amount(String yesterday_total_amount) {
            this.yesterday_total_amount = yesterday_total_amount;
        }

        public String getYesterday_mom_amount() {
            return yesterday_mom_amount;
        }

        public void setYesterday_mom_amount(String yesterday_mom_amount) {
            this.yesterday_mom_amount = yesterday_mom_amount;
        }

        public String getYesterday_per_capita_amount() {
            return yesterday_per_capita_amount;
        }

        public void setYesterday_per_capita_amount(String yesterday_per_capita_amount) {
            this.yesterday_per_capita_amount = yesterday_per_capita_amount;
        }

        public String getYesterday_active_merchants() {
            return yesterday_active_merchants;
        }

        public void setYesterday_active_merchants(String yesterday_active_merchants) {
            this.yesterday_active_merchants = yesterday_active_merchants;
        }

        public String getYesterday_mom_active_merchants() {
            return yesterday_mom_active_merchants;
        }

        public void setYesterday_mom_active_merchants(String yesterday_mom_active_merchants) {
            this.yesterday_mom_active_merchants = yesterday_mom_active_merchants;
        }

        public String getYesterday_per_capita_active_merchants() {
            return yesterday_per_capita_active_merchants;
        }

        public void setYesterday_per_capita_active_merchants(String yesterday_per_capita_active_merchants) {
            this.yesterday_per_capita_active_merchants = yesterday_per_capita_active_merchants;
        }

        public String getYesterday_active_stores() {
            return yesterday_active_stores;
        }

        public void setYesterday_active_stores(String yesterday_active_stores) {
            this.yesterday_active_stores = yesterday_active_stores;
        }

        public String getYesterday_mom_active_stores() {
            return yesterday_mom_active_stores;
        }

        public void setYesterday_mom_active_stores(String yesterday_mom_active_stores) {
            this.yesterday_mom_active_stores = yesterday_mom_active_stores;
        }

        public String getYesterday_per_capita_active_stores() {
            return yesterday_per_capita_active_stores;
        }

        public void setYesterday_per_capita_active_stores(String yesterday_per_capita_active_stores) {
            this.yesterday_per_capita_active_stores = yesterday_per_capita_active_stores;
        }
    }
}
