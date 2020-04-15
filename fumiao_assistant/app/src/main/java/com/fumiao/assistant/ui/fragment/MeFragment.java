package com.fumiao.assistant.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpFragment;
import com.fumiao.assistant.event.LoginEvent;
import com.fumiao.assistant.mvp.me.MePresenter;
import com.fumiao.assistant.mvp.me.MeView;
import com.fumiao.assistant.ui.activity.LoginActivity;
import com.fumiao.assistant.ui.activity.me.AboutActivity;
import com.fumiao.assistant.ui.activity.me.ChangePasswordActivity;
import com.fumiao.assistant.ui.activity.me.DownloadActivity;
import com.fumiao.assistant.ui.activity.me.PersonMessageActivity;
import com.fumiao.assistant.ui.dialog.MsgDialog;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUtil;
import com.fumiao.core.widget.CircleImageView;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhaolong on 2019/9/11.
 */
public class MeFragment extends MvpFragment<MePresenter> implements MeView {
    Unbinder unbinder;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    MsgDialog msgDialog;

    @Override
    protected MePresenter createPresenter() {
        return new MePresenter(this, getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_me, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        String user_phone = SPUtil.getInstance().getString(USER_PHONE);
        tvName.setText(user_phone);
        msgDialog = new MsgDialog(getActivity());
    }

    public void refreshView() {
        String user_phone = SPUtil.getInstance().getString(USER_PHONE);
        tvName.setText(user_phone);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_personal_center, R.id.btn_modify_psd, R.id.btn_logout, R.id.btn_download, R.id.btn_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_personal_center:
                jumpActivity(PersonMessageActivity.class);
                break;
            case R.id.btn_modify_psd:
                Bundle bundle = new Bundle();
                bundle.putString("phone", SPUtil.getInstance().getString(USER_PHONE));
                jumpActivity(ChangePasswordActivity.class, bundle);
                break;
            case R.id.btn_logout:
                msgDialog.show(getString(R.string.login_out), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mvpPresenter.loginOut();
                        msgDialog.dismiss();
                    }
                });
                break;
            case R.id.btn_download:
                jumpActivity(DownloadActivity.class);
                break;
            case R.id.btn_about:
                jumpActivity(AboutActivity.class);
                break;
        }
    }

    @Override
    public void loginSuccess(String msg) {
        SPUtil.getInstance().putBoolean(IS_LOGIN, false);
        SPUtil.getInstance().putString(USER_PHONE, "");
        EventBus.getDefault().post(new LoginEvent(LOGIN_EXIT));
        jumpActivity(LoginActivity.class);
        ToastUtil.show("退出成功");
    }
}
