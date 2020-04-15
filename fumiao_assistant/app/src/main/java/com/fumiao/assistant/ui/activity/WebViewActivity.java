package com.fumiao.assistant.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.BaseActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.PermissionInterceptor;
import com.just.agentweb.WebChromeClient;
import butterknife.BindView;

/**
 * Author: XieBoss
 * Date: 2019/8/27 0027 11:12
 *
 * @Description:
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_content)
    LinearLayout webContent;
    protected AgentWeb mAgentWeb;
    public static final String WEB_TITLE = "WEB_TITLE";
    public static final String WEB_URL = "WEB_URL";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web, false);
        init();
    }

    private void init() {
        String title = getIntent().getStringExtra(WEB_TITLE);
        String url = getIntent().getStringExtra(WEB_URL);
        setTitle(title);
        mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent(webContent,new LinearLayout.LayoutParams(-1,-1))
                    .useDefaultIndicator()  // 使用默认进度条
                    .setWebChromeClient(mWebChromeClient)
                    .setPermissionInterceptor(mPermissionInterceptor)
                    .createAgentWeb()
                    .ready()
                    .go(url);
    }

    private com.just.agentweb.WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    };

    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {
        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
