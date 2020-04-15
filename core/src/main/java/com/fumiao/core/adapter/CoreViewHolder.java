package com.fumiao.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fumiao.core.R;


/**
 * Created by yunshi on 2017/11/20.
 */

public class CoreViewHolder extends RecyclerView.ViewHolder {
    View convertView;
    Context context;
    //空view的标志位
    private boolean isEmptyView = false;

    public CoreViewHolder(View itemView, Context context) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
    }


    public CoreViewHolder(View itemView, Context context, boolean isEmptyView) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
        this.isEmptyView = isEmptyView;
    }

    public boolean isEmptyView() {
        return isEmptyView;
    }

    public View getConvertView() {
        return convertView;
    }

    public void setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
    }

    public void setText(int id, Spanned text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
    }

    public void setBg(int res, int bg_res) {
        ViewGroup viewGroup = convertView.findViewById(res);
        viewGroup.setBackgroundResource(bg_res);
    }

    public void setTextBg(int id, int bg_res){
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setBackgroundResource(bg_res);
    }

    public void setTextColor(int id, int color) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setTextColor(context.getResources().getColor(color));
    }

    public void setImageResource(int id, int resouceId) {
        ImageView img = (ImageView) convertView.findViewById(id);
        img.setImageResource(resouceId);
    }

    public void setImageResource(int id, String url) {
        RequestOptions requestOptions = RequestOptions.errorOf(R.drawable.ic_img);
        requestOptions.placeholder(R.drawable.ic_img);
        ImageView img = (ImageView) convertView.findViewById(id);
        Glide.with(context).load(url).apply(requestOptions).into(img);
    }

    public void setImageResource(int id, String url, int def) {
        RequestOptions requestOptions = RequestOptions.errorOf(def);
        requestOptions.placeholder(def);
        ImageView img = (ImageView) convertView.findViewById(id);
        Glide.with(context).load(url).apply(requestOptions).into(img);
    }

    public void setCImageResource(int id, String url) {
        ImageView img = (ImageView) convertView.findViewById(id);
//        ImageLoader.circleImage(context,url,img);
    }

    public void setBImageResource(int id, String url) {
        ImageView img = (ImageView) convertView.findViewById(id);
//        ImageLoader.bankImage(context,url,img);
    }

    public void setOnClickListener(int id, View.OnClickListener listener) {
        convertView.findViewById(id).setOnClickListener(listener);
    }

    public void setChecked(int id, boolean check) {
        CheckBox checkBox = (CheckBox) convertView.findViewById(id);
        checkBox.setChecked(check);
    }

    public void setVisibility(int id, int visibility) {
        View view = convertView.findViewById(id);
        view.setVisibility(visibility);
    }

    public void setAlpha(int id, float alpha){
        View view = convertView.findViewById(id);
        view.setAlpha(alpha);
    }
}
