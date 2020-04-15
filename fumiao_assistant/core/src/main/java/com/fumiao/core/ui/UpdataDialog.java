package com.fumiao.core.ui;

import android.content.Context;
import android.view.View;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.fumiao.core.R;

/**
 * Created by chee on 2018/9/21.
 */
public class UpdataDialog extends CoreDialog {

    public UpdataDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_updata);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
