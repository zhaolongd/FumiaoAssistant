package com.fumiao.assistant.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.LoginBean;
import com.fumiao.assistant.event.LoginEvent;
import com.fumiao.assistant.mvp.main.LoginPresenter;
import com.fumiao.assistant.mvp.main.LoginView;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhaolong on 2019/9/12.
 */
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.ed_user_account)
    EditText edUserAccount;
    @BindView(R.id.ed_user_password)
    EditText edUserPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        edUserAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (edUserAccount.getText().toString().trim().isEmpty() || edUserPassword.getText().toString().trim().isEmpty()) {
                    btnLogin.setBackgroundResource(R.drawable.btn_unclickable_bg);
                } else {
                    btnLogin.setBackgroundResource(R.drawable.btn_clickable_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edUserAccount.getText().toString().trim().isEmpty() || edUserPassword.getText().toString().trim().isEmpty()) {
                    btnLogin.setBackgroundResource(R.drawable.btn_unclickable_bg);
                } else {
                    btnLogin.setBackgroundResource(R.drawable.btn_clickable_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this, this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (edUserAccount.getText().toString().isEmpty() || edUserPassword.getText().toString().isEmpty()) {
            ToastUtil.show(getString(R.string.account_password_empty_tips));
            return;
        }
        mvpPresenter.login(edUserAccount.getText().toString(), edUserPassword.getText().toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 过滤按键动作
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void loginSuccess(LoginBean data) {
        if(data != null){
            ToastUtil.show(data.getMsg());
            SPUtil.getInstance().putBoolean(IS_LOGIN, true);
            SPUtil.getInstance().putString(USER_PHONE, data.getInfo().getMobile());
            jumpActivity(MainActivity.class);
            EventBus.getDefault().post(new LoginEvent(LOGIN_SUCCESS));
        }
    }
}
