package com.fumiao.assistant.bean.home;
import com.fumiao.core.adapter.CoreBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhaolong on 2019/9/19.
 * 小微进件需要参数
 */
@Entity
public class InComing extends CoreBean {
    static final long serialVersionUID = -15515456L;
    @Id(autoincrement = true)
    private Long id;
    /**
     * 以下是进件提交的数据
     */
    private String cate_id;
    private String cate_name;
    private String mecDisNm;  //商户名称
    private String mblNo; //手机号
    private String wxQrcodeList; //微信费率
    private String aliQrcodeList; //支付宝费率
    private String thousandQrcodeList; //0-1000费率
    private String highestQrcodeList; //大于1000费率
    private String cprRegAddr; //详细地址
    private String regProvCd; //省code
    private String regCityCd; //市code
    private String regDistCd; //区code
    private String actNm; //账户名
    private String stmManIdNo; //身份证号
    private String actNo; //银行卡号;
    private String lbnkNo; //开户支行号;
    private String lbnkNm; //开户行 中国工商银行北京通州支行新华分理处
    private String legalPersonidPositivePic; //结算人身份证正面
    private String legalPersonidOppositePic; //结算人身份证反面
    private String bankCardPositivePic; //银行卡正面
    private String bankCardOppositePic; //银行卡反面
    private String storePic; //门头照
    private String businessPlacePic; //收银台照
    private String insideScenePic; //门店内景
    /**
     * 以下是界面显示的数据
     */
    private String regProv; //省
    private String regCity; //市
    private String regDist; //区
    private String legalPersonidPositiveLoadPic; //结算人身份证正面 服务器图片url
    private String legalPersonidOppositeLoadPic; //结算人身份证反面 服务器图片url
    private String bankCardPositiveLoadPic; //银行卡正面 服务器图片url
    private String bankCardOppositeLoadPic; //银行卡反面 服务器图片url
    private String storeLoadPic; //门头照 服务器图片url
    private String businessPlaceLoadPic; //收银台照 服务器图片url
    private String insideSceneLoadPic; //门店内景 服务器图片url
    private String settlePersonIdcardLoadPositive; //对私结算 身份证正面
    private String settlePersonIdcardLoadOpposite; //对私结算 身份证反面
    private String bankCode; //开户行code
    private String bankName; //开户行
    private String bankCityCode; //开户城市code
    private String bankCityName; //开户城市
    private String createTime;
    private int status; // 进件状态
    private String failure_msg; //驳回原因

    private String licenseLoadPic; //营业执照照片 服务器图片url
    private String openingAccountLicenseLoadPic; //开户许可证 服务器图片url
    private String letterOfAuthPicLoadPic; //非法人授权函 服务器图片url

    /**
     * 驳回需要的数据
     */
    private String merchant_id;
    private String member_id; //原有的店长id
    private String merchant_name;
    private String store_id;
    private String incoming_parts_id;
    private String merchant_type;

    /**
     * 普通商户进件需要的数据
     */
    //商户信息
    private String licensePic; //营业执照照片
    private String cprRegNmCn; //营业执照注册名称
    private String registCode; //营业执照注册号
    private String mccCd; //商户大类 工商类别
    private String mcc_name; //商户大类 名称
    private String identityName; //法人姓名
    private String identityTyp; //证件类型
    private String identityNo; //法人证件号
    //商户信息
    private String haveLicenceNo; //02 个体 03企业
    private boolean isHaveLicence; // true ：企业  false：个体
    private String actTyp; //结算账户类型 00对公 01对私
    //结算
    private String settleType; //结算方式 03 T1 04 D1
    private int people_type; //法人或者非法人 1法人，2非法人
    private String openingAccountLicensePic; //开户许可证 对私非必填
    private String letterOfAuthPic; //非法人授权函
    private String settlePersonIdcardPositive; //对私结算 身份证正面
    private String settlePersonIdcardOpposite; //对私结算 身份证反面
    private boolean isBusinessComplete = false;
    private boolean isMerchantComplete = false;
    private boolean isSettlementComplete = false;
    private boolean isRateComplete = false;
    @Generated(hash = 1630623672)
    public InComing(Long id, String cate_id, String cate_name, String mecDisNm,
            String mblNo, String wxQrcodeList, String aliQrcodeList,
            String thousandQrcodeList, String highestQrcodeList, String cprRegAddr,
            String regProvCd, String regCityCd, String regDistCd, String actNm,
            String stmManIdNo, String actNo, String lbnkNo, String lbnkNm,
            String legalPersonidPositivePic, String legalPersonidOppositePic,
            String bankCardPositivePic, String bankCardOppositePic, String storePic,
            String businessPlacePic, String insideScenePic, String regProv,
            String regCity, String regDist, String legalPersonidPositiveLoadPic,
            String legalPersonidOppositeLoadPic, String bankCardPositiveLoadPic,
            String bankCardOppositeLoadPic, String storeLoadPic,
            String businessPlaceLoadPic, String insideSceneLoadPic,
            String settlePersonIdcardLoadPositive,
            String settlePersonIdcardLoadOpposite, String bankCode, String bankName,
            String bankCityCode, String bankCityName, String createTime, int status,
            String failure_msg, String licenseLoadPic,
            String openingAccountLicenseLoadPic, String letterOfAuthPicLoadPic,
            String merchant_id, String member_id, String merchant_name,
            String store_id, String incoming_parts_id, String merchant_type,
            String licensePic, String cprRegNmCn, String registCode, String mccCd,
            String mcc_name, String identityName, String identityTyp,
            String identityNo, String haveLicenceNo, boolean isHaveLicence,
            String actTyp, String settleType, int people_type,
            String openingAccountLicensePic, String letterOfAuthPic,
            String settlePersonIdcardPositive, String settlePersonIdcardOpposite,
            boolean isBusinessComplete, boolean isMerchantComplete,
            boolean isSettlementComplete, boolean isRateComplete) {
        this.id = id;
        this.cate_id = cate_id;
        this.cate_name = cate_name;
        this.mecDisNm = mecDisNm;
        this.mblNo = mblNo;
        this.wxQrcodeList = wxQrcodeList;
        this.aliQrcodeList = aliQrcodeList;
        this.thousandQrcodeList = thousandQrcodeList;
        this.highestQrcodeList = highestQrcodeList;
        this.cprRegAddr = cprRegAddr;
        this.regProvCd = regProvCd;
        this.regCityCd = regCityCd;
        this.regDistCd = regDistCd;
        this.actNm = actNm;
        this.stmManIdNo = stmManIdNo;
        this.actNo = actNo;
        this.lbnkNo = lbnkNo;
        this.lbnkNm = lbnkNm;
        this.legalPersonidPositivePic = legalPersonidPositivePic;
        this.legalPersonidOppositePic = legalPersonidOppositePic;
        this.bankCardPositivePic = bankCardPositivePic;
        this.bankCardOppositePic = bankCardOppositePic;
        this.storePic = storePic;
        this.businessPlacePic = businessPlacePic;
        this.insideScenePic = insideScenePic;
        this.regProv = regProv;
        this.regCity = regCity;
        this.regDist = regDist;
        this.legalPersonidPositiveLoadPic = legalPersonidPositiveLoadPic;
        this.legalPersonidOppositeLoadPic = legalPersonidOppositeLoadPic;
        this.bankCardPositiveLoadPic = bankCardPositiveLoadPic;
        this.bankCardOppositeLoadPic = bankCardOppositeLoadPic;
        this.storeLoadPic = storeLoadPic;
        this.businessPlaceLoadPic = businessPlaceLoadPic;
        this.insideSceneLoadPic = insideSceneLoadPic;
        this.settlePersonIdcardLoadPositive = settlePersonIdcardLoadPositive;
        this.settlePersonIdcardLoadOpposite = settlePersonIdcardLoadOpposite;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.bankCityCode = bankCityCode;
        this.bankCityName = bankCityName;
        this.createTime = createTime;
        this.status = status;
        this.failure_msg = failure_msg;
        this.licenseLoadPic = licenseLoadPic;
        this.openingAccountLicenseLoadPic = openingAccountLicenseLoadPic;
        this.letterOfAuthPicLoadPic = letterOfAuthPicLoadPic;
        this.merchant_id = merchant_id;
        this.member_id = member_id;
        this.merchant_name = merchant_name;
        this.store_id = store_id;
        this.incoming_parts_id = incoming_parts_id;
        this.merchant_type = merchant_type;
        this.licensePic = licensePic;
        this.cprRegNmCn = cprRegNmCn;
        this.registCode = registCode;
        this.mccCd = mccCd;
        this.mcc_name = mcc_name;
        this.identityName = identityName;
        this.identityTyp = identityTyp;
        this.identityNo = identityNo;
        this.haveLicenceNo = haveLicenceNo;
        this.isHaveLicence = isHaveLicence;
        this.actTyp = actTyp;
        this.settleType = settleType;
        this.people_type = people_type;
        this.openingAccountLicensePic = openingAccountLicensePic;
        this.letterOfAuthPic = letterOfAuthPic;
        this.settlePersonIdcardPositive = settlePersonIdcardPositive;
        this.settlePersonIdcardOpposite = settlePersonIdcardOpposite;
        this.isBusinessComplete = isBusinessComplete;
        this.isMerchantComplete = isMerchantComplete;
        this.isSettlementComplete = isSettlementComplete;
        this.isRateComplete = isRateComplete;
    }
    @Generated(hash = 1681691242)
    public InComing() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCate_id() {
        return this.cate_id;
    }
    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }
    public String getCate_name() {
        return this.cate_name;
    }
    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
    public String getMecDisNm() {
        return this.mecDisNm;
    }
    public void setMecDisNm(String mecDisNm) {
        this.mecDisNm = mecDisNm;
    }
    public String getMblNo() {
        return this.mblNo;
    }
    public void setMblNo(String mblNo) {
        this.mblNo = mblNo;
    }
    public String getWxQrcodeList() {
        return this.wxQrcodeList;
    }
    public void setWxQrcodeList(String wxQrcodeList) {
        this.wxQrcodeList = wxQrcodeList;
    }
    public String getAliQrcodeList() {
        return this.aliQrcodeList;
    }
    public void setAliQrcodeList(String aliQrcodeList) {
        this.aliQrcodeList = aliQrcodeList;
    }
    public String getThousandQrcodeList() {
        return this.thousandQrcodeList;
    }
    public void setThousandQrcodeList(String thousandQrcodeList) {
        this.thousandQrcodeList = thousandQrcodeList;
    }
    public String getHighestQrcodeList() {
        return this.highestQrcodeList;
    }
    public void setHighestQrcodeList(String highestQrcodeList) {
        this.highestQrcodeList = highestQrcodeList;
    }
    public String getCprRegAddr() {
        return this.cprRegAddr;
    }
    public void setCprRegAddr(String cprRegAddr) {
        this.cprRegAddr = cprRegAddr;
    }
    public String getRegProvCd() {
        return this.regProvCd;
    }
    public void setRegProvCd(String regProvCd) {
        this.regProvCd = regProvCd;
    }
    public String getRegCityCd() {
        return this.regCityCd;
    }
    public void setRegCityCd(String regCityCd) {
        this.regCityCd = regCityCd;
    }
    public String getRegDistCd() {
        return this.regDistCd;
    }
    public void setRegDistCd(String regDistCd) {
        this.regDistCd = regDistCd;
    }
    public String getActNm() {
        return this.actNm;
    }
    public void setActNm(String actNm) {
        this.actNm = actNm;
    }
    public String getStmManIdNo() {
        return this.stmManIdNo;
    }
    public void setStmManIdNo(String stmManIdNo) {
        this.stmManIdNo = stmManIdNo;
    }
    public String getActNo() {
        return this.actNo;
    }
    public void setActNo(String actNo) {
        this.actNo = actNo;
    }
    public String getLbnkNo() {
        return this.lbnkNo;
    }
    public void setLbnkNo(String lbnkNo) {
        this.lbnkNo = lbnkNo;
    }
    public String getLbnkNm() {
        return this.lbnkNm;
    }
    public void setLbnkNm(String lbnkNm) {
        this.lbnkNm = lbnkNm;
    }
    public String getLegalPersonidPositivePic() {
        return this.legalPersonidPositivePic;
    }
    public void setLegalPersonidPositivePic(String legalPersonidPositivePic) {
        this.legalPersonidPositivePic = legalPersonidPositivePic;
    }
    public String getLegalPersonidOppositePic() {
        return this.legalPersonidOppositePic;
    }
    public void setLegalPersonidOppositePic(String legalPersonidOppositePic) {
        this.legalPersonidOppositePic = legalPersonidOppositePic;
    }
    public String getBankCardPositivePic() {
        return this.bankCardPositivePic;
    }
    public void setBankCardPositivePic(String bankCardPositivePic) {
        this.bankCardPositivePic = bankCardPositivePic;
    }
    public String getBankCardOppositePic() {
        return this.bankCardOppositePic;
    }
    public void setBankCardOppositePic(String bankCardOppositePic) {
        this.bankCardOppositePic = bankCardOppositePic;
    }
    public String getStorePic() {
        return this.storePic;
    }
    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }
    public String getBusinessPlacePic() {
        return this.businessPlacePic;
    }
    public void setBusinessPlacePic(String businessPlacePic) {
        this.businessPlacePic = businessPlacePic;
    }
    public String getInsideScenePic() {
        return this.insideScenePic;
    }
    public void setInsideScenePic(String insideScenePic) {
        this.insideScenePic = insideScenePic;
    }
    public String getRegProv() {
        return this.regProv;
    }
    public void setRegProv(String regProv) {
        this.regProv = regProv;
    }
    public String getRegCity() {
        return this.regCity;
    }
    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }
    public String getRegDist() {
        return this.regDist;
    }
    public void setRegDist(String regDist) {
        this.regDist = regDist;
    }
    public String getLegalPersonidPositiveLoadPic() {
        return this.legalPersonidPositiveLoadPic;
    }
    public void setLegalPersonidPositiveLoadPic(
            String legalPersonidPositiveLoadPic) {
        this.legalPersonidPositiveLoadPic = legalPersonidPositiveLoadPic;
    }
    public String getLegalPersonidOppositeLoadPic() {
        return this.legalPersonidOppositeLoadPic;
    }
    public void setLegalPersonidOppositeLoadPic(
            String legalPersonidOppositeLoadPic) {
        this.legalPersonidOppositeLoadPic = legalPersonidOppositeLoadPic;
    }
    public String getBankCardPositiveLoadPic() {
        return this.bankCardPositiveLoadPic;
    }
    public void setBankCardPositiveLoadPic(String bankCardPositiveLoadPic) {
        this.bankCardPositiveLoadPic = bankCardPositiveLoadPic;
    }
    public String getBankCardOppositeLoadPic() {
        return this.bankCardOppositeLoadPic;
    }
    public void setBankCardOppositeLoadPic(String bankCardOppositeLoadPic) {
        this.bankCardOppositeLoadPic = bankCardOppositeLoadPic;
    }
    public String getStoreLoadPic() {
        return this.storeLoadPic;
    }
    public void setStoreLoadPic(String storeLoadPic) {
        this.storeLoadPic = storeLoadPic;
    }
    public String getBusinessPlaceLoadPic() {
        return this.businessPlaceLoadPic;
    }
    public void setBusinessPlaceLoadPic(String businessPlaceLoadPic) {
        this.businessPlaceLoadPic = businessPlaceLoadPic;
    }
    public String getInsideSceneLoadPic() {
        return this.insideSceneLoadPic;
    }
    public void setInsideSceneLoadPic(String insideSceneLoadPic) {
        this.insideSceneLoadPic = insideSceneLoadPic;
    }
    public String getSettlePersonIdcardLoadPositive() {
        return this.settlePersonIdcardLoadPositive;
    }
    public void setSettlePersonIdcardLoadPositive(
            String settlePersonIdcardLoadPositive) {
        this.settlePersonIdcardLoadPositive = settlePersonIdcardLoadPositive;
    }
    public String getSettlePersonIdcardLoadOpposite() {
        return this.settlePersonIdcardLoadOpposite;
    }
    public void setSettlePersonIdcardLoadOpposite(
            String settlePersonIdcardLoadOpposite) {
        this.settlePersonIdcardLoadOpposite = settlePersonIdcardLoadOpposite;
    }
    public String getBankCode() {
        return this.bankCode;
    }
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    public String getBankName() {
        return this.bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankCityCode() {
        return this.bankCityCode;
    }
    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode;
    }
    public String getBankCityName() {
        return this.bankCityName;
    }
    public void setBankCityName(String bankCityName) {
        this.bankCityName = bankCityName;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getFailure_msg() {
        return this.failure_msg;
    }
    public void setFailure_msg(String failure_msg) {
        this.failure_msg = failure_msg;
    }
    public String getLicenseLoadPic() {
        return this.licenseLoadPic;
    }
    public void setLicenseLoadPic(String licenseLoadPic) {
        this.licenseLoadPic = licenseLoadPic;
    }
    public String getOpeningAccountLicenseLoadPic() {
        return this.openingAccountLicenseLoadPic;
    }
    public void setOpeningAccountLicenseLoadPic(
            String openingAccountLicenseLoadPic) {
        this.openingAccountLicenseLoadPic = openingAccountLicenseLoadPic;
    }
    public String getLetterOfAuthPicLoadPic() {
        return this.letterOfAuthPicLoadPic;
    }
    public void setLetterOfAuthPicLoadPic(String letterOfAuthPicLoadPic) {
        this.letterOfAuthPicLoadPic = letterOfAuthPicLoadPic;
    }
    public String getMerchant_id() {
        return this.merchant_id;
    }
    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }
    public String getMember_id() {
        return this.member_id;
    }
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public String getMerchant_name() {
        return this.merchant_name;
    }
    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }
    public String getStore_id() {
        return this.store_id;
    }
    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
    public String getIncoming_parts_id() {
        return this.incoming_parts_id;
    }
    public void setIncoming_parts_id(String incoming_parts_id) {
        this.incoming_parts_id = incoming_parts_id;
    }
    public String getMerchant_type() {
        return this.merchant_type;
    }
    public void setMerchant_type(String merchant_type) {
        this.merchant_type = merchant_type;
    }
    public String getLicensePic() {
        return this.licensePic;
    }
    public void setLicensePic(String licensePic) {
        this.licensePic = licensePic;
    }
    public String getCprRegNmCn() {
        return this.cprRegNmCn;
    }
    public void setCprRegNmCn(String cprRegNmCn) {
        this.cprRegNmCn = cprRegNmCn;
    }
    public String getRegistCode() {
        return this.registCode;
    }
    public void setRegistCode(String registCode) {
        this.registCode = registCode;
    }
    public String getMccCd() {
        return this.mccCd;
    }
    public void setMccCd(String mccCd) {
        this.mccCd = mccCd;
    }
    public String getMcc_name() {
        return this.mcc_name;
    }
    public void setMcc_name(String mcc_name) {
        this.mcc_name = mcc_name;
    }
    public String getIdentityName() {
        return this.identityName;
    }
    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }
    public String getIdentityTyp() {
        return this.identityTyp;
    }
    public void setIdentityTyp(String identityTyp) {
        this.identityTyp = identityTyp;
    }
    public String getIdentityNo() {
        return this.identityNo;
    }
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
    public String getHaveLicenceNo() {
        return this.haveLicenceNo;
    }
    public void setHaveLicenceNo(String haveLicenceNo) {
        this.haveLicenceNo = haveLicenceNo;
    }
    public boolean getIsHaveLicence() {
        return this.isHaveLicence;
    }
    public void setIsHaveLicence(boolean isHaveLicence) {
        this.isHaveLicence = isHaveLicence;
    }
    public String getActTyp() {
        return this.actTyp;
    }
    public void setActTyp(String actTyp) {
        this.actTyp = actTyp;
    }
    public String getSettleType() {
        return this.settleType;
    }
    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }
    public int getPeople_type() {
        return this.people_type;
    }
    public void setPeople_type(int people_type) {
        this.people_type = people_type;
    }
    public String getOpeningAccountLicensePic() {
        return this.openingAccountLicensePic;
    }
    public void setOpeningAccountLicensePic(String openingAccountLicensePic) {
        this.openingAccountLicensePic = openingAccountLicensePic;
    }
    public String getLetterOfAuthPic() {
        return this.letterOfAuthPic;
    }
    public void setLetterOfAuthPic(String letterOfAuthPic) {
        this.letterOfAuthPic = letterOfAuthPic;
    }
    public String getSettlePersonIdcardPositive() {
        return this.settlePersonIdcardPositive;
    }
    public void setSettlePersonIdcardPositive(String settlePersonIdcardPositive) {
        this.settlePersonIdcardPositive = settlePersonIdcardPositive;
    }
    public String getSettlePersonIdcardOpposite() {
        return this.settlePersonIdcardOpposite;
    }
    public void setSettlePersonIdcardOpposite(String settlePersonIdcardOpposite) {
        this.settlePersonIdcardOpposite = settlePersonIdcardOpposite;
    }
    public boolean getIsBusinessComplete() {
        return this.isBusinessComplete;
    }
    public void setIsBusinessComplete(boolean isBusinessComplete) {
        this.isBusinessComplete = isBusinessComplete;
    }
    public boolean getIsMerchantComplete() {
        return this.isMerchantComplete;
    }
    public void setIsMerchantComplete(boolean isMerchantComplete) {
        this.isMerchantComplete = isMerchantComplete;
    }
    public boolean getIsSettlementComplete() {
        return this.isSettlementComplete;
    }
    public void setIsSettlementComplete(boolean isSettlementComplete) {
        this.isSettlementComplete = isSettlementComplete;
    }
    public boolean getIsRateComplete() {
        return this.isRateComplete;
    }
    public void setIsRateComplete(boolean isRateComplete) {
        this.isRateComplete = isRateComplete;
    }

}
