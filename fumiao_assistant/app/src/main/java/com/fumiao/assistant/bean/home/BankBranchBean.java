package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class BankBranchBean  extends CoreBean{
    private String interbank_num;
    private String interbank_name;

    public String getInterbank_num() {
        return interbank_num;
    }

    public void setInterbank_num(String interbank_num) {
        this.interbank_num = interbank_num;
    }

    public String getInterbank_name() {
        return interbank_name;
    }

    public void setInterbank_name(String interbank_name) {
        this.interbank_name = interbank_name;
    }
}
