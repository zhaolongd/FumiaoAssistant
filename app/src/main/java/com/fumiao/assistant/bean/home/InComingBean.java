package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

/**
 * Author: XieBoss
 * Date: 2019/9/19 0019 16:10
 * 商户审核管理
 * @Description:
 */
public class InComingBean extends CoreBean {


        /**
         * id : 11
         * merchant_id : 366079158121568
         * passageway_id : 0
         * content : {"cate_name":"美食,小吃快餐","mecDisNm":"绝味鸭脖","mblNo":"18779010813","operationalType":"01","haveLicenseNo":"01","mecTypeFlag":"00","wxQrcodeList":"0.38","aliQrcodeList":"0.38","thousandQrcodeList":"0.38","highestQrcodeList":"0.60","settleType":"04","cprRegAddr":"xxxxxxx","regProvCd":"510000000000","regCityCd":"511900000000","regDistCd":"511922000000","mccCd":"5812","identityTyp":"00","legalPersonLicStt":"","legalPersonLicEnt":"","actNm":"老刘","actTyp":"01","stmManIdNo":"360425199402223421","accountLicStt":"","accountLicEnt":"","actNo":"6527 1120 3416 5569 212","lbnkNo":"102290000277","lbnkNm":"中国工商银行上海市万象城支行","legalPersonidPositivePic":"/uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png","legalPersonidOppositePic":"/uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png","bankCardPositivePic":"/uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png","bankCardOppositePic":"","merchantAgreementPic":"","storePic":"/uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg","businessPlacePic":"/uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg","insideScenePic":"/uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg","csTelNo":"18779010813","identityName":"老刘","identityNo":"360425199402223421","qrcodeList":[{"rate":"0.38","rateType":"01"},{"rate":"0.38","rateType":"02"},{"rate":"0.38","rateType":"06"},{"rate":"0.60","rateType":"07"}]}
         * status : 3
         * update_time : 0
         * create_time : 1568960151
         * business_license :
         * id_card_front : /uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
         * id_card_back : /uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
         * bank_card_front : /uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
         * bank_card_back :
         * business_license_id : 0
         * id_card : 4294967295
         * store_id : 129
         * failure_msg : 费率必须在{0.21}~{0.21}之间
         * application_id :
         * mno : 0
         * income_resp_info : null
         * store_name : 绝味鸭脖
         * status_name : 审核驳回
         * legalPersonidPositivePic : http://api.coresystem.51fumiao.com/uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
         * legalPersonidOppositePic : http://api.coresystem.51fumiao.com/uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
         * bankCardPositivePic : http://api.coresystem.51fumiao.com/uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
         * bankCardOppositePic : http://api.coresystem.51fumiao.com
         * storePic : http://api.coresystem.51fumiao.com/uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg
         * businessPlacePic : http://api.coresystem.51fumiao.com/uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg
         * insideScenePic : http://api.coresystem.51fumiao.com/uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg
         */

        private int id;
        private String merchant_id;
        private int passageway_id;
        private ContentBean content;
        private int status;
        private int update_time;
        private int create_time;
        private String merchant_type_name;
        private String business_license;
        private String id_card_front;
        private String id_card_back;
        private String bank_card_front;
        private String bank_card_back;
        private String business_license_id;
        private int merchant_type;
        private String id_card;
        private int store_id;
        private String failure_msg;
        private String application_id;
        private String mno;
        private int income_resp_info; //0不可付款   1可付款
        private String store_name;
        private String status_name;
        private String legalPersonidPositivePic;
        private String legalPersonidOppositePic;
        private String bankCardPositivePic;
        private String bankCardOppositePic;
        private String storePic;
        private String businessPlacePic;
        private String insideScenePic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMerchant_type_name() {
        return merchant_type_name;
        }

        public void setMerchant_type_name(String merchant_type_name) {
        this.merchant_type_name = merchant_type_name;
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

        public int getPassageway_id() {
            return passageway_id;
        }

        public void setPassageway_id(int passageway_id) {
            this.passageway_id = passageway_id;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }

        public String getId_card_front() {
            return id_card_front;
        }

        public void setId_card_front(String id_card_front) {
            this.id_card_front = id_card_front;
        }

        public String getId_card_back() {
            return id_card_back;
        }

        public void setId_card_back(String id_card_back) {
            this.id_card_back = id_card_back;
        }

        public String getBank_card_front() {
            return bank_card_front;
        }

        public void setBank_card_front(String bank_card_front) {
            this.bank_card_front = bank_card_front;
        }

        public String getBank_card_back() {
            return bank_card_back;
        }

        public void setBank_card_back(String bank_card_back) {
            this.bank_card_back = bank_card_back;
        }

        public String getBusiness_license_id() {
            return business_license_id;
        }

        public void setBusiness_license_id(String business_license_id) {
            this.business_license_id = business_license_id;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getFailure_msg() {
            return failure_msg;
        }

        public void setFailure_msg(String failure_msg) {
            this.failure_msg = failure_msg;
        }

        public String getApplication_id() {
            return application_id;
        }

        public void setApplication_id(String application_id) {
            this.application_id = application_id;
        }

        public String getMno() {
            return mno;
        }

        public void setMno(String mno) {
            this.mno = mno;
        }

        public int getIncome_resp_info() {
            return income_resp_info;
        }

        public void setIncome_resp_info(int income_resp_info) {
            this.income_resp_info = income_resp_info;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getLegalPersonidPositivePic() {
            return legalPersonidPositivePic;
        }

        public void setLegalPersonidPositivePic(String legalPersonidPositivePic) {
            this.legalPersonidPositivePic = legalPersonidPositivePic;
        }

        public String getLegalPersonidOppositePic() {
            return legalPersonidOppositePic;
        }

        public void setLegalPersonidOppositePic(String legalPersonidOppositePic) {
            this.legalPersonidOppositePic = legalPersonidOppositePic;
        }

        public String getBankCardPositivePic() {
            return bankCardPositivePic;
        }

        public void setBankCardPositivePic(String bankCardPositivePic) {
            this.bankCardPositivePic = bankCardPositivePic;
        }

        public String getBankCardOppositePic() {
            return bankCardOppositePic;
        }

        public void setBankCardOppositePic(String bankCardOppositePic) {
            this.bankCardOppositePic = bankCardOppositePic;
        }

        public String getStorePic() {
            return storePic;
        }

        public void setStorePic(String storePic) {
            this.storePic = storePic;
        }

        public String getBusinessPlacePic() {
            return businessPlacePic;
        }

        public void setBusinessPlacePic(String businessPlacePic) {
            this.businessPlacePic = businessPlacePic;
        }

        public String getInsideScenePic() {
            return insideScenePic;
        }

        public void setInsideScenePic(String insideScenePic) {
            this.insideScenePic = insideScenePic;
        }

        public static class ContentBean {
            /**
             * cate_name : 美食,小吃快餐
             * mecDisNm : 绝味鸭脖
             * mblNo : 18779010813
             * operationalType : 01
             * haveLicenseNo : 01
             * mecTypeFlag : 00
             * wxQrcodeList : 0.38
             * aliQrcodeList : 0.38
             * thousandQrcodeList : 0.38
             * highestQrcodeList : 0.60
             * settleType : 04
             * cprRegAddr : xxxxxxx
             * regProvCd : 510000000000
             * regCityCd : 511900000000
             * regDistCd : 511922000000
             * mccCd : 5812
             * identityTyp : 00
             * legalPersonLicStt :
             * legalPersonLicEnt :
             * actNm : 老刘
             * actTyp : 01
             * stmManIdNo : 360425199402223421
             * accountLicStt :
             * accountLicEnt :
             * actNo : 6527 1120 3416 5569 212
             * lbnkNo : 102290000277
             * lbnkNm : 中国工商银行上海市万象城支行
             * legalPersonidPositivePic : /uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
             * legalPersonidOppositePic : /uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
             * bankCardPositivePic : /uploads/incomong_parts/1b/b6bac3701d13033d462063726a7566de4e769d.png
             * bankCardOppositePic :
             * merchantAgreementPic :
             * storePic : /uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg
             * businessPlacePic : /uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg
             * insideScenePic : /uploads/incomong_parts/4a/8185546ba1eb6d4809010dd10be67a80459f27.jpg
             * csTelNo : 18779010813
             * identityName : 老刘
             * identityNo : 360425199402223421
             * qrcodeList : [{"rate":"0.38","rateType":"01"},{"rate":"0.38","rateType":"02"},{"rate":"0.38","rateType":"06"},{"rate":"0.60","rateType":"07"}]
             */

            private String cate_name;
            private String mecDisNm;
            private String mblNo;
            private String operationalType;
            private String haveLicenseNo;
            private String mecTypeFlag;
            private String wxQrcodeList;
            private String aliQrcodeList;
            private String thousandQrcodeList;
            private String highestQrcodeList;
            private String settleType;
            private String cprRegAddr;
            private String regProvCd;
            private String regCityCd;
            private String regDistCd;
            private String mccCd;
            private String identityTyp;
            private String legalPersonLicStt;
            private String legalPersonLicEnt;
            private String actNm;
            private String actTyp;
            private String stmManIdNo;
            private String accountLicStt;
            private String accountLicEnt;
            private String actNo;
            private String lbnkNo;
            private String lbnkNm;
            private String legalPersonidPositivePic;
            private String legalPersonidOppositePic;
            private String bankCardPositivePic;
            private String bankCardOppositePic;
            private String merchantAgreementPic;
            private String storePic;
            private String businessPlacePic;
            private String insideScenePic;
            private String csTelNo;
            private String identityName;
            private String identityNo;
            private List<QrcodeListBean> qrcodeList;

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getMecDisNm() {
                return mecDisNm;
            }

            public void setMecDisNm(String mecDisNm) {
                this.mecDisNm = mecDisNm;
            }

            public String getMblNo() {
                return mblNo;
            }

            public void setMblNo(String mblNo) {
                this.mblNo = mblNo;
            }

            public String getOperationalType() {
                return operationalType;
            }

            public void setOperationalType(String operationalType) {
                this.operationalType = operationalType;
            }

            public String getHaveLicenseNo() {
                return haveLicenseNo;
            }

            public void setHaveLicenseNo(String haveLicenseNo) {
                this.haveLicenseNo = haveLicenseNo;
            }

            public String getMecTypeFlag() {
                return mecTypeFlag;
            }

            public void setMecTypeFlag(String mecTypeFlag) {
                this.mecTypeFlag = mecTypeFlag;
            }

            public String getWxQrcodeList() {
                return wxQrcodeList;
            }

            public void setWxQrcodeList(String wxQrcodeList) {
                this.wxQrcodeList = wxQrcodeList;
            }

            public String getAliQrcodeList() {
                return aliQrcodeList;
            }

            public void setAliQrcodeList(String aliQrcodeList) {
                this.aliQrcodeList = aliQrcodeList;
            }

            public String getThousandQrcodeList() {
                return thousandQrcodeList;
            }

            public void setThousandQrcodeList(String thousandQrcodeList) {
                this.thousandQrcodeList = thousandQrcodeList;
            }

            public String getHighestQrcodeList() {
                return highestQrcodeList;
            }

            public void setHighestQrcodeList(String highestQrcodeList) {
                this.highestQrcodeList = highestQrcodeList;
            }

            public String getSettleType() {
                return settleType;
            }

            public void setSettleType(String settleType) {
                this.settleType = settleType;
            }

            public String getCprRegAddr() {
                return cprRegAddr;
            }

            public void setCprRegAddr(String cprRegAddr) {
                this.cprRegAddr = cprRegAddr;
            }

            public String getRegProvCd() {
                return regProvCd;
            }

            public void setRegProvCd(String regProvCd) {
                this.regProvCd = regProvCd;
            }

            public String getRegCityCd() {
                return regCityCd;
            }

            public void setRegCityCd(String regCityCd) {
                this.regCityCd = regCityCd;
            }

            public String getRegDistCd() {
                return regDistCd;
            }

            public void setRegDistCd(String regDistCd) {
                this.regDistCd = regDistCd;
            }

            public String getMccCd() {
                return mccCd;
            }

            public void setMccCd(String mccCd) {
                this.mccCd = mccCd;
            }

            public String getIdentityTyp() {
                return identityTyp;
            }

            public void setIdentityTyp(String identityTyp) {
                this.identityTyp = identityTyp;
            }

            public String getLegalPersonLicStt() {
                return legalPersonLicStt;
            }

            public void setLegalPersonLicStt(String legalPersonLicStt) {
                this.legalPersonLicStt = legalPersonLicStt;
            }

            public String getLegalPersonLicEnt() {
                return legalPersonLicEnt;
            }

            public void setLegalPersonLicEnt(String legalPersonLicEnt) {
                this.legalPersonLicEnt = legalPersonLicEnt;
            }

            public String getActNm() {
                return actNm;
            }

            public void setActNm(String actNm) {
                this.actNm = actNm;
            }

            public String getActTyp() {
                return actTyp;
            }

            public void setActTyp(String actTyp) {
                this.actTyp = actTyp;
            }

            public String getStmManIdNo() {
                return stmManIdNo;
            }

            public void setStmManIdNo(String stmManIdNo) {
                this.stmManIdNo = stmManIdNo;
            }

            public String getAccountLicStt() {
                return accountLicStt;
            }

            public void setAccountLicStt(String accountLicStt) {
                this.accountLicStt = accountLicStt;
            }

            public String getAccountLicEnt() {
                return accountLicEnt;
            }

            public void setAccountLicEnt(String accountLicEnt) {
                this.accountLicEnt = accountLicEnt;
            }

            public String getActNo() {
                return actNo;
            }

            public void setActNo(String actNo) {
                this.actNo = actNo;
            }

            public String getLbnkNo() {
                return lbnkNo;
            }

            public void setLbnkNo(String lbnkNo) {
                this.lbnkNo = lbnkNo;
            }

            public String getLbnkNm() {
                return lbnkNm;
            }

            public void setLbnkNm(String lbnkNm) {
                this.lbnkNm = lbnkNm;
            }

            public String getLegalPersonidPositivePic() {
                return legalPersonidPositivePic;
            }

            public void setLegalPersonidPositivePic(String legalPersonidPositivePic) {
                this.legalPersonidPositivePic = legalPersonidPositivePic;
            }

            public String getLegalPersonidOppositePic() {
                return legalPersonidOppositePic;
            }

            public void setLegalPersonidOppositePic(String legalPersonidOppositePic) {
                this.legalPersonidOppositePic = legalPersonidOppositePic;
            }

            public String getBankCardPositivePic() {
                return bankCardPositivePic;
            }

            public void setBankCardPositivePic(String bankCardPositivePic) {
                this.bankCardPositivePic = bankCardPositivePic;
            }

            public String getBankCardOppositePic() {
                return bankCardOppositePic;
            }

            public void setBankCardOppositePic(String bankCardOppositePic) {
                this.bankCardOppositePic = bankCardOppositePic;
            }

            public String getMerchantAgreementPic() {
                return merchantAgreementPic;
            }

            public void setMerchantAgreementPic(String merchantAgreementPic) {
                this.merchantAgreementPic = merchantAgreementPic;
            }

            public String getStorePic() {
                return storePic;
            }

            public void setStorePic(String storePic) {
                this.storePic = storePic;
            }

            public String getBusinessPlacePic() {
                return businessPlacePic;
            }

            public void setBusinessPlacePic(String businessPlacePic) {
                this.businessPlacePic = businessPlacePic;
            }

            public String getInsideScenePic() {
                return insideScenePic;
            }

            public void setInsideScenePic(String insideScenePic) {
                this.insideScenePic = insideScenePic;
            }

            public String getCsTelNo() {
                return csTelNo;
            }

            public void setCsTelNo(String csTelNo) {
                this.csTelNo = csTelNo;
            }

            public String getIdentityName() {
                return identityName;
            }

            public void setIdentityName(String identityName) {
                this.identityName = identityName;
            }

            public String getIdentityNo() {
                return identityNo;
            }

            public void setIdentityNo(String identityNo) {
                this.identityNo = identityNo;
            }

            public List<QrcodeListBean> getQrcodeList() {
                return qrcodeList;
            }

            public void setQrcodeList(List<QrcodeListBean> qrcodeList) {
                this.qrcodeList = qrcodeList;
            }

            public static class QrcodeListBean {
                /**
                 * rate : 0.38
                 * rateType : 01
                 */

                private String rate;
                private String rateType;

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getRateType() {
                    return rateType;
                }

                public void setRateType(String rateType) {
                    this.rateType = rateType;
                }
            }
    }
}
