package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.InComingDetailBean;
import com.fumiao.assistant.bean.home.InComingResultBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class NormalMerchantInComingPresenter  extends BasePresenter<NormalMerchantInComingView> {

    public NormalMerchantInComingPresenter(NormalMerchantInComingView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     *  普通商户进件提交
     */
    public void normalMerchantIncoming(InComing inComing){
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "app");
        httpParams.put("merchant_type", "2");
        httpParams.put("cate_id", inComing.getCate_id());
        httpParams.put("cate_name", inComing.getCate_name());
        httpParams.put("mecDisNm", inComing.getMecDisNm());
        httpParams.put("mblNo", inComing.getMblNo());
        httpParams.put("haveLicenseNo", inComing.getHaveLicenceNo()); //资质类型 02 个体户 03 企业
        httpParams.put("settleType", inComing.getSettleType()); //结算方式 03 T1 04 D1
        httpParams.put("cprRegNmCn", inComing.getCprRegNmCn()); //营业执照注册名称
        httpParams.put("registCode", inComing.getRegistCode()); //营业执照注册号
        httpParams.put("cprRegAddr", inComing.getCprRegAddr());
        httpParams.put("regProvCd", inComing.getRegProvCd());
        httpParams.put("regCityCd", inComing.getRegCityCd());
        httpParams.put("regDistCd", inComing.getRegDistCd());
        httpParams.put("province_name", inComing.getRegProv());
        httpParams.put("city_name", inComing.getRegCity());
        httpParams.put("area_name", inComing.getRegDist());
//        httpParams.put("wxQrcodeList", inComing.getWxQrcodeList());
//        httpParams.put("aliQrcodeList", inComing.getAliQrcodeList());
//        httpParams.put("thousandQrcodeList", inComing.getThousandQrcodeList());
//        httpParams.put("highestQrcodeList", inComing.getHighestQrcodeList());
        httpParams.put("mccCd", inComing.getMccCd()); //商户大类
        httpParams.put("mcc_name", inComing.getMcc_name()); //商户大类 名称
        httpParams.put("identityName", inComing.getIdentityName()); //法人姓名
        httpParams.put("identityNo", inComing.getIdentityNo()); //法人证件号
        httpParams.put("actTyp", inComing.getActTyp()); //结算账户类型 00对公 01对私
        httpParams.put("actNm", inComing.getActNm());
        httpParams.put("stmManIdNo", inComing.getStmManIdNo());
        httpParams.put("people_type", inComing.getPeople_type()); //法人或者非法人 1法人，2非法人
        httpParams.put("actNo", inComing.getActNo());
        httpParams.put("lbnkNo", inComing.getLbnkNo());
        httpParams.put("lbnkNm", inComing.getLbnkNm());
        httpParams.put("licensePic", inComing.getLicensePic()); // 营业执照照片
        httpParams.put("legalPersonidPositivePic", inComing.getLegalPersonidPositivePic());
        httpParams.put("legalPersonidOppositePic", inComing.getLegalPersonidOppositePic());
        httpParams.put("openingAccountLicensePic", inComing.getOpeningAccountLicensePic()); // 开户许可证 对私非必填
//        String actTyp = inComing.getActTyp();
//        int people_type = inComing.getPeople_type();
        if(inComing.getActTyp().equals("01") && inComing.getPeople_type() == 1){
            httpParams.put("settlePersonIdcardPositive", inComing.getLegalPersonidPositivePic());
            httpParams.put("settlePersonIdcardOpposite", inComing.getLegalPersonidOppositePic());
        }else {
            httpParams.put("settlePersonIdcardPositive", inComing.getSettlePersonIdcardPositive());
            httpParams.put("settlePersonIdcardOpposite", inComing.getSettlePersonIdcardOpposite());
        }
        httpParams.put("bankCardPositivePic", inComing.getBankCardPositivePic());
        httpParams.put("bankCardOppositePic", inComing.getBankCardOppositePic());
        httpParams.put("storePic", inComing.getStorePic());
        httpParams.put("businessPlacePic", inComing.getBusinessPlacePic());
        httpParams.put("insideScenePic", inComing.getInsideScenePic());
        httpParams.put("letterOfAuthPic", inComing.getLetterOfAuthPic());
        OkGo.<BaseResponse<InComingResultBean>>post(NORMAL_MERCHANT_INCOMING)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<InComingResultBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<InComingResultBean>> response) {
                        mvpView.inComingSuccess(response.body().data);
                    }
                });
    }

    public void getInComingDetail(String id){
        OkGo.<BaseResponse<InComingDetailBean>>post(MERCHANT_DETAIL)
                .params("merchant_id", id)
                .execute(new JsonCallback<BaseResponse<InComingDetailBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<InComingDetailBean>> response) {
                        mvpView.showInComingDetail(response.body().data);
                    }
                });

    }

    /**
     * 驳回重新提交
     */
    public void rejectNormalMerchantIncoming(InComing inComing){
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "app");
        httpParams.put("phone", inComing.getMblNo());
        httpParams.put("merchant_name", inComing.getMerchant_name());  //原有的商户名称
        httpParams.put("member_id", inComing.getMember_id());
        httpParams.put("merchant_id", inComing.getMerchant_id());
        httpParams.put("store_id", inComing.getStore_id());
        httpParams.put("incoming_parts_id", inComing.getIncoming_parts_id());
        httpParams.put("merchant_type", "2");

        httpParams.put("cate_id", inComing.getCate_id());
        httpParams.put("cate_name", inComing.getCate_name());
        httpParams.put("mecDisNm", inComing.getMecDisNm());
        httpParams.put("mblNo", inComing.getMblNo());
        httpParams.put("haveLicenseNo", inComing.getHaveLicenceNo()); //资质类型 02 个体户 03 企业
        httpParams.put("settleType", inComing.getSettleType()); //结算方式 03 T1 04 D1
        httpParams.put("cprRegNmCn", inComing.getCprRegNmCn()); //营业执照注册名称
        httpParams.put("registCode", inComing.getRegistCode()); //营业执照注册号
        httpParams.put("cprRegAddr", inComing.getCprRegAddr());
        httpParams.put("regProvCd", inComing.getRegProvCd());
        httpParams.put("regCityCd", inComing.getRegCityCd());
        httpParams.put("regDistCd", inComing.getRegDistCd());
        httpParams.put("province_name", inComing.getRegProv());
        httpParams.put("city_name", inComing.getRegCity());
        httpParams.put("area_name", inComing.getRegDist());
//        httpParams.put("wxQrcodeList", inComing.getWxQrcodeList());
//        httpParams.put("aliQrcodeList", inComing.getAliQrcodeList());
//        httpParams.put("thousandQrcodeList", inComing.getThousandQrcodeList());
//        httpParams.put("highestQrcodeList", inComing.getHighestQrcodeList());
        httpParams.put("mccCd", inComing.getMccCd()); //商户大类
        httpParams.put("mcc_name", inComing.getMcc_name()); //商户大类 名称
        httpParams.put("identityName", inComing.getIdentityName()); //法人姓名
        httpParams.put("identityNo", inComing.getIdentityNo()); //法人证件号
        httpParams.put("actTyp", inComing.getActTyp()); //结算账户类型 00对公 01对私
        httpParams.put("actNm", inComing.getActNm());
        httpParams.put("stmManIdNo", inComing.getStmManIdNo());
        httpParams.put("people_type", inComing.getPeople_type()); //法人或者非法人 1法人，2非法人
        httpParams.put("actNo", inComing.getActNo());
        httpParams.put("lbnkNo", inComing.getLbnkNo());
        httpParams.put("lbnkNm", inComing.getLbnkNm());
        httpParams.put("licensePic", inComing.getLicensePic()); // 营业执照照片
        httpParams.put("legalPersonidPositivePic", inComing.getLegalPersonidPositivePic());
        httpParams.put("legalPersonidOppositePic", inComing.getLegalPersonidOppositePic());
        httpParams.put("openingAccountLicensePic", inComing.getOpeningAccountLicensePic()); // 开户许可证 对私非必填
//        String actTyp = inComing.getActTyp();
//        int people_type = inComing.getPeople_type();
        if(inComing.getActTyp().equals("01") && inComing.getPeople_type() == 1){
            httpParams.put("settlePersonIdcardPositive", inComing.getLegalPersonidPositivePic());
            httpParams.put("settlePersonIdcardOpposite", inComing.getLegalPersonidOppositePic());
        }else {
            httpParams.put("settlePersonIdcardPositive", inComing.getSettlePersonIdcardPositive());
            httpParams.put("settlePersonIdcardOpposite", inComing.getSettlePersonIdcardOpposite());
        }
        httpParams.put("bankCardPositivePic", inComing.getBankCardPositivePic());
        httpParams.put("bankCardOppositePic", inComing.getBankCardOppositePic());
        httpParams.put("storePic", inComing.getStorePic());
        httpParams.put("businessPlacePic", inComing.getBusinessPlacePic());
        httpParams.put("insideScenePic", inComing.getInsideScenePic());
        httpParams.put("letterOfAuthPic", inComing.getLetterOfAuthPic());
        OkGo.<BaseResponse<InComingResultBean>>post(REJECT_NORMAL_MERCHANT_INCOMING)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<InComingResultBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<InComingResultBean>> response) {
                        mvpView.rejectInComingSuccess(response.body().data);
                    }
                });
    }
}
