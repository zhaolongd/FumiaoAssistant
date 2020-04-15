package com.fumiao.assistant.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.event.LoginEvent;
import com.fumiao.assistant.mvp.main.MainPresenter;
import com.fumiao.assistant.mvp.main.MainView;
import com.fumiao.assistant.ui.fragment.HomeFragment;
import com.fumiao.assistant.ui.fragment.MeFragment;
import com.fumiao.assistant.ui.fragment.MerchantFragment;
import com.fumiao.assistant.ui.fragment.TransactionsFragment;
import com.fumiao.core.uitls.AppManager;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.StatusBarUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {


    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.btn_home)
    LinearLayout btnHome;
    @BindView(R.id.btn_merchant)
    LinearLayout btnMerchant;
    @BindView(R.id.btn_data)
    LinearLayout btnData;
    @BindView(R.id.btn_me)
    LinearLayout btnMe;
    FragmentManager fm = getSupportFragmentManager();
    HomeFragment homeFragment;
    MerchantFragment merchantFragment;
    TransactionsFragment transactionsFragment;
    MeFragment meFragment;


    private boolean login;
    private boolean isReset = false; //菜单栏选项到首页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, false);
        setTag(MAIN_PAGE);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isReset) {
            select(0);
            isReset = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        EventBus.getDefault().register(this);
        StatusBarCompat.translucentStatusBar(this, true);
        login = SPUtil.getInstance().getBoolean(IS_LOGIN);
        if (!login) {
            jumpActivity(LoginActivity.class);
        }
        select(0);
    }

    public void select(int p) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (merchantFragment != null) {
            transaction.hide(merchantFragment);
        }
        if (transactionsFragment != null) {
            transaction.hide(transactionsFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
        btnReset();
        switch (p) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_content, homeFragment).show(homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                changeBtnState(btnHome, true);
                StatusBarUtils.changeToLightStatusBar(this);
                break;
            case 1:
                if (merchantFragment == null) {
                    merchantFragment = new MerchantFragment();
                    transaction.add(R.id.main_content, merchantFragment).show(merchantFragment);
                } else {
                    transaction.show(merchantFragment);
                }
                changeBtnState(btnMerchant, true);
                StatusBarUtils.changeToLightStatusBar(this);
                break;
            case 2:
                if (transactionsFragment == null) {
                    transactionsFragment = new TransactionsFragment();
                    transaction.add(R.id.main_content, transactionsFragment).show(transactionsFragment);
                } else {
                    transaction.show(transactionsFragment);
                }
                changeBtnState(btnData, true);
                StatusBarUtils.changeToLightStatusBar(this);
                break;
            case 3:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.main_content, meFragment).show(meFragment);
                } else {
                    transaction.show(meFragment);
                }
                changeBtnState(btnMe, true);
                StatusBarUtils.cancelLightStatusBar(this);
                break;
        }
        transaction.commit();
    }

    private void btnReset() {
        changeBtnState(btnHome, false);
        changeBtnState(btnMerchant, false);
        changeBtnState(btnData, false);
        changeBtnState(btnMe, false);
    }

    private void changeBtnState(LinearLayout linearLayout, boolean state) {
        ImageView imageView = (ImageView) linearLayout.getChildAt(0);
        TextView textView = (TextView) linearLayout.getChildAt(1);
        imageView.setSelected(state);
        textView.setTextColor(getResources().getColor(state ? R.color.colorAccent : R.color.font_des));
    }

    public void onMenuClicks(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                select(0);
                break;
            case R.id.btn_merchant:
                select(1);
                break;
            case R.id.btn_data:
                select(2);
                break;
            case R.id.btn_me:
                select(3);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState); //阻止activity保存fragment的状态
    }

    /**
     * 重新让新的Fragment指向了原本未被销毁的fragment
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (homeFragment == null && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        }

        if (merchantFragment == null && fragment instanceof MerchantFragment) {
            merchantFragment = (MerchantFragment) fragment;
        }

        if (transactionsFragment == null && fragment instanceof TransactionsFragment) {
            transactionsFragment = (TransactionsFragment) fragment;
        }

        if (meFragment == null && fragment instanceof MeFragment) {
            meFragment = (MeFragment) fragment;
        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), R.string.quit_app_tips,
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
//                ActivityStackManager.getInstance().finishAllActivities();
                AppManager.getAppManager().closeApp();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LoginEvent event) {
        if (event.getMsg() == LOGIN_SUCCESS) {
            if (meFragment != null) {
                meFragment.refreshView();
            }
        } else if (event.getMsg() == LOGIN_EXIT) {
            isReset = true;
        }
    }


}
