package com.fumiao.assistant.ui.adapter;

import android.content.Context;

import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.BankCityBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

public class BankCityAdapter extends CoreRecycleAdapter{
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public BankCityAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        BankCityBean bankCityBean = (BankCityBean) bean;
        holder.setText(R.id.name, bankCityBean.getCity_name());
    }
}
