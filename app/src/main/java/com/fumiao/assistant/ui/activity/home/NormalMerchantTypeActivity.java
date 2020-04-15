package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.MerchantTypeListBean;
import com.fumiao.assistant.mvp.home.NormalMerchantTypePresenter;
import com.fumiao.assistant.mvp.home.NormalMerchantTypeView;
import com.fumiao.assistant.ui.adapter.MerchantTypeAdapter;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.google.android.flexbox.FlexboxLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: XieBoss
 * Date: 2019/10/11 0011 18:40
 *
 * @Description:
 */
public class NormalMerchantTypeActivity extends MvpActivity<NormalMerchantTypePresenter> implements NormalMerchantTypeView {

    @BindView(R.id.type_name_rcy)
    RecyclerView typeNameRcy;
    @BindView(R.id.fbl)
    FlexboxLayout fbl;
    MerchantTypeAdapter mTypeAdapter;
    int selectType = 0; //选中一级标题的position
    List<MerchantTypeListBean.MerchantTypeBean> mMerchantTypeBeans;
    List<MerchantTypeListBean.MerchantTypeBean.SubTypeBean> mSubTypeBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_merchant_type, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("工商类别");
        mMerchantTypeBeans = new ArrayList<>();
        mSubTypeBeans = new ArrayList<>();
        mTypeAdapter = new MerchantTypeAdapter(this, R.layout.item_merchant_type, mMerchantTypeBeans);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        typeNameRcy.setLayoutManager(lm);
        typeNameRcy.setAdapter(mTypeAdapter);
        mTypeAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                selectType = position;
                setTypeSelect(position);
                mSubTypeBeans.clear();
                if (mMerchantTypeBeans.get(position).getList() != null) {
                    mSubTypeBeans.addAll(mMerchantTypeBeans.get(position).getList());
                    fbl.removeAllViews();
                    for (int i = 0; i < mSubTypeBeans.size(); i++) {
                        MerchantTypeListBean.MerchantTypeBean.SubTypeBean childItem = mSubTypeBeans.get(i);
                        TextView child = createFlexItemTextView(fbl);
                        child.setText(childItem.getName());
//                        int finalI = i;
                        child.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("mcc_name", childItem.getName());
                                intent.putExtra("mcc_code", childItem.getMcc());
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        });
                        fbl.addView(child);
                    }
                }
            }
        });
        mvpPresenter.getMerchantList();
    }

    private LayoutInflater mInflater = null;

    private TextView createFlexItemTextView(FlexboxLayout fbl) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(fbl.getContext());
        }
        return (TextView) mInflater.inflate(R.layout.item_mcc_child, fbl, false);
    }

    private void setTypeSelect(int position) {
        for (int i = 0; i < mMerchantTypeBeans.size(); i++) {
            mMerchantTypeBeans.get(i).setSelect(false);
        }
        mMerchantTypeBeans.get(position).setSelect(true);
        mTypeAdapter.notifyDataSetChanged();
    }


    @Override
    protected NormalMerchantTypePresenter createPresenter() {
        return new NormalMerchantTypePresenter(this, this);
    }


    @Override
    public void showMerchantList(MerchantTypeListBean data) {
        if (data != null) {
            mMerchantTypeBeans.addAll(data.getMerchant_type());
            mSubTypeBeans.addAll(mMerchantTypeBeans.get(selectType).getList());
            for (int i = 0; i < mSubTypeBeans.size(); i++) {
                MerchantTypeListBean.MerchantTypeBean.SubTypeBean childItem = mSubTypeBeans.get(i);
                TextView child = createFlexItemTextView(fbl);
                child.setText(childItem.getName());
//                        int finalI = i;
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("mcc_name", childItem.getName());
                        intent.putExtra("mcc_code", childItem.getMcc());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                fbl.addView(child);
            }
            setTypeSelect(selectType);
            mTypeAdapter.notifyDataSetChanged();
        }
    }
}
