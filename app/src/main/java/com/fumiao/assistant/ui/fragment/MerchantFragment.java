package com.fumiao.assistant.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpFragment;
import com.fumiao.assistant.mvp.merchant.MerchantPresenter;
import com.fumiao.assistant.mvp.merchant.MerchantView;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MerchantFragment extends MvpFragment<MerchantPresenter> implements MerchantView {
    Unbinder unbinder;
    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.merchant_list_line)
    TextView merchantListLine;
    @BindView(R.id.merchant_list)
    RelativeLayout merchantList;
    @BindView(R.id.store_list_line)
    TextView storeListLine;
    @BindView(R.id.store_list)
    RelativeLayout storeList;
    MerchantListFragment merchantListFragment;
    StoreListFragment storeListFragment;
    @BindView(R.id.iv_search_clear)
    ImageView ivSearchClear;
    @BindView(R.id.content)
    FrameLayout content;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int tabIndex = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_merchant, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        fragmentManager = getChildFragmentManager();
        setMyTabHost(tabIndex);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edtSearch.getText().toString().trim().isEmpty()){
                    ivSearchClear.setVisibility(View.GONE);
                }else {
                    ivSearchClear.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSystemSofeKeyboard();
                    updateSearch();
                    return true;
                }
                return false;
            }
        });
    }

    private void setMyTabHost(int index) {
        transaction = fragmentManager.beginTransaction();
        if (merchantListFragment != null) {
            transaction.hide(merchantListFragment);
        }
        if (storeListFragment != null) {
            transaction.hide(storeListFragment);
        }
        clearText();
        //切换tab search需要清空
        edtSearch.setText("");
        tabIndex = index;
        switch (index) {
            case 1:
                edtSearch.setHint("请输入商户名");
                merchantListLine.setVisibility(View.VISIBLE);
                if (merchantListFragment == null) {
                    merchantListFragment = new MerchantListFragment();
                    transaction.add(R.id.content, merchantListFragment);
                } else {
                    transaction.show(merchantListFragment);
                }

                if(storeListFragment != null){
                    storeListFragment.updateSearch("");
                }
                break;
            case 2:
                edtSearch.setHint("请输入门店名");
                storeListLine.setVisibility(View.VISIBLE);
                if (storeListFragment == null) {
                    storeListFragment = new StoreListFragment();
                    transaction.add(R.id.content, storeListFragment);
                } else {
                    transaction.show(storeListFragment);
                }
                if(merchantListFragment != null){
                    merchantListFragment.updateSearch("");
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void clearText() {
        merchantListLine.setVisibility(View.INVISIBLE);
        storeListLine.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected MerchantPresenter createPresenter() {
        return new MerchantPresenter(this, getActivity());
    }

    @OnClick({R.id.tv_search, R.id.merchant_list, R.id.store_list, R.id.iv_search_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                hideSystemSofeKeyboard();
                if(edtSearch.getText().toString().trim().equals("")){
                    return;
                }
                updateSearch();
                break;
            case R.id.merchant_list:
                if(tabIndex != 1){
                    setMyTabHost(1);
                }
                break;
            case R.id.store_list:
                if(tabIndex != 2){
                    setMyTabHost(2);
                }
                break;
            case R.id.iv_search_clear:
                ivSearchClear.setVisibility(View.GONE);
                edtSearch.setText("");
                updateSearch();
                break;
        }
    }

    /**
     * 隐藏系统键盘
     */
    public void hideSystemSofeKeyboard() {
        // 如果软键盘已经显示，则隐藏
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 需要搜索数据
     */
    private void updateSearch(){
        if(tabIndex == 1){
            if(merchantListFragment != null){
                merchantListFragment.updateSearch(edtSearch.getText().toString().trim());
            }
        }else {
            if(storeListFragment != null){
                storeListFragment.updateSearch(edtSearch.getText().toString().trim());
            }
        }
    }
}
