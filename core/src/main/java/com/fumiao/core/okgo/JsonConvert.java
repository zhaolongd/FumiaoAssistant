package com.fumiao.core.okgo;

import android.util.Log;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.okgo.model.SimpleResponse;
import com.fumiao.core.uitls.L;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by chee on 2018/6/4.
 */
public class JsonConvert<T> implements Converter<T> {

    private Class<T> clazz;
    private Type type;
    public static int SUCCESS = 1;
    public static int LOGIN = 20004; //请求超时


    public JsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonConvert(Type type) {
        this.type = type;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用

        // 如果你对这里的代码原理不清楚，可以看这里的详细原理说明: https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        // 如果你对这里的代码原理不清楚，可以看这里的详细原理说明: https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        // 如果你对这里的代码原理不清楚，可以看这里的详细原理说明: https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback

        if (type == null) {
            if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                return parseClass(response, clazz);
            }
        }

        if (type instanceof ParameterizedType) {
            return parseParameterizedType(response, (ParameterizedType) type);
        } else if (type instanceof Class) {
            return parseClass(response, (Class<?>) type);
        } else {
            return parseType(response, type);
        }
    }

    private T parseClass(Response response, Class<?> rawType) throws Exception {
        if (rawType == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());

        if (rawType == String.class) {
            //noinspection unchecked
            return (T) body.string();
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(body.string());
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(body.string());
        } else {
            T t = Convert.fromJson(jsonReader, rawType);
            response.close();
            return t;
        }
    }

    private T parseType(Response response, Type type) throws Exception {
        if (type == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());
        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        T t = Convert.fromJson(jsonReader, type);
        response.close();
        return t;
    }

    private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
        if (type == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        String responseJson = response.body().string();
        BaseResponse baseData = Convert.fromJson(responseJson, BaseResponse.class);
        if (baseData.code != SUCCESS) {
            response.close();
            throw new IllegalStateException(baseData.msg);
        }

        Type rawType = type.getRawType();                     // 泛型的实际类型
        Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数
        if (rawType != BaseResponse.class) {
            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
            T t = Convert.fromJson(responseJson, type);
            response.close();
            return t;
        } else {
            if (typeArgument == Void.class) {
                // 泛型格式如下： new JsonCallback<BaseResponse<Void>>(this)
                SimpleResponse simpleResponse = Convert.fromJson(responseJson, SimpleResponse.class);
                response.close();
                //noinspection unchecked
                return (T) simpleResponse.toLzyResponse();
            } else {
                // 泛型格式如下： new JsonCallback<BaseResponse<内层JavaBean>>(this)
                BaseResponse lzyResponse = Convert.fromJson(responseJson, type);
                response.close();
                int code = lzyResponse.code;
                //这里的0是以下意思
                //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改
                if (code == SUCCESS) {
                    //noinspection unchecked
                    return (T) lzyResponse;
                }else {
                    //直接将服务端的错误信息抛出，onError中可以获取
                    throw new IllegalStateException("错误代码：" + code + "，错误信息：" + lzyResponse.msg);
                }
            }
        }
    }

}
