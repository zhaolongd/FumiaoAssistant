package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class IDCardOcrBean extends CoreBean{
    private String error_message; //当请求失败时才会返回此字符串，具体返回内容见后续错误信息章节。否则此字段不存在。
    private List<CardBean> cards; //检测出证件的数组 注：如果没有检测出证件则为空数组
    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public List<CardBean> getCards() {
        return cards;
    }

    public void setCards(List<CardBean> cards) {
        this.cards = cards;
    }

    public static class CardBean {
        private String address;
        private String birthday;
        private String gender;
        private String id_card_number;
        private String name;
        private String race;
        private int type;
        private String side;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }
    }
}
