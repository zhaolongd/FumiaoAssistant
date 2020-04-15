package com.fumiao.core.okgo;

import android.app.Activity;

import com.fumiao.core.uitls.ToastUtil;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.fumiao.core.app.CoreActivity;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.L;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.UnknownHostException;
import static com.fumiao.core.okgo.JsonConvert.SUCCESS;


/**
 * Created by chee on 2018/6/4.
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Activity activity;
    private boolean showLoad;
    private Type type;
    private Class<T> clazz;

    public JsonCallback(Activity activity) {
        this.activity = (CoreActivity) activity;
        this.showLoad = true;
    }

    public JsonCallback(Activity activity, boolean showLoad) {
        this.activity = (CoreActivity) activity;
        this.showLoad = showLoad;
    }

    @Override
    public void onSuccess(Response<T> response) {
        if (((BaseResponse) response.body()).code == SUCCESS) {
            onSuccessCallback(response);
        } else {
            ToastUtil.show(((BaseResponse) response.body()).msg);
        }
    }

    public abstract void onSuccessCallback(Response<T> response);

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        L.e("OkGo", response.getException().getMessage());
        Throwable exception = response.getException();
        if(exception instanceof UnknownHostException ||exception instanceof ConnectException){
            ToastUtil.show("与服务器连接失败，请稍后重试！");
        }else {
           ToastUtil.show(response.getException().getMessage());
        }
    }

    @Override
    public void onStart(Request request) {
        if (activity instanceof CoreActivity && showLoad) {
            ((CoreActivity) activity).showLoad();
        }
    }

    @Override
    public void onCacheSuccess(Response response) {
    }

    @Override
    public void onFinish() {
        if (activity instanceof CoreActivity && showLoad) {
            ((CoreActivity) activity).hintLoad();
        }
    }

    @Override
    public void uploadProgress(Progress progress) {
    }

    @Override
    public void downloadProgress(Progress progress) {
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }
        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

}
