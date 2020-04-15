package com.fumiao.core.okgo.model;

import java.io.Serializable;

/**
 * Created by chee on 2018/6/4.
 */
public class BaseResponse<T> implements Serializable {

    public boolean status;
    public int code;
    public String msg;
    public T data;


    @Override
    public String toString() {
        return "BaseResponse{\n" +//
                "\tstatus=" + status + "\n" +//
                "\tcode=" + code + "\n" +//
                "\tmsg='" + msg + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }


}
