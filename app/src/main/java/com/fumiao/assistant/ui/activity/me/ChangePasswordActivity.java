package com.fumiao.assistant.ui.activity.me;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.event.LoginEvent;
import com.fumiao.assistant.mvp.me.ChangePasswordPresenter;
import com.fumiao.assistant.mvp.me.ChangePasswordView;
import com.fumiao.assistant.ui.activity.LoginActivity;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: XieBoss
 * Date: 2019/9/17 0017 10:27
 *
 * @Description:
 */
public class ChangePasswordActivity extends MvpActivity<ChangePasswordPresenter> implements ChangePasswordView {
    @BindView(R.id.password_status)
    ImageView passwordStatus;
    @BindView(R.id.new_password_status)
    ImageView newPasswordStatus;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.btn_change)
    Button btnChange;
    String phone = "";//手机号
    @BindView(R.id.ed_new_psd)
    EditText edNewPsd;
    @BindView(R.id.ed_old_psd)
    EditText edOldPsd;
    //密码是否显示和隐藏标识
    private boolean isShowPassword = false;

    @Override
    protected ChangePasswordPresenter createPresenter() {
        return new ChangePasswordPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
    }

    private void init() {
        setTitle("修改密码");

        edOldPsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (edOldPsd.getText().toString().trim().isEmpty() || edNewPsd.getText().toString().trim().isEmpty()) {
                    btnChange.setBackgroundResource(R.drawable.btn_unclickable_bg);
                } else {
                    btnChange.setBackgroundResource(R.drawable.btn_clickable_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edNewPsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (edOldPsd.getText().toString().trim().isEmpty() || edNewPsd.getText().toString().trim().isEmpty()) {
                    btnChange.setBackgroundResource(R.drawable.btn_unclickable_bg);
                } else {
                    btnChange.setBackgroundResource(R.drawable.btn_clickable_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        phone = getIntent().getStringExtra("phone");
        tvUsername.setText(phone);
    }


    @OnClick({R.id.password_status, R.id.new_password_status, R.id.btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.password_status:
                isShowPassword = isShowPassword ? false : true;
                updatePasswordView(isShowPassword);
                break;
            case R.id.new_password_status:
                isShowPassword = isShowPassword ? false : true;
                updateNewPasswordView(isShowPassword);
                break;
            case R.id.btn_change:
                if (edOldPsd.getText().toString().isEmpty()) {
                    ToastUtil.show(edOldPsd.getHint().toString());
                    return;
                }
                if (edNewPsd.getText().toString().isEmpty()) {
                    ToastUtil.show(edNewPsd.getHint().toString());
                    return;
                }
                mvpPresenter.changePassword(edOldPsd.getText().toString(), edNewPsd.getText().toString(), phone);
                break;
        }
    }

    private void updatePasswordView(boolean isShowPassword) {
        if (isShowPassword) {
            //显示密码
            edOldPsd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordStatus.setImageResource(R.mipmap.ic_show_pd);
        } else {
            //隐藏密码
            edOldPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordStatus.setImageResource(R.mipmap.ic_hide_pd);
        }
        edOldPsd.setSelection(edOldPsd.getText().length());
    }


    private void updateNewPasswordView(boolean isShowPassword) {
        if (isShowPassword) {
            //显示密码
            edNewPsd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            newPasswordStatus.setImageResource(R.mipmap.ic_show_pd);
        } else {
            //隐藏密码
            edNewPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            newPasswordStatus.setImageResource(R.mipmap.ic_hide_pd);
        }
        edNewPsd.setSelection(edNewPsd.getText().length());
    }

    @Override
    public void changeSuccess(String msg) {
        ToastUtil.show("修改成功");
        SPUtil.getInstance().putBoolean(IS_LOGIN, false);
        EventBus.getDefault().post(new LoginEvent(LOGIN_EXIT));
        jumpActivity(LoginActivity.class);
    }
}
