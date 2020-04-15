package com.fumiao.assistant.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fumiao.assistant.R;
import com.fumiao.core.ui.CoreDialog;

/**
 * Created by zhaolong on 2019/9/12.
 */
public class MsgDialog extends CoreDialog {

    TextView tv_msg;
    Button btn_close, btn_confirm;

    public MsgDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_msg);
        tv_msg = findViewById(R.id.tv_msg);
        btn_close = findViewById(R.id.btn_close);
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show(String msg, View.OnClickListener clickListener) {
        tv_msg.setText(msg);
        btn_confirm.setOnClickListener(clickListener);
        super.show();
    }

    public void show(String msg, String leftText, String rightText, View.OnClickListener leftClick, View.OnClickListener rightClick) {
        tv_msg.setText(msg);
        btn_close.setText(leftText);
        btn_confirm.setText(rightText);

        if (leftText.equals("")){
            btn_close.setVisibility(View.GONE);
            setCanceledOnTouchOutside(false);
            setCancelable(false);
        }

        if (rightText.equals("")){
            btn_confirm.setVisibility(View.GONE);
        }

        if (leftText.isEmpty()) {
            btn_close.setVisibility(View.GONE);
        }

        if (rightText.isEmpty()) {
            btn_confirm.setVisibility(View.GONE);
        }

        btn_close.setOnClickListener(leftClick);
        btn_confirm.setOnClickListener(rightClick);
        super.show();
    }

}
