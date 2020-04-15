package com.fumiao.assistant.ui.adapter;

import android.content.Context;

import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.data.PersonnelBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

/**
 * Created by zhaolong.
 * Description: 人员交易数据
 * Date: 2020/3/4 0004 11:13
 */
public class PersonnelAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    private int statistics_flag = 1; // 1:商户，2:门店
    public PersonnelAdapter(Context context, int layoutId, List<? extends CoreBean> data, int type) {
        super(context, layoutId, data);
        statistics_flag = type;
    }
    public void update(int type){
        statistics_flag = type;
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);

        PersonnelBean person = (PersonnelBean) bean;
        holder.setText(R.id.tv_name, person.getPeopel().getName());
        if(statistics_flag == 1){
            holder.setText(R.id.tv_yestoday_active, person.getDatas().getYesterday_active_merchants());
        }else {
            holder.setText(R.id.tv_yestoday_active, person.getDatas().getYesterday_active_stores());
        }
        holder.setText(R.id.tv_yestoday_count, person.getDatas().getYesterday_total_count());
        holder.setText(R.id.tv_yestoday_amount, person.getDatas().getYesterday_total_amount());
        holder.setText(R.id.tv_cumulative_amount, person.getDatas().getCumulative_amount());
        holder.setText(R.id.tv_month_growth_rate, person.getDatas().getMonth_monthly_growth_rate()+ "%");
    }
}
