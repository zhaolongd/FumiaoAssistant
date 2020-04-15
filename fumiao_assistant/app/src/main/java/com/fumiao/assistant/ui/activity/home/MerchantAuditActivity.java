package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.mvp.home.MerchantsauditPresenter;
import com.fumiao.assistant.mvp.home.MerchantsauditView;
import com.fumiao.assistant.ui.fragment.MerchantsAuditFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: XieBoss
 * Date: 2019/9/16 0016 16:31
 *
 * @Description:
 */
public class MerchantAuditActivity extends MvpActivity<MerchantsauditPresenter> implements MerchantsauditView {

    @BindView(R.id.all_status)
    TextView allStatus;
    @BindView(R.id.review_status)
    TextView reviewStatus;
    @BindView(R.id.reject_status)
    TextView rejectStatus;
    @BindView(R.id.bill_content)
    FrameLayout billContent;
    @BindView(R.id.edit_audit_query)
    EditText editAuditQuery;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MerchantsAuditFragment mStatusFragment; //全部
    private MerchantsAuditFragment mReviewFragment; //审核中
    private MerchantsAuditFragment mRejectedFragment; //驳回


    @Override
    protected MerchantsauditPresenter createPresenter() {
        return new MerchantsauditPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_management, false);
        setTag(MERCHANT_AUDIT_PAGE);
        init();
    }

    private void init() {
        setTitle("商户审核管理");
        fragmentManager = getSupportFragmentManager();
        setMyTabHost(1);
    }

    private void setMyTabHost(int index) {
        transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        clearText();
        switch (index) {
            case 1:
                allStatus.setTextColor(getResources().getColor(R.color.text_press_color));
                if (mStatusFragment == null) {
                    mStatusFragment = new MerchantsAuditFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);
                    mStatusFragment.setArguments(bundle);
                    searchData(mStatusFragment);
                    transaction.add(R.id.bill_content, mStatusFragment);
                } else {
                    transaction.show(mStatusFragment);
                }
                break;
            case 2:
                reviewStatus.setTextColor(getResources().getColor(R.color.text_press_color));
                if (mReviewFragment == null) {
                    mReviewFragment = new MerchantsAuditFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    mReviewFragment.setArguments(bundle);
                    searchData(mReviewFragment);
                    transaction.add(R.id.bill_content, mReviewFragment);
                } else {
                    transaction.show(mReviewFragment);
                }
                break;
            case 3:
                rejectStatus.setTextColor(getResources().getColor(R.color.text_press_color));
                if (mRejectedFragment == null) {
                    mRejectedFragment = new MerchantsAuditFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 2);
                    mRejectedFragment.setArguments(bundle);
                    searchData(mRejectedFragment);
                    transaction.add(R.id.bill_content, mRejectedFragment);
                } else {
                    transaction.show(mRejectedFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 商户名查询
     *
     */
    public void searchData(MerchantsAuditFragment fragment){
        editAuditQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragment.update(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mStatusFragment != null) {
            transaction.hide(mStatusFragment);
        }
        if (mReviewFragment != null) {
            transaction.hide(mReviewFragment);
        }
        if (mRejectedFragment != null) {
            transaction.hide(mRejectedFragment);
        }
    }

    private void clearText() {
        allStatus.setTextColor(getResources().getColor(R.color.text_color));
        rejectStatus.setTextColor(getResources().getColor(R.color.text_color));
        reviewStatus.setTextColor(getResources().getColor(R.color.text_color));
    }

    @OnClick({R.id.all_status, R.id.review_status, R.id.reject_status,R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_status:
                setMyTabHost(1);
                break;
            case R.id.review_status:
                setMyTabHost(2);
                break;
            case R.id.reject_status:
                setMyTabHost(3);
                break;
            case R.id.tv_cancel:
                editAuditQuery.setText("");
                break;
        }
    }


}
