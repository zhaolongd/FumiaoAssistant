package com.fumiao.assistant.mvp.merchant;

import com.fumiao.assistant.bean.merchant.StoreListBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface StoreListView extends BaseView{
    void showStoreList(StoreListBean data);
    void stateChangeOnError();
}
