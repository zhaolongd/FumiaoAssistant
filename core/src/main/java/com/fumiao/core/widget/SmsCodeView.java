package com.fumiao.core.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fumiao.core.R;


/**
 * Created by chee on 2018/5/14.
 * 短信验证码控件
 */
@SuppressLint("AppCompatCustomView")
public class SmsCodeView extends TextView {

    private String get_code = "获取验证码";

    public SmsCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SmsCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmsCodeView);
        int background = typedArray.getResourceId(R.styleable.SmsCodeView_background, 0);
        if (background != 0) {
            this.setBackgroundResource(background);
        }

        this.setPadding(10, 10, 10, 10);
        final ViewGroup.LayoutParams VG_LP_MW
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(VG_LP_MW);
        this.setText(get_code);
        this.setGravity(Gravity.CENTER);
    }

    public void start() {
        this.setEnabled(false);
        timer.start();
    }

    public void finish(){
        if(timer != null){
            timer.cancel();
            timer.onFinish();
        }
    }

    public CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            setText("已发送(" + millisUntilFinished / 1000 + ")");
        }
        @Override
        public void onFinish() {
            setEnabled(true);
            setText(get_code);
        }
    };

}
