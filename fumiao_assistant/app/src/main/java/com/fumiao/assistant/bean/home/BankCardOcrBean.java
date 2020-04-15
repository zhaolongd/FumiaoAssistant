package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class BankCardOcrBean extends CoreBean{
    private List<BankCardBean> bank_cards; //检测出证件的数组 注：如果没有检测出证件则为空数组
    private String error_message; //当请求失败时才会返回此字符串，具体返回内容见后续错误信息章节。否则此字段不存在。

    public List<BankCardBean> getBank_cards() {
        return bank_cards;
    }

    public void setBank_cards(List<BankCardBean> bank_cards) {
        this.bank_cards = bank_cards;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public static class BankCardBean {
        private String number;
        private String bank;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }
    }
}
