package com.fumiao.core.uitls;

import android.Manifest;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.fumiao.core.R;

import io.reactivex.functions.Consumer;

/**
 * Created by chee on 2018/6/14.
 */

public class SelectImageUtils {

    private static SelectImageUtils insingle;

    public int requestCode;//响应码
    private boolean isAvatar;//是否为头像
    private static Activity mActivity;
    private PopupWindow popupWindow;

    private SelectImageUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public static SelectImageUtils getInsingle(Activity mActivity) {
        if (insingle == null) {
            insingle = new SelectImageUtils(mActivity);
        }else {
            SelectImageUtils.mActivity = mActivity;
        }
        return insingle;
    }

    public void clear() {
        insingle = null;
    }

    public void showWindow(View v, int requestCode) {
        showWindow(v, requestCode, false);
    }

    public void showWindow(View v, int requestCode, boolean isAvatar) {
        this.isAvatar = isAvatar;
        this.requestCode = requestCode;
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        View view = LayoutInflater.from(mActivity).inflate(R.layout.popup_select_image, null);
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopupWindow);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });
        setOnPopupViewClick(view);
        setBackgroundAlpha(0.5f);
    }

    private void setOnPopupViewClick(View view) {
        TextView tvPickPhoto, tvPhotograph, tvCancel;
        tvPickPhoto = (TextView) view.findViewById(R.id.tv_pick_photo);
        tvPhotograph = (TextView) view.findViewById(R.id.tv_photograph);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvPickPhoto.setOnClickListener(listener);
        tvPhotograph.setOnClickListener(listener);
        tvCancel.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            popupWindow.dismiss();
            if (view.getId() == R.id.tv_pick_photo) {
                pickPhoto();
            } else if (view.getId() == R.id.tv_photograph) {
                photograph();
            } else if (view.getId() == R.id.tv_cancel) {
            }

        }
    };

    //设置屏幕背景透明效果
    private  void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = alpha;
        mActivity.getWindow().setAttributes(lp);
    }

    private void photograph() {
        RxPermissions rxPermissions = new RxPermissions(mActivity);
        rxPermissions.requestEach( Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        PermissionUtil.getInstance().check(permission, mActivity, "存储", new PermissionUtil.Callback() {
                            @Override
                            public void onSucceed() {
                                PictureSelector.create(mActivity)
                                        .openCamera(PictureMimeType.ofImage())
                                        .enableCrop(true)
                                        .withAspectRatio(1, 1)
                                        .maxSelectNum(1)
                                        .enableCrop(isAvatar)// 是否裁剪 true or false
//                                        .circleDimmedLayer(isAvatar)
                                        .imageFormat(PictureMimeType.PNG)
                                        .compress(true)// 是否压缩 true or false
                                        .showCropFrame(false)
                                        .showCropGrid(false)
                                        .rotateEnabled(isAvatar) // 裁剪是否可旋转图片 true or false
                                        .scaleEnabled(isAvatar)// 裁剪是否可放大缩小图片 true or false
                                        .forResult(requestCode);
                            }
                        });
                    }
                });
    }

    private void pickPhoto() {
        RxPermissions rxPermissions = new RxPermissions(mActivity);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        PermissionUtil.getInstance().check(permission, mActivity, "存储", new PermissionUtil.Callback() {
                            @Override
                            public void onSucceed() {
                                PictureSelector.create(mActivity)
                                        .openGallery(PictureMimeType.ofImage())
                                        .enableCrop(true)
                                        .withAspectRatio(1, 1)
                                        .maxSelectNum(1)
                                        .imageFormat(PictureMimeType.PNG)
                                        .enableCrop(isAvatar)// 是否裁剪 true or false
//                                        .circleDimmedLayer(isAvatar)
                                        .compress(true)// 是否压缩 true or false
                                        .showCropFrame(false)
                                        .showCropGrid(false)
                                        .rotateEnabled(isAvatar) // 裁剪是否可旋转图片 true or false
                                        .scaleEnabled(isAvatar)// 裁剪是否可放大缩小图片 true or false
                                        .forResult(requestCode);
                            }
                        });
                    }
                });
    }

}
