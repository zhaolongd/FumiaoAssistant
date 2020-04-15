package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class PartsBean extends CoreBean{
    private String id;
    private String merchant_id;
    private int status;  //（0：待进件 1：审核中，2：审核通过，3：审核驳回，4：图片驳回）
    private String update_time;
    private String create_time;
    private ContentBean content;
    private String store_id;
    private String failure_msg; //驳回信息
    private  BankInfoBean bank_info;
    /**
     * 以下图片服务器url
     */
    private String licensePic;
    private String openingAccountLicensePic;
    private String settlePersonIdcardPositive;
    private String settlePersonIdcardOpposite;
    private String letterOfAuthPic;
    private String legalPersonidPositivePic;
    private String legalPersonidOppositePic;
    private String bankCardPositivePic;
    private String bankCardOppositePic;
    private String storePic;
    private String businessPlacePic;
    private String insideScenePic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getFailure_msg() {
        return failure_msg;
    }

    public void setFailure_msg(String failure_msg) {
        this.failure_msg = failure_msg;
    }

    public BankInfoBean getBank_info() {
        return bank_info;
    }

    public void setBank_info(BankInfoBean bank_info) {
        this.bank_info = bank_info;
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

    public String getLicensePic() {
        return licensePic;
    }

    public void setLicensePic(String licensePic) {
        this.licensePic = licensePic;
    }

    public String getOpeningAccountLicensePic() {
        return openingAccountLicensePic;
    }

    public void setOpeningAccountLicensePic(String openingAccountLicensePic) {
        this.openingAccountLicensePic = openingAccountLicensePic;
    }

    public String getSettlePersonIdcardPositive() {
        return settlePersonIdcardPositive;
    }

    public void setSettlePersonIdcardPositive(String settlePersonIdcardPositive) {
        this.settlePersonIdcardPositive = settlePersonIdcardPositive;
    }

    public String getSettlePersonIdcardOpposite() {
        return settlePersonIdcardOpposite;
    }

    public void setSettlePersonIdcardOpposite(String settlePersonIdcardOpposite) {
        this.settlePersonIdcardOpposite = settlePersonIdcardOpposite;
    }

    public String getLetterOfAuthPic() {
        return letterOfAuthPic;
    }

    public void setLetterOfAuthPic(String letterOfAuthPic) {
        this.letterOfAuthPic = letterOfAuthPic;
    }

    public static class ContentBean extends CoreBean {
       private String mecDisNm;
       private String mblNo; //"13755651611",
        private String operationalType; //"01",
        private String haveLicenseNo; //"01",
        private String mecTypeFlag; //"00",
        private String wxQrcodeList; //"0.38",
        private String aliQrcodeList; //"0.38",
        private String thousandQrcodeList; //"0.38",
        private String highestQrcodeList; //"0.38",
        private String settleType; //"04",
        private String cprRegNmCn;
        private String registCode;
        private String licenseMatch;
        private String cprRegAddr;//"哈哈哈哈就",
        private String regProvCd; //"110000000000",
        private String regCityCd; //"110100000000",
        private String  regDistCd; //"110118000000",
        private String mccCd; //"5812",
        private String mcc_name;
        private String identityTyp; //"00",
        private String  actNm; //"22555",
        private String actTyp; //"01",
        private String stmManIdNo; //"360103198910233819",
        private int people_type;
        private String accountLicStt;
        private String accountLicEnt;
        private String actNo; //"2555566",
        private String lbnkNo; //"301100000058",
        private String lbnkNm; //"交通银行北京亚运村支行",
        private String licensePic;
        private String taxRegistLicensePic;
        private String orgCodePic;
        private String legalPersonidPositivePic; //"\/uploads\/4e\/81b2f3fc4bb3080c7c2402f305643cece30594.jpeg",
        private String legalPersonidOppositePic; //"\/uploads\/12\/b377812bc57189c0aeccd0c80057fb39572f83.jpeg",
        private String openingAccountLicensePic;
        private String bankCardPositivePic;//"\/uploads\/0d\/e44f524029aa5451f82ef95b56ebdcfdc857a5.jpeg",
        private String bankCardOppositePic; //"\/uploads\/e6\/d03b751d21384e704dde1e7f6e333cc0a29d74.jpeg",
        private String settlePersonIdcardOpposite;
        private String settlePersonIdcardPositive;
        private String storePic; //"\/uploads\/26\/ba9713f6c149fa459700c1c995e121876b1aee.jpeg",
        private String businessPlacePic; //"\/uploads\/26\/ba9713f6c149fa459700c1c995e121876b1aee.jpeg",
        private String insideScenePic; //"\/uploads\/73\/5006dced320b6b75842f75e0ab1d727bce8921.jpeg",
        private String letterOfAuthPic;
        private String csTelNo; //"13755651611",
        private String identityName; //"22555",
        private String identityNo; //"360103198910233819"

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

        public String getMcc_name() {
            return mcc_name;
        }

        public void setMcc_name(String mcc_name) {
            this.mcc_name = mcc_name;
        }

        public int getPeople_type() {
            return people_type;
        }

        public void setPeople_type(int people_type) {
            this.people_type = people_type;
        }

        public String getLicensePic() {
            return licensePic;
        }

        public void setLicensePic(String licensePic) {
            this.licensePic = licensePic;
        }

        public String getTaxRegistLicensePic() {
            return taxRegistLicensePic;
        }

        public void setTaxRegistLicensePic(String taxRegistLicensePic) {
            this.taxRegistLicensePic = taxRegistLicensePic;
        }

        public String getOrgCodePic() {
            return orgCodePic;
        }

        public void setOrgCodePic(String orgCodePic) {
            this.orgCodePic = orgCodePic;
        }

        public String getOpeningAccountLicensePic() {
            return openingAccountLicensePic;
        }

        public void setOpeningAccountLicensePic(String openingAccountLicensePic) {
            this.openingAccountLicensePic = openingAccountLicensePic;
        }

        public String getSettlePersonIdcardOpposite() {
            return settlePersonIdcardOpposite;
        }

        public void setSettlePersonIdcardOpposite(String settlePersonIdcardOpposite) {
            this.settlePersonIdcardOpposite = settlePersonIdcardOpposite;
        }

        public String getSettlePersonIdcardPositive() {
            return settlePersonIdcardPositive;
        }

        public void setSettlePersonIdcardPositive(String settlePersonIdcardPositive) {
            this.settlePersonIdcardPositive = settlePersonIdcardPositive;
        }

        public String getLetterOfAuthPic() {
            return letterOfAuthPic;
        }

        public void setLetterOfAuthPic(String letterOfAuthPic) {
            this.letterOfAuthPic = letterOfAuthPic;
        }

        public String getCprRegNmCn() {
            return cprRegNmCn;
        }

        public void setCprRegNmCn(String cprRegNmCn) {
            this.cprRegNmCn = cprRegNmCn;
        }

        public String getRegistCode() {
            return registCode;
        }

        public void setRegistCode(String registCode) {
            this.registCode = registCode;
        }

        public String getLicenseMatch() {
            return licenseMatch;
        }

        public void setLicenseMatch(String licenseMatch) {
            this.licenseMatch = licenseMatch;
        }
    }

    public static class BankInfoBean extends CoreBean {
        /**
         * "id": 14338,
         * "interbank_num": "103126080113",
         * "interbank_name": "中国农业银行秦皇岛玉龙湾分理处",
         * "interbank_code": "103",
         * "bank_name": "中国农业银行",
         * "network_link_number": "C1010311000014",
         * "province_num": "13",
         * "province_name": "河北省",
         * "city_num": "1303",
         * "city_name": "秦皇岛市"
         */
        private String id;
        private String interbank_num;
        private String interbank_name;
        private String interbank_code;
        private String bank_name;
        private String province_num;
        private String province_name;
        private String city_num;
        private String city_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getProvince_num() {
            return province_num;
        }

        public void setProvince_num(String province_num) {
            this.province_num = province_num;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_num() {
            return city_num;
        }

        public void setCity_num(String city_num) {
            this.city_num = city_num;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }
    }

}
