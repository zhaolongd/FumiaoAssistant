package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessActivity extends BaseActivity {

    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

    }

    @OnClick(R.id.tv_complete)
    public void onViewClicked() {
        finish();
    }
}
