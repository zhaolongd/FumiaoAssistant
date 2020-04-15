package com.fumiao.assistant.bean.data;

import java.util.List;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/17 0017 14:00
 */
public class StoreStatisticsListBean {
    private List<MerchantStoreStatisticsBean> store_list;
    private int total;

    public List<MerchantStoreStatisticsBean> getStore_list() {
        return store_list;
    }

    public void setStore_list(List<MerchantStoreStatisticsBean> store_list) {
        this.store_list = store_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
