package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.data.MerchantStoreStatisticsBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/3/13 0013 16:15
 */
public class MerchantStoreStatisticsAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public MerchantStoreStatisticsAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);

        MerchantStoreStatisticsBean data = (MerchantStoreStatisticsBean) bean;
        holder.setText(R.id.tv_name, data.getName());
        holder.setText(R.id.tv_yesterday_total_amount, data.getDatas().getYesterday_total_amount());
        holder.setText(R.id.tv_yesterday_total_count, data.getDatas().getWeek_mom_amount());
        holder.setText(R.id.tv_week_mom_amount, data.getDatas().getWeek_mom_amount());
    }
}
