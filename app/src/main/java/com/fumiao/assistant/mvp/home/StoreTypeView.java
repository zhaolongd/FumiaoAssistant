package com.fumiao.assistant.mvp.home;


import com.fumiao.assistant.bean.home.CateListBean;
import com.fumiao.assistant.mvp.base.BaseView;

public interface StoreTypeView extends BaseView {
    void showCateList(CateListBean data);
}
