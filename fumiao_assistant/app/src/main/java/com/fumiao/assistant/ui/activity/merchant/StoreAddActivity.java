package com.fumiao.assistant.ui.activity.merchant;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.mvp.merchant.StoreAddPresenter;
import com.fumiao.assistant.mvp.merchant.StoreAddView;
import com.fumiao.assistant.tools.SelectAddrModule;
import com.fumiao.assistant.ui.dialog.AuthorizationDialog;
import com.fumiao.core.uitls.Callback;
import com.fumiao.core.uitls.EmojiFilter;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.core.widget.KeyEditText;
import butterknife.BindView;
import butterknife.OnClick;

public class StoreAddActivity extends MvpActivity<StoreAddPresenter> implements StoreAddView {

    @BindView(R.id.store_name)
    KeyEditText storeName;
    @BindView(R.id.contact_name)
    KeyEditText contactName;
    @BindView(R.id.contact_phone)
    KeyEditText contactPhone;
    @BindView(R.id.store_area)
    ItemArrowLayout storeArea;
    @BindView(R.id.store_addr)
    EditText storeAddr;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    String province = "", province_name = "", city = "", city_name = "", area = "", area_name = "";
    SelectAddrModule selectAddrModule;
    AuthorizationDialog authorizationDialog;
    private String verification_code; //验证码
    private String merchant_phone;
    private String merchant_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_add);
        init();
    }

    private void init() {
        setTitle("新增门店");
        merchant_phone = getIntent().getStringExtra("merchant_phone");
        merchant_id = getIntent().getStringExtra("merchant_id");
        storeName.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20), new EmojiFilter()});
        contactName.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(15), new EmojiFilter()});
        contactPhone.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        storeAddr.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30), new EmojiFilter()});
        authorizationDialog = new AuthorizationDialog(this);
        selectAddrModule = new SelectAddrModule(this, new Callback<String>() {
            @Override
            public void onSuccess(String[] s) {
                province_name = s[0];
                province = s[1];
                city_name = s[2];
                city = s[3];
                area_name = s[4];
                area = s[5];
                storeArea.setDes(province_name + city_name + area_name);
            }
        });

    }

    @Override
    protected StoreAddPresenter createPresenter() {
        return new StoreAddPresenter(this, this);
    }

    @OnClick({R.id.store_area, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.store_area:
                selectAddrModule.showPickerView(new Callback<String>() {
                    @Override
                    public void onSuccess(String[] s) {
                        province_name = s[0];
                        province = s[1];
                        city_name = s[2];
                        city = s[3];
                        area_name = s[4];
                        area = s[5];
                        storeArea.setDes(province_name + city_name + area_name);
                    }
                }, storeArea, (RelativeLayout) findViewById(R.id.rootview));
                break;
            case R.id.btn_submit:
                if (storeName.isEmpty()) {
                    ToastUtil.show("请输入门店名称");
                    return;
                }
                if (contactName.isEmpty()) {
                    ToastUtil.show("请输入联系人姓名");
                    return;
                }
                if (contactPhone.isEmpty()) {
                    ToastUtil.show("请输入联系人电话");
                    return;
                }
                if (province == null || province.equals("")) {
                    ToastUtil.show("请选择所属地区");
                    return;
                }
                if (storeAddr.getText().toString().trim().equals("")) {
                    ToastUtil.show("请输入详细地址");
                    return;
                }
                authorizationDialog.show(getString(R.string.store_add_authorization), merchant_phone, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verification_code = authorizationDialog.getVerificationCode();
                        if(verification_code == null || verification_code.equals("")){
                            ToastUtil.show("请输入验证码");
                            return;
                        }
                        mvpPresenter.storeAdd(merchant_id,contactPhone.getText(), storeName.getText(), contactName.getText(), verification_code, merchant_phone,
                                province, city, area, province_name, city_name, area_name, storeAddr.getText().toString().trim());
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mvpPresenter.sendCode(merchant_phone, merchant_id);
                    }
                });
                break;
        }
    }

    @Override
    public void sendCodeSuccess(String msg) {
        ToastUtil.show(msg);
        if(authorizationDialog != null){
            authorizationDialog.sendVerificationCodeSuccess();
        }
    }

    @Override
    public void storeAddSuccess(String msg) {
        authorizationDialog.dismiss();
        Bundle bundle = new Bundle();
        bundle.putString("content", "新增门店成功！");
        jumpActivity(AddSuccessActivity.class, bundle);
        finish();
    }
}
