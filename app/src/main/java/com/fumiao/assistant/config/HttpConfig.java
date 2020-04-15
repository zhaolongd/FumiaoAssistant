package com.fumiao.assistant.config;

import com.fumiao.assistant.BuildConfig;

public interface HttpConfig {
    String BASE_URL = BuildConfig.URL;
    String BASE_ACTIVITY_URL = BuildConfig.ACTIVITY_URL;
    String LOGIN = BASE_URL + "/login/login";//初始化
    String GET_PHONE_CODE = BASE_URL + "/login/send_code";//发送短信验证码
    String CHECK_PHONE_CODE = BASE_URL + "/login/checkPhoneCode";
    String OPENING_BANK = BASE_URL + "/merchant/opening_bank";//开户银行
    String BANK_PROVINCE = BASE_URL + "/merchant/province_branch";//开户银行省
    String BANK_CITY = BASE_URL + "/merchant/bank_branch";//开户银行城市
    String BANK_BRANCH = BASE_URL + "/merchant/opening_address";//开户银行支行
    String CATE_LIST = BASE_URL + "/store/cateList";//门店分类列表
    String AREA = BASE_URL + "/store/region";//地址
    String UPLOAD_IMAGE = BASE_URL + "/merchant/upimg";//上传图片
    String INCOMING_LIST = BASE_URL + "/incoming_parts/incoming_parts_list"; //进件列表
    String INCOMING_PARTS = BASE_URL + "/IncomingParts/incoming_parts"; //小微进件
    String NORMAL_MERCHANT_INCOMING = BASE_URL + "/IncomingParts/ordinary_incoming"; //普通商户进件
    String REJECT_NORMAL_MERCHANT_INCOMING = BASE_URL+ "/IncomingParts/reject_ordinary_incoming"; //普通商户进件驳回重新提交
    String REJECT_INCOMING_PARTS = BASE_URL + "/IncomingParts/reject_incoming_parts"; //进件驳回重新提交
    String MERCHANT_LIST = BASE_URL + "/merchant/get_merchant_list"; //商户列表
    String MERCHANT_DETAIL_INFO = BASE_URL + "/merchant/app_merchant_info"; //商户详情
    String MERCHANT_DETAIL = BASE_URL + "/IncomingParts/incoming_parts_info"; //商户详情 进件详情
    String CHANGE_PASSWORD = BASE_URL + "/Admin/save_pwd"; //修改密码
    String LOGIN_OUT = BASE_URL + "/login/logout"; //退出登录
    String SALESMAN_INFO = BASE_URL + "/admin/salesman_info"; //业务员统计交易金额，笔数
    String OCR_ID_CARD = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard"; //OCR 身份证识别信息
    String OCR_BANK_CARD = "https://api-cn.faceplusplus.com/cardpp/v1/ocrbankcard"; //OCR 银行卡识别信息
    String NORMAL_MERCHANT_LIST = BASE_URL + "/IncomingParts/merchant_type";//商户类别列表
    String LOWE_RATE_ACTIVITY = BASE_ACTIVITY_URL +"/activity/?id=";
    String DEVICE_LIST = BASE_URL + "/merchant/merchant_device"; // 终端列表   参数merchant_id
    String DEVICE_DETAIL = BASE_URL + "/device/device_info"; //  设备详情，参数设备sn号，参数名sn
    String RELIEVE_CODE = BASE_URL + "/device/send_relieve_code"; //解绑发送短信接口
    String UNBIND_DEVICE = BASE_URL + "/device/checkPhoneCode"; //解绑验证短信接口，参数：门店store_id，序列号serial_number，验证码code
    String STORE_LIST = BASE_URL + "/store/get_store_list"; //门店列表
    String STORE_DETAIL = BASE_URL + "/store/app_store_info"; //门店详情
    String MERCHANT_STORE = BASE_URL + "/merchant/merchant_store"; //商户下所有门店
    String ADD_STORE_SEND_CODE = BASE_URL + "/store/send_code"; //新增门店短信接口，参数  phone,  原商户手机号
    String ADD_STORE = BASE_URL + "/store/add_store"; //新增门店
    String STORE_DEVICE_LIST = BASE_URL + "/store/app_store_device"; //门店下所有终端设备列表   参数   store_id
    String SALESMAN_STATISTICS = BASE_URL + "/admin/salesman_statistics"; //数据统计
    String ACTIVATION_CODE = BASE_URL + "/device/activation_code"; //激活设备  参数需加密
    String DATA_STATISTICS = BASE_URL + "/Statistics/get_data_statistics";
    String TEAM_STATISTICS = BASE_URL + "/Statistics/get_team_statistics";
    String PERSONNEL_STATISTICS = BASE_URL + "/Statistics/get_peoples_statistics";
    String MERCHANT_STATISTICS = BASE_URL + "/Statistics/get_people_merchants_statistics";
    String STORE_STATISTICS = BASE_URL + "/Statistics/get_people_stores_statistics";
}
