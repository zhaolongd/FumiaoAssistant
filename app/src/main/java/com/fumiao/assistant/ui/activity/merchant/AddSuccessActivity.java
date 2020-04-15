package com.fumiao.assistant.ui.activity.merchant;

import android.os.Bundle;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class AddSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_success);
        init();
    }

    private void init(){
        content = getIntent().getStringExtra("content");
        tvContent.setText(content);
    }

    @OnClick(R.id.btn_complete)
    public void onViewClicked() {
        finish();
    }
}
