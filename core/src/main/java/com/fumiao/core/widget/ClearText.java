package com.fumiao.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fumiao.core.R;

/**
 * Created by chee on 2018/5/12.
 * 自定义输入框
 */
public class ClearText extends LinearLayout {

    private TextView keyText;
    private EditText textEdit;
    private ViewGroup root;
    private ImageView clear;
    private View line;

    public ClearText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ClearText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearText);

        String text = typedArray.getString(R.styleable.ClearText_text);
        String hint = typedArray.getString(R.styleable.ClearText_clear_hint);
        String key = typedArray.getString(R.styleable.ClearText_clear_key);
        int line_visibility = typedArray.getInt(R.styleable.ClearText_clear_line_visibility, 0);
        boolean is_password = typedArray.getBoolean(R.styleable.ClearText_clear_is_password, false);//是否為密碼
        boolean ed_focusable = typedArray.getBoolean(R.styleable.ClearText_clear_ed_focusable, true);
        final boolean is_clear = typedArray.getBoolean(R.styleable.ClearText_is_clear, false); //是否显示清除按钮
        boolean is_number = typedArray.getBoolean(R.styleable.ClearText_clear_is_number, false);
        float key_width = typedArray.getDimension(R.styleable.ClearText_clear_key_width, 0);


        root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.widget_clear, null);
        keyText = root.findViewById(R.id.oil_edit_key);
        textEdit = root.findViewById(R.id.clear_edit_text);
        line = root.findViewById(R.id.line_id);
        textEdit.setFocusable(ed_focusable);
        clear = root.findViewById(R.id.clear);
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("num","num");
                clear.setVisibility(View.GONE);
                textEdit.setText("");
            }
        });

        if (is_password) {
            textEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if (is_number) {
            textEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
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

        textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(is_clear){
                    if(isEmpty()){
                        clear.setVisibility(View.GONE);
                    }else {
                        clear.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
