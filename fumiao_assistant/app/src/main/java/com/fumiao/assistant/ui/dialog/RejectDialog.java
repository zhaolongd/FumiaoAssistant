package com.fumiao.assistant.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fumiao.assistant.R;
import com.fumiao.core.ui.CoreDialog;

/**
 * Author: XieBoss
 * Date: 2019/9/22 0022 10:11
 *
 * @Description:
 */
public class RejectDialog extends CoreDialog {

    TextView tvMsg;
    TextView tvConfirm;


    public RejectDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_reject);
        tvMsg = findViewById(R.id.tv_msg);
        tvConfirm = findViewById(R.id.tv_confirm);


    }

    public void show(String msg, View.OnClickListener clickListener) {
        tvMsg.setText(msg);
        tvConfirm.setOnClickListener(clickListener);
        super.show();
    }



}
