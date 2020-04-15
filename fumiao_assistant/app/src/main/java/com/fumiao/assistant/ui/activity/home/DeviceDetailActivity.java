package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.DeviceDetailBean;
import com.fumiao.assistant.mvp.home.DeviceDetailPresenter;
import com.fumiao.assistant.mvp.home.DeviceDetailView;
import com.fumiao.assistant.ui.dialog.AuthorizationDialog;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.widget.KeyEditText;
import butterknife.BindView;
import butterknife.OnClick;

public class DeviceDetailActivity extends MvpActivity<DeviceDetailPresenter> implements DeviceDetailView {

    @BindView(R.id.device_name)
    KeyEditText deviceName;
    @BindView(R.id.device_sn)
    KeyEditText deviceSn;
    @BindView(R.id.device_type)
    KeyEditText deviceType;
    @BindView(R.id.device_store)
    KeyEditText deviceStore;
    @BindView(R.id.activation_time)
    KeyEditText activationTime;
    @BindView(R.id.device_status)
    KeyEditText deviceStatus;
    @BindView(R.id.btn_unbind)
    Button btnUnbind;
    private String sn; //设备sn号
    private String serial_number;
    private String verification_code; //验证码
    private String store_id;
    AuthorizationDialog authorizationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        init();
    }

    private void init() {
        setTitle("终端详情");
        sn = getIntent().getStringExtra("sn");
        mvpPresenter.getDeviceDetail(sn);
        authorizationDialog = new AuthorizationDialog(this);
        deviceName.getEditText().setEnabled(false);
        deviceSn.getEditText().setEnabled(false);
        deviceType.getEditText().setEnabled(false);
        deviceStore.getEditText().setEnabled(false);
        activationTime.getEditText().setEnabled(false);
        deviceStatus.getEditText().setEnabled(false);
    }

    @Override
    protected DeviceDetailPresenter createPresenter() {
        return new DeviceDetailPresenter(this, this);
    }

    @OnClick(R.id.btn_unbind)
    public void onViewClicked() {
        authorizationDialog.show(getString(R.string.device_unbind_authorization), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification_code = authorizationDialog.getVerificationCode();
                if(verification_code == null || verification_code.equals("")){
                    ToastUtil.show("请输入验证码");
                    return;
                }
                mvpPresenter.unbindDevice(store_id, serial_number, verification_code);
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.sendRelieveCode();
            }
        });
    }

    @Override
    public void sendRelieveCodeSuccess(String msg) {
        ToastUtil.show(msg);
        if(authorizationDialog != null){
            authorizationDialog.sendVerificationCodeSuccess();
        }
    }

    @Override
    public void showDeviceDetail(DeviceDetailBean data) {
        if(data != null){
            store_id = data.getDevice_info().getStore_id();
            serial_number = data.getDevice_info().getUnion_qrcode_number();
            deviceName.setText(data.getDevice_info().getStore_name());
            deviceSn.setText(data.getDevice_info().getSn());
            deviceType.setText(data.getDevice_info().getType_name());
            deviceStore.setText(data.getDevice_info().getStore_name());
            activationTime.setText(data.getDevice_info().getBind_time());
            deviceStatus.setText(data.getDevice_info().getStatus_name());
        }
    }

    @Override
    public void unbindDeviceSuccess(String msg) {
        authorizationDialog.dismiss();
        jumpActivity(SuccessActivity.class);
        finish();
    }
}
