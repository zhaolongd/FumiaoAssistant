package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.BankProvinceBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;

import java.util.List;

public class BankProvinceAdapter  extends CoreRecycleAdapter{
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public BankProvinceAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        BankProvinceBean bankProvinceBean = (BankProvinceBean) bean;
        holder.setText(R.id.name, bankProvinceBean.getProvince_name());
    }
}
