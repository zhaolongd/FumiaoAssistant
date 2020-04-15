package com.fumiao.assistant.bean.merchant;

import java.util.List;

public class MerchantListBean {
    private String total;
    private String per_page;
    private String current_page;
    private String last_page;
    private List<MerchantBean> data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getLast_page() {
        return last_page;
    }

    public void setLast_page(String last_page) {
        this.last_page = last_page;
    }

    public List<MerchantBean> getData() {
        return data;
    }

    public void setData(List<MerchantBean> data) {
        this.data = data;
    }
}
