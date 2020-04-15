package com.fumiao.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fumiao.core.R;

/**
 * Created by chee on 2018/5/12.
 * 自定义输入框
 */
public class KeyEditText extends LinearLayout {

    private TextView keyText;
    private EditText textEdit;
    private ViewGroup root;
    private View line;

    public KeyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public KeyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OilEditText);

        String text = typedArray.getString(R.styleable.OilEditText_text);
        String hint = typedArray.getString(R.styleable.OilEditText_hint);
        String key = typedArray.getString(R.styleable.OilEditText_key);
        int line_visibility = typedArray.getInt(R.styleable.OilEditText_line_visibility, 0);
        boolean is_password = typedArray.getBoolean(R.styleable.OilEditText_is_password, false);//是否為密碼
        boolean is_show =  typedArray.getBoolean(R.styleable.OilEditText_is_show, false);//是否显示密碼
        boolean ed_focusable = typedArray.getBoolean(R.styleable.OilEditText_ed_focusable, true);
        boolean is_number = typedArray.getBoolean(R.styleable.OilEditText_is_number, false);
        boolean is_decimal_number = typedArray.getBoolean(R.styleable.OilEditText_is_decimal_number, false);
        float key_width = typedArray.getDimension(R.styleable.OilEditText_key_width, 0);


        root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.widget_oiledit, null);
        keyText = root.findViewById(R.id.oil_edit_key);
        textEdit = root.findViewById(R.id.oil_edit_text);
        line = root.findViewById(R.id.line_id);
        textEdit.setFocusable(ed_focusable);

        if (is_password) {
            textEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if(is_show){
            textEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        if (is_number) {
            textEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        /**
         * 可以带小数点的浮点格式
         */
        if(is_decimal_number){
            textEdit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        }

        if (key_width != 0) {
            ViewGroup.LayoutParams layoutParams = keyText.getLayoutParams();
            layoutParams.width = (int) key_width;
            keyText.setLayoutParams(layoutParams);
        }

        keyText.setText(key);
        textEdit.setHint(hint);
        textEdit.setText(text);
        line.setVisibility(line_visibility == 1 ? VISIBLE : GONE);

        final ViewGroup.LayoutParams VG_LP_MW
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addView(root, VG_LP_MW);
    }

    public void setText(String text) {
        textEdit.setText(text);
    }

    public String getText() {
        return textEdit.getText().toString().trim();
    }

    public EditText getEditText() {
        return textEdit;
    }

    public void setKey(String key) {
        keyText.setText(key);
    }

    public void setHint(String hint) {
        textEdit.setHint(hint);
    }

    public String getHint() {
        return textEdit.getHint().toString();
    }

    public void addTextChangedListener(TextWatcher watcher) {
        textEdit.addTextChangedListener(watcher);
    }

    public static class OilEditTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public boolean isEmpty() {
        return textEdit.getText().toString().trim().equals("");
    }

}
