package com.fumiao.assistant.ui.adapter;

import android.content.Context;

import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.merchant.StoreBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.DateUtils;

import java.util.List;

public class StoreListAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public StoreListAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean) {
        super.convert(holder, bean);
        StoreBean storeBean = (StoreBean) bean;
        holder.setText(R.id.tv_store_status, storeBean.getStatus_name());
        holder.setText(R.id.tv_create_time, "创建时间：" + storeBean.getCreate_time());
        holder.setText(R.id.tv_store_name, storeBean.getName());
        holder.setText(R.id.tv_store_number, "门店号：" + storeBean.getSerial_number());
        holder.setText(R.id.tv_contacts, "联系人：" + storeBean.getRealname());
        holder.setText(R.id.tv_store_area, "地区：" + storeBean.getPca());
        holder.setText(R.id.tv_merchant_name, "所属商户：" + storeBean.getMerchant_name());
    }
}
