package com.fumiao.assistant.ui.activity.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import com.fumiao.core.uitls.L;
import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

public class ScanQrCodeActivity extends BaseActivity implements QRCodeView.Delegate {

    @BindView(R.id.zbarview)
    ZBarView zbarview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code, false);
        init();
    }

    private void init() {
        zbarview.setDelegate(this);
        zbarview.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        setTitle("绑定设备");
    }

    @Override
    protected void onResume() {
        zbarview.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
        super.onResume();
    }

    @Override
    protected void onStop() {
        //zbarview.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zbarview.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        zbarview.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
//        vibrate();
//        zbarview.startSpot(); // 延迟0.5秒后开始识别
        L.e("result:" + result);
        Intent intent = new Intent();
        intent.putExtra("qr_code", result);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        L.e("打开相机出错");
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
