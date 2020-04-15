package com.fumiao.assistant.ui.adapter;

import android.content.Context;

import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;

import java.util.List;

public class DraftAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public DraftAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        InComing inComing = (InComing) bean;
        String merchant_name = inComing.getMecDisNm();
        if(merchant_name == null || merchant_name.equals("")){
            holder.setText(R.id.tv_merchant_name, "未填写商户名称");
        }else {
            holder.setText(R.id.tv_merchant_name, inComing.getMecDisNm());
        }
        String name = inComing.getActNm();
        if(name == null || name.equals("")){
            holder.setText(R.id.tv_name, "未填写账户名");
        }else {
            holder.setText(R.id.tv_name, inComing.getActNm());
        }
        holder.setText(R.id.tv_create_time, inComing.getCreateTime());
        holder.setText(R.id.tv_phone, inComing.getMblNo());
    }
}
