package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhaolong on 2019/9/12.
 */

public class MerchantInComingActivity extends BaseActivity {

    @BindView(R.id.btn_normal_merchant_incoming)
    LinearLayout btnNormalMerchantIncoming;
    @BindView(R.id.btn_micro_merchant_incoming)
    LinearLayout btnMicroMerchantIncoming;
    @BindView(R.id.btn_incoming_draft)
    LinearLayout btnIncomingDraft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_incoming);
        setTag(MERCHANT_INCOMING_PAGE);
        init();
    }

    private void init() {
        setTitle(R.string.collect_merchant_data);
    }

    @OnClick({R.id.btn_normal_merchant_incoming, R.id.btn_micro_merchant_incoming, R.id.btn_incoming_draft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_normal_merchant_incoming:
                Bundle bundle = new Bundle();
                bundle.putBoolean("normal_merchant_incoming", true);
                jumpActivity(MerchantRegisterActivity.class, bundle);
                break;
            case R.id.btn_micro_merchant_incoming:
                jumpActivity(MerchantRegisterActivity.class);
                break;
            case R.id.btn_incoming_draft:
                jumpActivity(InComingDraftActivity.class);
                break;
        }
    }
}
