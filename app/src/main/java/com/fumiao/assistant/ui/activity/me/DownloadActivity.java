package com.fumiao.assistant.ui.activity.me;

import android.os.Bundle;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;

/**
 * Author: XieBoss
 * Date: 2019/9/27 0027 14:36
 *
 * @Description:
 */
public class DownloadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        init();
    }

    private void init() {
        setTitle("下载福喵APP");
    }


}
