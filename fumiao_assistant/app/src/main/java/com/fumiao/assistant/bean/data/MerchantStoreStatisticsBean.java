package com.fumiao.assistant.bean.data;

import com.fumiao.core.adapter.CoreBean;

/**
 * Created by zhaolong.
 * Description: 商户门店个人交易数据
 * Date: 2020/3/13 0013 15:47
 */
public class MerchantStoreStatisticsBean extends CoreBean {
    private String merchant_id;
    private String store_id;
    private String name;
    private Datas datas;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Datas getDatas() {
        return datas;
    }

    public void setDatas(Datas datas) {
        this.datas = datas;
    }

    public static class Datas{
        private String yesterday_total_amount;
        private String yesterday_total_count;
        private String week_mom_amount;

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

        public String getWeek_mom_amount() {
            return week_mom_amount;
        }

        public void setWeek_mom_amount(String week_mom_amount) {
            this.week_mom_amount = week_mom_amount;
        }
    }
}
