package com.fumiao.assistant.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.core.ui.CoreDialog;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.widget.KeyEditText;
import com.fumiao.core.widget.SmsCodeView;
import static com.fumiao.assistant.config.KeyConfig.USER_PHONE;

public class AuthorizationDialog  extends CoreDialog {

    TextView tv_msg, tv_phone;
    KeyEditText phone_code;
    Button btn_close, btn_confirm;
    SmsCodeView btnGetPhoneCode;
    String user_phone;

    public AuthorizationDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_authorization);
        user_phone = SPUtil.getInstance().getString(USER_PHONE);
        tv_msg = findViewById(R.id.tv_msg);
        tv_phone = findViewById(R.id.tv_phone);
        phone_code = findViewById(R.id.phone_code);
        btn_close = findViewById(R.id.btn_close);
        btn_confirm = findViewById(R.id.btn_confirm);
        btnGetPhoneCode = findViewById(R.id.btn_get_phone_code); //验证码
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show(String msg, View.OnClickListener clickListener, View.OnClickListener codeClickListener) {
        tv_msg.setText(msg);
        tv_phone.setText(user_phone);
        btn_confirm.setOnClickListener(clickListener);
        btnGetPhoneCode.setOnClickListener(codeClickListener);
        super.show();
    }

    public void show(String msg, String user_phone, View.OnClickListener clickListener, View.OnClickListener codeClickListener) {
        tv_msg.setText(msg);
        tv_phone.setText(user_phone);
        btn_confirm.setOnClickListener(clickListener);
        btnGetPhoneCode.setOnClickListener(codeClickListener);
        super.show();
    }

    public String getVerificationCode(){
       return phone_code.getText();
    }

    public void sendVerificationCodeSuccess(){
        btnGetPhoneCode.start();
    }

}
