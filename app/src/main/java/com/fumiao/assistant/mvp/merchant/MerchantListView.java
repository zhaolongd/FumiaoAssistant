package com.fumiao.assistant.mvp.merchant;

import com.fumiao.assistant.bean.merchant.MerchantListBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface MerchantListView  extends BaseView{
    void showMerchantList(MerchantListBean data);
    void stateChangeOnError();
}
