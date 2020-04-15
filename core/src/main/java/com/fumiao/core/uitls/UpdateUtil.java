package com.fumiao.core.uitls;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.fumiao.core.R;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.ui.UpdataDialog;

/**
 * Created by chee on 2018/9/21.
 */
public class UpdateUtil {

    private static String LAST_VERSIOIN = "last_versioin";//忽略版本

    private static UpdateUtil single;

    private UpdateUtil() {
    }

    public static UpdateUtil getSingle() {
        if (single == null) {
            single = new UpdateUtil();
        }
        return single;
    }

    public void checkVersion(final Context context,final String base_url, String url, String version, final boolean is_check, final Callback callback) {
        OkGo.<BaseResponse<UpdataBean>>get(url)
                .params("version", version)
                .params("int_platform", 1)
                .execute(new JsonCallback<BaseResponse<UpdataBean>>((Activity) context, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<UpdataBean>> response) {
                        UpdataBean updataBean = response.body().data;
                        if (updataBean.getLast_versioin().equals(SPUtil.getInstance().getString(LAST_VERSIOIN)) && !updataBean.isIs_force() && !is_check) {
                            callback.onSuccess("");
                        } else if (updataBean.isIs_last() || (updataBean.getLast_versioin().equals(SPUtil.getInstance().getString(LAST_VERSIOIN)) && !is_check)) {
                            callback.onSuccess("");
                        } else {
                            updata(context,base_url, updataBean, callback);
                        }
                    }
                });
    }


    private void updata(Context context,String base_url, final UpdataBean updataBean, final Callback callback) {
        AllenVersionChecker
                .getInstance()
                .downloadOnly(
                        UIData.create().
                                setDownloadUrl(base_url+updataBean.getPkg_url()).setContent(updataBean.getDescription())
                )
                .setCustomVersionDialogListener(new CustomVersionDialogListener() {
                    @Override
                    public Dialog getCustomVersionDialog(Context context, UIData versionBundle) {
                        final UpdataDialog updataDialog = new UpdataDialog(context);
                        TextView app_version = updataDialog.findViewById(R.id.app_version);
                        TextView content = updataDialog.findViewById(R.id.updata_content);
                        TextView dialog_cancel = updataDialog.findViewById(R.id.versionchecklib_version_dialog_cancel);
                        View line = updataDialog.findViewById(R.id.line);
                        app_version.setText("发现新版本" + updataBean.getLast_versioin());
                        dialog_cancel.setVisibility(updataBean.isIs_force() ? View.GONE : View.VISIBLE);
                        line.setVisibility(updataBean.isIs_force() ? View.GONE : View.VISIBLE);
                        content.setText(versionBundle.getContent());
                        dialog_cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_UP) {
                                    SPUtil.getInstance().putString(LAST_VERSIOIN, updataBean.getLast_versioin());
                                    callback.onSuccess("取消");
                                }
                                return false;
                            }
                        });
                        return updataDialog;
                    }
                })
                .setForceUpdateListener(new ForceUpdateListener() {
                    @Override
                    public void onShouldForceUpdate() {
                        if (updataBean.isIs_force()) {
                            AppManager.getAppManager().closeApp();
                        }
                    }
                })
                .executeMission(context);
    }

    class UpdataBean {

        /**
         * is_last : true
         * is_force : false
         * last_versioin : 1.1
         * description :
         * pkg_url :
         */

        private boolean is_last;
        private boolean is_force;
        private String last_versioin;
        private String description;
        private String pkg_url;

        public boolean isIs_last() {
            return is_last;
        }

        public void setIs_last(boolean is_last) {
            this.is_last = is_last;
        }

        public boolean isIs_force() {
            return is_force;
        }

        public void setIs_force(boolean is_force) {
            this.is_force = is_force;
        }

        public String getLast_versioin() {
            return last_versioin;
        }

        public void setLast_versioin(String last_versioin) {
            this.last_versioin = last_versioin;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPkg_url() {
            return pkg_url;
        }

        public void setPkg_url(String pkg_url) {
            this.pkg_url = pkg_url;
        }
    }

}
