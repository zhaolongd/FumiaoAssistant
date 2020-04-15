package com.fumiao.assistant.bean.home;

import com.fumiao.assistant.bean.merchant.MerchantBean;
import com.fumiao.assistant.bean.merchant.StoreBean;
import com.fumiao.core.adapter.CoreBean;

public class InComingDetailBean extends CoreBean{
    private String merchant_id;
    private String user_info;
    private PartsBean parts_info;
    private StoreBean store_info;
    private MemberBean member_info;
    private MerchantBean merchant_info;

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public PartsBean getParts_info() {
        return parts_info;
    }

    public void setParts_info(PartsBean parts_info) {
        this.parts_info = parts_info;
    }

    public StoreBean getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreBean store_info) {
        this.store_info = store_info;
    }

    public MemberBean getMember_info() {
        return member_info;
    }

    public void setMember_info(MemberBean member_info) {
        this.member_info = member_info;
    }

    public MerchantBean getMerchant_info() {
        return merchant_info;
    }

    public void setMerchant_info(MerchantBean merchant_info) {
        this.merchant_info = merchant_info;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }
}
