package com.fumiao.assistant.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import com.fumiao.assistant.R;
import com.fumiao.assistant.bean.home.BankBranchBean;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import java.util.List;

public class BankBranchAdapter extends CoreRecycleAdapter {
    private String changeStr = "";
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public BankBranchAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        BankBranchBean bankBranchBean = (BankBranchBean) bean;
        String branch_bank_name = bankBranchBean.getInterbank_name();
        if (changeStr != null && branch_bank_name.contains(changeStr)) {
            int index = branch_bank_name.indexOf(changeStr);
            int len = changeStr.length();
            Spanned temp = Html.fromHtml(branch_bank_name.substring(0, index)
                    + "<font color=#FF8500>"
                    + branch_bank_name.substring(index, index + len) + "</font>"
                    + branch_bank_name.substring(index + len, branch_bank_name.length()));
            holder.setText(R.id.name, temp);
        } else {
            holder.setText(R.id.name, branch_bank_name);
        }
    }

    public void changeText(String textStr) {
        this.changeStr = textStr;
    }
}
