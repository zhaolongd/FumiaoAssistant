package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import com.fumiao.core.uitls.AppManager;
import butterknife.OnClick;

public class InComingResultActivity extends BaseActivity {

    private boolean isRejectInComing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_result);
        init();
    }

    private void init() {
        setTitle("商户进件");
        isRejectInComing = getIntent().getBooleanExtra("reject_incoming", false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRejectInComing) {
            AppManager.getAppManager().backToTag(MERCHANT_AUDIT_PAGE);
        }else {
            AppManager.getAppManager().backToTag(MERCHANT_INCOMING_PAGE);
        }
    }

    @OnClick(R.id.tv_call_tel)
    public void onViewClicked() {
        Intent Intent =  new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:0791-82327999"));//跳转到拨号界面，同时传递电话号码
        startActivity(Intent);
    }
}
