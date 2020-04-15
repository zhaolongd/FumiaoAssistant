package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.InComingBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 18:51
 *
 * @Description:
 */
public class MerchantsAuditAdapter extends CoreRecycleAdapter {
    private String changeStr = "";
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public MerchantsAuditAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        InComingBean detailsBean = (InComingBean) bean;
        String store_name = detailsBean.getStore_name();
        if (changeStr != null && store_name.contains(changeStr)) {
            int index = store_name.indexOf(changeStr);
            int len = changeStr.length();
            Spanned temp = Html.fromHtml(store_name.substring(0, index)
                    + "<font color=#FF8500>"
                    + store_name.substring(index, index + len) + "</font>"
                    + store_name.substring(index + len, store_name.length()));
            holder.setText(R.id.store_name, temp);
        } else {
            holder.setText(R.id.store_name, store_name);
        }
        holder.setText(R.id.order_id, detailsBean.getMerchant_id());
        String merchant_type_name = "";
        //1：小微商户，2：普通商户，3：总店，4：分店）
        switch (detailsBean.getMerchant_type()){
            case 1:
                merchant_type_name = "小微商户";
                break;
            case 2:
                merchant_type_name = "普通商户";
                break;
            case 3:
                merchant_type_name = "总店";
                break;
            case 4:
                merchant_type_name = "分店";
                break;
        }

        String status = "";
        //状态（0：待进件 1：审核中，2：审核通过，3：审核驳回）
        switch (detailsBean.getStatus()){
            case 0:
                status = "待进件";
                holder.setTextColor(R.id.tv_status, R.color.forbidden);
                break;
            case 1:
                status = "审核中";
                holder.setTextColor(R.id.tv_status, R.color.text_press_color);
                break;
            case 2:
                status = "审核通过";
                holder.setTextColor(R.id.tv_status, R.color.normal);
                break;
            case 3:
                status = "审核驳回";
                holder.setTextColor(R.id.tv_status, R.color.forbidden);
                break;
        }
        holder.setText(R.id.tv_status, status);
        holder.setText(R.id.store_type, merchant_type_name);
        int resp = detailsBean.getIncome_resp_info(); //0不可付款   1可付款
        if(resp == 1){
            holder.setVisibility(R.id.iv_incoming_resp, View.VISIBLE);
        }else {
            holder.setVisibility(R.id.iv_incoming_resp, View.INVISIBLE);
        }
    }

    public void changeText(String textStr) {
        this.changeStr = textStr;
    }
}
