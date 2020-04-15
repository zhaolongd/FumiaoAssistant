package com.fumiao.assistant.mvp.merchant;

import com.fumiao.assistant.bean.merchant.MerchantDetailBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface MerchantDetailView extends BaseView {
    void showMerchantDetail(MerchantDetailBean data);
}
