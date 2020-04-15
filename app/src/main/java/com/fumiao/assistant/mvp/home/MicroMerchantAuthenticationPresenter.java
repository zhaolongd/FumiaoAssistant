package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.BankCardOcrBean;
import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.bean.home.InComingResultBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.Convert;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 11:32
 *
 * @Description:
 */
public class MicroMerchantAuthenticationPresenter extends BasePresenter<MicroMerchantAuthenticationView> {
    public MicroMerchantAuthenticationPresenter(MicroMerchantAuthenticationView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void uploadImage(String image, int code){
        OkGo.<BaseResponse<UploadImageBean>>post(UPLOAD_IMAGE)
                .params("upimgs", new File(image))
                .execute(new JsonCallback<BaseResponse<UploadImageBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<UploadImageBean>> response) {
                        mvpView.uploadImageUrl(response.body().data, code, image);
                    }
                });
    }

    /**
     *  进件提交
     */
    public void inComingPart(InComing inComing){
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "app");
        httpParams.put("merchant_type", "1");
        httpParams.put("cate_id", inComing.getCate_id());
        httpParams.put("cate_name", inComing.getCate_name());
        httpParams.put("mecDisNm", inComing.getMecDisNm());
        httpParams.put("mblNo", inComing.getMblNo());
//        httpParams.put("wxQrcodeList", inComing.getWxQrcodeList());
//        httpParams.put("aliQrcodeList", inComing.getAliQrcodeList());
//        httpParams.put("thousandQrcodeList", inComing.getThousandQrcodeList());
//        httpParams.put("highestQrcodeList", inComing.getHighestQrcodeList());
        httpParams.put("settleType", "04");
        httpParams.put("cprRegAddr", inComing.getCprRegAddr());
        httpParams.put("regProvCd", inComing.getRegProvCd());
        httpParams.put("regCityCd", inComing.getRegCityCd());
        httpParams.put("regDistCd", inComing.getRegDistCd());
        httpParams.put("province_name", inComing.getRegProv());
        httpParams.put("city_name", inComing.getRegCity());
        httpParams.put("area_name", inComing.getRegDist());
        httpParams.put("actNm", inComing.getActNm());
        httpParams.put("stmManIdNo", inComing.getStmManIdNo());
        httpParams.put("actNo", inComing.getActNo());
        httpParams.put("lbnkNo", inComing.getLbnkNo());
        httpParams.put("lbnkNm", inComing.getLbnkNm());
        httpParams.put("legalPersonidPositivePic", inComing.getLegalPersonidPositivePic());
        httpParams.put("legalPersonidOppositePic", inComing.getLegalPersonidOppositePic());
        httpParams.put("bankCardPositivePic", inComing.getBankCardPositivePic());
        httpParams.put("bankCardOppositePic", inComing.getBankCardOppositePic());
        httpParams.put("storePic", inComing.getStorePic());
        httpParams.put("businessPlacePic", inComing.getBusinessPlacePic());
        httpParams.put("insideScenePic", inComing.getInsideScenePic());
        OkGo.<BaseResponse<InComingResultBean>>post(INCOMING_PARTS)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<InComingResultBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<InComingResultBean>> response) {
                        mvpView.inComingSuccess(response.body().data);
                    }
                });
    }

    /**
     * 驳回重新提交
     */
    public void rejectInComingPart(InComing inComing){
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", "app");
        httpParams.put("merchant_id", inComing.getMerchant_id());
        httpParams.put("merchant_type", "1");
        httpParams.put("phone", inComing.getMblNo());
        httpParams.put("merchant_name", inComing.getMerchant_name());  //原有的商户名称
        httpParams.put("member_id", inComing.getMember_id());
        httpParams.put("store_id", inComing.getStore_id());
        httpParams.put("incoming_parts_id", inComing.getIncoming_parts_id());

        httpParams.put("cate_id", inComing.getCate_id());
        httpParams.put("cate_name", inComing.getCate_name());
        httpParams.put("mecDisNm", inComing.getMecDisNm());
        httpParams.put("mblNo", inComing.getMblNo());
//        httpParams.put("wxQrcodeList", inComing.getWxQrcodeList());
//        httpParams.put("aliQrcodeList", inComing.getAliQrcodeList());
//        httpParams.put("thousandQrcodeList", inComing.getThousandQrcodeList());
//        httpParams.put("highestQrcodeList", inComing.getHighestQrcodeList());
        httpParams.put("settleType", "04");
        httpParams.put("cprRegAddr", inComing.getCprRegAddr());
        httpParams.put("regProvCd", inComing.getRegProvCd());
        httpParams.put("regCityCd", inComing.getRegCityCd());
        httpParams.put("regDistCd", inComing.getRegDistCd());
        httpParams.put("province_name", inComing.getRegProv());
        httpParams.put("city_name", inComing.getRegCity());
        httpParams.put("area_name", inComing.getRegDist());
        httpParams.put("actNm", inComing.getActNm());
        httpParams.put("stmManIdNo", inComing.getStmManIdNo());
        httpParams.put("actNo", inComing.getActNo());
        httpParams.put("lbnkNo", inComing.getLbnkNo());
        httpParams.put("lbnkNm", inComing.getLbnkNm());
        httpParams.put("legalPersonidPositivePic", inComing.getLegalPersonidPositivePic());
        httpParams.put("legalPersonidOppositePic", inComing.getLegalPersonidOppositePic());
        httpParams.put("bankCardPositivePic", inComing.getBankCardPositivePic());
        httpParams.put("bankCardOppositePic", inComing.getBankCardOppositePic());
        httpParams.put("storePic", inComing.getStorePic());
        httpParams.put("businessPlacePic", inComing.getBusinessPlacePic());
        httpParams.put("insideScenePic", inComing.getInsideScenePic());
        OkGo.<BaseResponse<InComingResultBean>>post(REJECT_INCOMING_PARTS)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<InComingResultBean>>(activity, true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<InComingResultBean>> response) {
                        mvpView.rejectInComingSuccess(response.body().data);
                    }
                });
    }

    public void ocrIDCardInfo(String image){
        OkGo.<String>post(OCR_ID_CARD)
                .params("api_key", "VT54TMMhb4YVHD0y7DUNSBumcBlbJscn")
                .params("api_secret", "amoFAC5E_BVO2hYlRWkGB46bfR9PzRMi")
                .params("image_file", new File(image))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        IDCardOcrBean data = Convert.fromJson(response.body().toString(), IDCardOcrBean.class);
                        mvpView.ocrIDCardSuccess(data, image);
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtil.show("身份证图片识别失败");
                    }
                });
    }

    public void ocrBankCardInfo(String image){
        OkGo.<String>post(OCR_BANK_CARD)
                .params("api_key", "VT54TMMhb4YVHD0y7DUNSBumcBlbJscn")
                .params("api_secret", "amoFAC5E_BVO2hYlRWkGB46bfR9PzRMi")
                .params("image_file", new File(image))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BankCardOcrBean data = Convert.fromJson(response.body().toString(), BankCardOcrBean.class);
                        mvpView.ocrBankCardSuccess(data, image);
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtil.show("银行卡图片识别失败");
                    }
                });
    }
}
