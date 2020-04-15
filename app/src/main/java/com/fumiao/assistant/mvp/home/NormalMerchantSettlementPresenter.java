package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.BankCardOcrBean;
import com.fumiao.assistant.bean.home.IDCardOcrBean;
import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.Convert;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import java.io.File;

/**
 * Author: XieBoss
 * Date: 2019/10/8 0008 17:00
 *
 * @Description:
 */
public class NormalMerchantSettlementPresenter extends BasePresenter<NormalMerchantSettlementView> {


    public NormalMerchantSettlementPresenter(NormalMerchantSettlementView mvpView, Activity activity) {
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
