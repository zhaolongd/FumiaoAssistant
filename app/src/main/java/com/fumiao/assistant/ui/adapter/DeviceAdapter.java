package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.DeviceBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

public class DeviceAdapter extends CoreRecycleAdapter {

    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public DeviceAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        final DeviceBean dataBean = (DeviceBean) bean;
        String remark = dataBean.getRemark();
        if(remark.equals("")){
            remark  = dataBean.getStore_name() + "收款设备";
        }
        holder.setText(R.id.tv_remark, remark);
        holder.setText(R.id.tv_no, "设备号：" + dataBean.getDevice_sn());
        holder.setText(R.id.tv_store_name, "所属门店：" + dataBean.getStore_name());
        if(dataBean.getDevice_type() == 1){
            holder.setImageResource(R.id.iv_device_bg,  R.mipmap.box_device_bg);
        }else {
            holder.setImageResource(R.id.iv_device_bg,  R.mipmap.cashier_device_bg);
        }
    }
}
