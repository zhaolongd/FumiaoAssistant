package com.fumiao.assistant.bean.home;

/**
 * Author: XieBoss
 * Date: 2019/9/20 0020 15:53
 *
 * @Description:
 */
public class LoginBean {

        private InfoBean info;
        private String msg;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public static class InfoBean {
            /**
             * "id": 2,
             * "num": 54321,
             * "agency_code": "86000000000000000000000",
             * "mobile": "18172888878",
             * "password": "03a22afd98159b9c4f70c080aebe2b89",
             * "name": "韦显光",
             * "status": 1,
             * "create_time": 1575619188,
             * "update_time": 0,
             * "login_time": 1575958716,
             * "login_ip": "220.175.71.19",
             * "director": 2
             */
            private int id;
            private String num;
            private String agency_code;
            private String mobile;
            private String password;
            private String name;
            private String status;
            private String create_time;
            private String update_time;
            private String login_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getAgency_code() {
                return agency_code;
            }

            public void setAgency_code(String agency_code) {
                this.agency_code = agency_code;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getLogin_time() {
                return login_time;
            }

            public void setLogin_time(String login_time) {
                this.login_time = login_time;
            }
        }
}
