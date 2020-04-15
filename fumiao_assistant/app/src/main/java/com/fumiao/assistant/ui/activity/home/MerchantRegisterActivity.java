package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.mvp.home.MerchantRegisterPresenter;
import com.fumiao.assistant.mvp.home.MerchantRegisterView;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.widget.KeyEditText;
import com.fumiao.core.widget.SmsCodeView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhaolong on 2019/9/12.
 */
public class MerchantRegisterActivity extends MvpActivity<MerchantRegisterPresenter> implements MerchantRegisterView {

    @BindView(R.id.user_phone)
    KeyEditText userPhone;
    @BindView(R.id.phone_code)
    KeyEditText phoneCode;
    @BindView(R.id.btn_get_phone_code)
    SmsCodeView btnGetPhoneCode;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private boolean isNormalMerchantIncoming = false; //标识普通商户进件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_register);
        init();
    }

    private void init() {
        setTitle(getString(R.string.merchant_register));
        isNormalMerchantIncoming = getIntent().getBooleanExtra("normal_merchant_incoming", false);
        userPhone.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        userPhone.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userPhone.isEmpty() || phoneCode.isEmpty()) {
                    btnLogin.setBackgroundResource(R.drawable.btn_unclickable_bg);
                } else {
                    btnLogin.setBackgroundResource(R.drawable.btn_clickable_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phoneCode.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userPhone.isEmpty() || phoneCode.isEmpty()) {
                    btnLogin.setBackgroundResource(R.drawable.btn_unclickable_bg);
                } else {
                    btnLogin.setBackgroundResource(R.drawable.btn_clickable_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected MerchantRegisterPresenter createPresenter() {
        return new MerchantRegisterPresenter(this, this);
    }

    @OnClick({R.id.btn_login, R.id.btn_get_phone_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (userPhone.isEmpty()) {
                    ToastUtil.show(userPhone.getHint());
                    return;
                }
                if (phoneCode.isEmpty()) {
                    ToastUtil.show(phoneCode.getHint());
                    return;
                }
                //for test start
                Bundle bundle = new Bundle();
                bundle.putString("phone", userPhone.getText());
                if(isNormalMerchantIncoming){
                    jumpActivity(NormalMerchantInComingActivity.class, bundle);
                }else {
                    jumpActivity(MicroMerchantInfoActivity.class, bundle);
                }
                //end
//                mvpPresenter.checkPhoneCode(userPhone.getText(), phoneCode.getText());
                break;
            case R.id.btn_get_phone_code:
                if (userPhone.isEmpty()) {
                    ToastUtil.show(userPhone.getHint());
                    return;
                }
                mvpPresenter.getVerificationCode(userPhone.getText());
                break;
        }
    }

    @Override
    public void sendVerificationCodeSuccess(String msg) {
        ToastUtil.show(msg);
        btnGetPhoneCode.start();
    }

    @Override
    public void checkPhoneSuccess() {
        Bundle bundle = new Bundle();
        bundle.putString("phone", userPhone.getText());
        if(isNormalMerchantIncoming){
            jumpActivity(NormalMerchantInComingActivity.class, bundle);
        }else {
            jumpActivity(MicroMerchantInfoActivity.class, bundle);
        }
    }
}
