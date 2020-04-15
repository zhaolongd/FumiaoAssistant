package com.fumiao.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fumiao.core.R;


/**
 * Created by chee on 2018/5/28.
 * 图标-文字-描述-箭头
 */
public class ItemArrowLayout extends LinearLayout {

    View root;
    TextView keyView, desView;
    ImageView iconView, arrowView;
    View icon_m;

    public ItemArrowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemArrowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        root = LayoutInflater.from(context).inflate(R.layout.widget_arrow_layout, null);
        keyView = root.findViewById(R.id.arrow_layout_key);
        desView = root.findViewById(R.id.arrow_layout_des);
        iconView = root.findViewById(R.id.arrow_layout_icon);
        arrowView = root.findViewById(R.id.arrow_layout_arrow);
        icon_m = root.findViewById(R.id.icon_m);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArrowLayout);
        String text = typedArray.getString(R.styleable.ArrowLayout_text);
        int icon = typedArray.getResourceId(R.styleable.ArrowLayout_icon, 0);
        float icon_width = typedArray.getDimension(R.styleable.ArrowLayout_icon_wide, 0);
        float icon_height = typedArray.getDimension(R.styleable.ArrowLayout_icon_high, 0);
        String des = typedArray.getString(R.styleable.ArrowLayout_des);
        boolean arrow_visibility = typedArray.getBoolean(R.styleable.ArrowLayout_arrow_visibility, true);
        int des_color = typedArray.getResourceId(R.styleable.ArrowLayout_des_color, 0);
        int key_color = typedArray.getResourceId(R.styleable.ArrowLayout_key_color, 0);

        if (icon_height != 0 || icon_width != 0) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams((int) icon_width, (int) icon_height);
            iconView.setLayoutParams(layoutParams);
        }

        if (des_color != 0) {
            desView.setTextColor(context.getResources().getColor(des_color));
        }
        if(key_color != 0){
            keyView.setTextColor(context.getResources().getColor(key_color));
        }

        keyView.setText(text);
        desView.setText(des);
        if (icon != 0) {
            iconView.setImageResource(icon);
        } else {
            icon_m.setVisibility(GONE);
            iconView.setVisibility(GONE);
        }

        arrowView.setVisibility(arrow_visibility ? VISIBLE : GONE);

        final ViewGroup.LayoutParams VG_LP_MW
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(root, VG_LP_MW);
    }

    public void setKey(String key) {
        keyView.setText(key);
    }
    public void setKeys(String key) {
        desView.setText(key);
    }



    public void setDes(String des) {
        desView.setText(des);
    }

    public void setDes(Spanned des) {
        desView.setText(des);
    }

    public String getDes() {
        return desView.getText().toString().trim();
    }


}
