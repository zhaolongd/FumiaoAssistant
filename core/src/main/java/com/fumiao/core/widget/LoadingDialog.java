package com.fumiao.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.fumiao.core.R;
import com.wang.avi.AVLoadingIndicatorView;


public class LoadingDialog extends Dialog {

    public LoadingDialog( Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        AVLoadingIndicatorView loadView = findViewById(R.id.loadView);
        loadView.show();
    }

}
