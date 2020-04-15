package com.fumiao.assistant.bean.merchant;

import com.fumiao.core.adapter.CoreBean;

public class MerchantBean extends CoreBean{
    private String id;
    private int merchant_type;
    private String merchant_id;
    private String name;
    private String update_time;
    private String create_time;
    private String phone;
    private int status; //0禁用 1正常 3作废
    private String province;
    private String city;
    private String area;
    private String address;
    private String category;
    private String salesman_account;
    private String merchant_type_name; // "小微商户"
    private String status_name; //"正常"
    private String salesman_name;
    private String pca; //"江西省/南昌市/西湖区"
    private String realname; //联系人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMerchant_type() {
        return merchant_type;
    }

    public void setMerchant_type(int merchant_type) {
        this.merchant_type = merchant_type;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSalesman_account() {
        return salesman_account;
    }

    public void setSalesman_account(String salesman_account) {
        this.salesman_account = salesman_account;
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

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getPca() {
        return pca;
    }

    public void setPca(String pca) {
        this.pca = pca;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
