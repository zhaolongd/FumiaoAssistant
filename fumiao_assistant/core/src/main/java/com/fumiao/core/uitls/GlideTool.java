package com.fumiao.core.uitls;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fumiao.core.R;

/**
 * Created by chee on 2018/9/21.
 */
public class GlideTool {

    public static void setImageResource(ImageView img, String url) {
        RequestOptions requestOptions = RequestOptions.errorOf(R.drawable.ic_img);
        requestOptions.placeholder(R.drawable.ic_img);
        Glide.with(img.getContext()).load(url).apply(requestOptions).into(img);
    }

    public static void setImageResource(ImageView img, String url,int def) {
        RequestOptions requestOptions = RequestOptions.errorOf(def);
        requestOptions.placeholder(def);
        Glide.with(img.getContext()).load(url).apply(requestOptions).into(img);
    }

}
