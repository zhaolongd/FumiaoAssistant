package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import android.view.View;

import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.CateListBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

public class StoreTypeAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public StoreTypeAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        CateListBean.CateBean cateBean = (CateListBean.CateBean) bean;
        holder.setText(R.id.tv_type_name, cateBean.getName());

        if(cateBean.isSelect()){
            holder.setTextBg(R.id.tv_type_name, R.drawable.round_select_bg);
        }else {
            holder.setTextBg(R.id.tv_type_name, R.drawable.round_white_bg);
        }
        if(cateBean.count <= 0){
            holder.setVisibility(R.id.type_point, View.INVISIBLE);
        }else {
            holder.setText(R.id.type_point, cateBean.count+"");
            holder.setVisibility(R.id.type_point, View.VISIBLE);
        }
    }
}
