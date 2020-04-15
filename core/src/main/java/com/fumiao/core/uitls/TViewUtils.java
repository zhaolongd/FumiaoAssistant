package com.fumiao.core.uitls;

import android.widget.TextView;

/**
 * Created by chee on 2018/9/7.
 */
public class TViewUtils {

    public static String getString(TextView textView){
        return textView.getText().toString().trim();
    }

    public static boolean isEmpty(TextView textView){
        return "".equals(getString(textView));
    }

}
