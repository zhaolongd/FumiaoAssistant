package com.fumiao.assistant.bean.merchant;

import com.fumiao.core.adapter.CoreBean;

/**
 * "name": "test101",
 * "serial_number": "1120191015988424",
 * "store_status": 1,
 * "create_time": "2019-10-15 14:40:22",
 * "address": "将顶焦度计多久多久",
 * "province": "110000000000",
 * "city": "110100000000",
 * "area": "110101000000",
 * "merchant_type": 2,
 * "phone": "13753558511",
 * "member_name": "张三",
 * "merchant_status": 1,
 * "salesman_name": "张三",
 * "merchant_name": "test101",
 * "merchant_id": "366079159148434",
 * "realname": "",
 * "merchant_type_name": "普通商户",
 * "status_name": "启用",
 * "pca": "北京市\/北京市\/东城区"
 */
public class StoreDetailBean extends CoreBean{
    private String name;
    private String serial_number;
    private String store_status;
    private String create_time;
    private String address;
    private String merchant_type;
    private String phone;
    private String member_name;
    private String merchant_status;
    private String salesman_name;
    private String merchant_name;
    private String merchant_id;
    private String realname;
    private String merchant_type_name;
    private String status_name;
    private String pca;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getStore_status() {
        return store_status;
    }

    public void setStore_status(String store_status) {
        this.store_status = store_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMerchant_type() {
        return merchant_type;
    }

    public void setMerchant_type(String merchant_type) {
        this.merchant_type = merchant_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMerchant_status() {
        return merchant_status;
    }

    public void setMerchant_status(String merchant_status) {
        this.merchant_status = merchant_status;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMerchant_type_name() {
        return merchant_type_name;
    }

    public void setMerchant_type_name(String merchant_type_name) {
        this.merchant_type_name = merchant_type_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getPca() {
        return pca;
    }

    public void setPca(String pca) {
        this.pca = pca;
    }
}
