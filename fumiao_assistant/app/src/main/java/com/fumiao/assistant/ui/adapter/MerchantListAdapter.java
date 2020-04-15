package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.merchant.MerchantBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

public class MerchantListAdapter extends CoreRecycleAdapter{

    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public MerchantListAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        MerchantBean merchantBean = (MerchantBean) bean;
        holder.setText(R.id.tv_merchant_status, merchantBean.getStatus_name());
        String merchant_type = merchantBean.getMerchant_type_name();
        holder.setText(R.id.tv_merchant_type, merchant_type);
        String merchant_name = merchantBean.getName();
        holder.setText(R.id.tv_merchant_name, merchant_name);
        holder.setText(R.id.tv_merchant_number, "商户号：" + merchantBean.getMerchant_id());
        holder.setText(R.id.tv_contacts, "联系人：" + merchantBean.getRealname());
        holder.setText(R.id.tv_merchant_area, "地区：" + merchantBean.getPca());
    }
}
