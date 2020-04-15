package com.fumiao.assistant.ui.activity.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.mvp.home.InComingDraftPresenter;
import com.fumiao.assistant.mvp.home.InComingDraftView;
import com.fumiao.assistant.tools.DBManager;
import com.fumiao.assistant.ui.adapter.DraftAdapter;
import com.fumiao.assistant.ui.dialog.MsgDialog;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by zhaolong on 2019/9/16.
 * 进件草稿
 */
public class InComingDraftActivity extends MvpActivity<InComingDraftPresenter> implements InComingDraftView {

    @BindView(R.id.rv_draft_list)
    RecyclerView rvDraftList;
    DraftAdapter draftAdapter;
    List<InComing> inComingList;
    MsgDialog msgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_draft);
        init();
    }

    private void init() {
        setTitle("进件草稿");
        msgDialog = new MsgDialog(this);
        inComingList = new ArrayList<>();
        draftAdapter = new DraftAdapter(this, R.layout.item_incoming_draft, inComingList);
        rvDraftList.setAdapter(draftAdapter);
        rvDraftList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rvDraftList.addItemDecoration(dividerItemDecoration);
        draftAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                InComing inComing = inComingList.get(position);
                String merchantType = inComing.getMerchant_type();
                Bundle bundle = new Bundle();
                bundle.putSerializable("incoming", inComing);
                if(merchantType != null){
                    if(merchantType.equals("1")){
                        jumpActivity(MicroMerchantInfoActivity.class, bundle);
                    }else if(merchantType.equals("2")){
                        jumpActivity(NormalMerchantInComingActivity.class, bundle);
                    }
                }
            }
        });
        draftAdapter.setOnItemLongClickListner(new CoreRecycleAdapter.OnItemLongClickListner() {
            @Override
            public void onItemLongClickListner(View v, int position) {
                msgDialog.show("是否删除", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InComing inComing = inComingList.get(position);
                        DBManager.getInstance(InComingDraftActivity.this).deleteInComing(inComing);
                        inComingList.remove(position);
                        draftAdapter.notifyDataSetChanged();
                        msgDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getInComingDraftData();
    }

    private void getInComingDraftData(){
        List<InComing> inComings = DBManager.getInstance(this).queryAllInComing();
        if(inComings != null){
            inComingList.clear();
            inComingList.addAll(inComings);
            draftAdapter.notifyDataSetChanged();
        }
        if (inComings == null || inComings.size() == 0){
            showEmpty(ContextCompat.getDrawable(this, R.mipmap.ic_merchant_list_null), "暂无草稿哦");
        }else {
            hideEmpty();
        }
    }

    @Override
    protected InComingDraftPresenter createPresenter() {
        return new InComingDraftPresenter(this, this);
    }
}
