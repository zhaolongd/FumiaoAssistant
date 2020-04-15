package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.data.TeamBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

/**
 * Created by zhaolong.
 * Description: 团队列表适配器
 * Date: 2020/3/3 0003 13:51
 */
public class TeamAdapter extends CoreRecycleAdapter {

    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    private int statistics_flag = 1; // 1:商户，2:门店
    public TeamAdapter(Context context, int layoutId, List<? extends CoreBean> data, int type) {
        super(context, layoutId, data);
        statistics_flag = type;
    }

    public void update(int type){
        statistics_flag = type;
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        TeamBean teamBean = (TeamBean) bean;
        holder.setText(R.id.tv_team_name, teamBean.getAgency_name());
        holder.setText(R.id.tv_peoples, teamBean.getPeoples()+ "人");
        holder.setText(R.id.tv_sum_totals, teamBean.getDatas().getYesterday_total_amount());
        holder.setText(R.id.tv_sum_totals_compare, teamBean.getDatas().getYesterday_mom_amount());
        holder.setText(R.id.tv_sum_totals_per_capita, teamBean.getDatas().getYesterday_per_capita_amount()+ "元");
        if(statistics_flag == 1){
            holder.setText(R.id.tv_active_title, "昨日活跃商户");
            holder.setText(R.id.tv_active, teamBean.getDatas().getYesterday_active_merchants()+ "(家)");
            holder.setText(R.id.tv_active_compare, teamBean.getDatas().getYesterday_mom_active_merchants());
            holder.setText(R.id.tv_active_per_capita, teamBean.getDatas().getYesterday_per_capita_active_merchants() +  "(家)");
        }else {
            holder.setText(R.id.tv_active_title, "昨日活跃门店");
            holder.setText(R.id.tv_active, teamBean.getDatas().getYesterday_active_stores()+ "(家)");
            holder.setText(R.id.tv_active_compare, teamBean.getDatas().getYesterday_mom_active_stores());
            holder.setText(R.id.tv_active_per_capita, teamBean.getDatas().getYesterday_per_capita_active_stores() +  "(家)");
        }
    }
}
