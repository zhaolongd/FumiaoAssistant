package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import android.util.Log;

import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.MerchantTypeListBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;

import java.util.List;

/**
 * Author: XieBoss
 * Date: 2019/10/11 0011 18:53
 *
 * @Description:
 */
public class MerchantTypeAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public MerchantTypeAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        MerchantTypeListBean.MerchantTypeBean merchantTypeBean = (MerchantTypeListBean.MerchantTypeBean) bean;
        holder.setText(R.id.tv_type_name, merchantTypeBean.getName());

        if(merchantTypeBean.isSelect()){
            holder.setTextBg(R.id.tv_type_name, R.drawable.round_select_bg);
        }else {
            holder.setTextBg(R.id.tv_type_name, R.drawable.round_white_bg);
        }
    }
}
