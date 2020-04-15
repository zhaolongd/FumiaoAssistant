package com.fumiao.assistant.ui.activity.me;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import com.fumiao.core.uitls.AppUtils;
import com.tencent.bugly.beta.Beta;
import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.btn_check_version)
    Button btnCheckVersion;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }

    private void init() {
        setTitle(getString(R.string.about_fumiao));
        tvVersion.setText(getString(R.string.current_version) + AppUtils.getVersionName(this));
    }

    @OnClick(R.id.btn_check_version)
    public void onViewClicked() {
        Beta.checkUpgrade(true,false);
    }
}
