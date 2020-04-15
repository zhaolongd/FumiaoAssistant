package com.fumiao.assistant.mvp.merchant;

import com.fumiao.assistant.mvp.base.BaseView;

public interface StoreAddView extends BaseView{
    void sendCodeSuccess(String msg);
    void storeAddSuccess(String msg);
}
