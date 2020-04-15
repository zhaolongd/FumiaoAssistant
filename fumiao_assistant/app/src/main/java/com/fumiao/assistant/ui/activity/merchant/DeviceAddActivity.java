package com.fumiao.assistant.ui.activity.merchant;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.merchant.StoreDetailBean;
import com.fumiao.assistant.mvp.merchant.DeviceAddPresenter;
import com.fumiao.assistant.mvp.merchant.DeviceAddView;
import com.fumiao.core.uitls.AESUtils;
import com.fumiao.core.uitls.PermissionUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class DeviceAddActivity extends MvpActivity<DeviceAddPresenter> implements DeviceAddView {
    @BindView(R.id.tv_merchant_number)
    TextView tvMerchantNumber;
    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_device_name)
    EditText tvDeviceName;
    @BindView(R.id.btn_scan_activation)
    Button btnScanActivation;
    private int device_type = 1; //1: 收款音响 2：收款码牌
    private String store_id;
    private StoreDetailBean storeDetail;
    private int QR_CODE_REQUEST = 556;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_add);
        init();
    }

    private void init() {
        device_type = getIntent().getIntExtra("device_type", 1);
        store_id = getIntent().getStringExtra("store_id");
        storeDetail = (StoreDetailBean) getIntent().getSerializableExtra("store_detail");
        if (device_type == 1) {
            setTitle("激活收款音响");
        } else {
            setTitle("激活收款码牌");
        }
        if(storeDetail != null){
            tvMerchantNumber.setText(storeDetail.getMerchant_id());
            tvMerchantName.setText(storeDetail.getMerchant_name());
            tvStoreName.setText(storeDetail.getName());
        }
    }

    @Override
    protected DeviceAddPresenter createPresenter() {
        return new DeviceAddPresenter(this, this);
    }

    @OnClick(R.id.btn_scan_activation)
    public void onViewClicked() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        PermissionUtil.getInstance().check(permission, DeviceAddActivity.this, "相机", new PermissionUtil.Callback() {
                            @Override
                            public void onSucceed() {
                                Bundle bundle = new Bundle();
                                bundle.putInt("scan_status", 1);
                                jumpActivityForResult(ScanQrCodeActivity.class, bundle, QR_CODE_REQUEST);
                            }
                        });
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == QR_CODE_REQUEST) {
            //激活设备操作
            String resultData = data.getStringExtra("qr_code");
            if (!resultData.isEmpty()) {
                String qrCode = resultData.substring(resultData.lastIndexOf("/") + 1);
                JSONObject bindCashierObject = new JSONObject();
                try {
                    bindCashierObject.put("store_id", store_id);
                    bindCashierObject.put("serial_number", qrCode);
                    String remark = tvDeviceName.getText().toString().trim();
                    if(remark.equals("")){
                        remark = storeDetail.getName() + "收款设备";
                    }
                    bindCashierObject.put("remark", remark);
                    String jsonString = bindCashierObject.toString();
                    mvpPresenter.activationQrcode(AESUtils.encrypt(jsonString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void activationQrcodeSuccess() {
        Bundle bundle = new Bundle();
        bundle.putString("content", "激活成功！");
        jumpActivity(AddSuccessActivity.class, bundle);
        finish();
    }
}
