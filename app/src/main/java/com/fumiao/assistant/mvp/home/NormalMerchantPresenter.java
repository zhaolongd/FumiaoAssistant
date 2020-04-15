package com.fumiao.assistant.mvp.home;

import android.app.Activity;

import com.fumiao.assistant.bean.home.UploadImageBean;
import com.fumiao.assistant.mvp.base.BasePresenter;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;

public class NormalMerchantPresenter extends BasePresenter<NormalMerchantView> {
    public NormalMerchantPresenter(NormalMerchantView mvpView, Activity activity) {
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


}
