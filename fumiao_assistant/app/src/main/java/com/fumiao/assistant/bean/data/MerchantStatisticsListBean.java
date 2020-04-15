package com.fumiao.assistant.bean.data;

import java.util.List;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/17 0017 13:58
 */
public class MerchantStatisticsListBean {

    private List<MerchantStoreStatisticsBean> merchant_list;
    private int total;

    public List<MerchantStoreStatisticsBean> getMerchant_list() {
        return merchant_list;
    }

    public void setMerchant_list(List<MerchantStoreStatisticsBean> merchant_list) {
        this.merchant_list = merchant_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
