package com.fumiao.core.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.fumiao.core.R;

/**
 * Created by chee on 2018/8/29.
 */
public class CoreDialog extends Dialog {
    public CoreDialog(Context context) {
        super(context, R.style.dialog);
    }

    public void setLayoutSize(int width, int height) {
//              setLayoutSize(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(this.getWindow().getAttributes());
//        lp.width = width;
//        lp.height = height;
//        this.getWindow().setAttributes(lp);
    }


    public void bottomPopUp(){

    }
}
