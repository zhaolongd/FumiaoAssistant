package com.fumiao.assistant.bean.data;

/**
 * "today_count": 0,
 * "today_sum_totals": "0.00元",
 * "today_merchant_ids": 0,
 * "yestoday_count": 0,
 * "yestoday_sum_totals": "0.00元",
 * "yestoday_merchant_ids": 0,
 * "month_count": 17,
 * "month_sum_totals": "0.05元"
 */

public class SalesmanStatisticsBean {
    private String today_count;
    private String today_sum_totals;
    private String today_merchant_ids;
    private String yestoday_count;
    private String yestoday_sum_totals;
    private String yestoday_merchant_ids;
    private String month_count;
    private String month_sum_totals;

    public String getToday_count() {
        return today_count;
    }

    public void setToday_count(String today_count) {
        this.today_count = today_count;
    }

    public String getToday_sum_totals() {
        return today_sum_totals;
    }

    public void setToday_sum_totals(String today_sum_totals) {
        this.today_sum_totals = today_sum_totals;
    }

    public String getToday_merchant_ids() {
        return today_merchant_ids;
    }

    public void setToday_merchant_ids(String today_merchant_ids) {
        this.today_merchant_ids = today_merchant_ids;
    }

    public String getYestoday_count() {
        return yestoday_count;
    }

    public void setYestoday_count(String yestoday_count) {
        this.yestoday_count = yestoday_count;
    }

    public String getYestoday_sum_totals() {
        return yestoday_sum_totals;
    }

    public void setYestoday_sum_totals(String yestoday_sum_totals) {
        this.yestoday_sum_totals = yestoday_sum_totals;
    }

    public String getYestoday_merchant_ids() {
        return yestoday_merchant_ids;
    }

    public void setYestoday_merchant_ids(String yestoday_merchant_ids) {
        this.yestoday_merchant_ids = yestoday_merchant_ids;
    }

    public String getMonth_count() {
        return month_count;
    }

    public void setMonth_count(String month_count) {
        this.month_count = month_count;
    }

    public String getMonth_sum_totals() {
        return month_sum_totals;
    }

    public void setMonth_sum_totals(String month_sum_totals) {
        this.month_sum_totals = month_sum_totals;
    }
}
