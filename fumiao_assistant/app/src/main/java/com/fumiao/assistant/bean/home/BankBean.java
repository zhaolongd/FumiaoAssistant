package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class BankBean extends CoreBean{
    private String interbank_code;
    private String bank_name;

    public String getInterbank_code() {
        return interbank_code;
    }

    public void setInterbank_code(String interbank_code) {
        this.interbank_code = interbank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
