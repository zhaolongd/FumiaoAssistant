package com.fumiao.assistant.ui.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fumiao.assistant.R;
import com.fumiao.assistant.app.MvpActivity;
import com.fumiao.assistant.bean.home.CateListBean;
import com.fumiao.assistant.mvp.home.StoreTypePresenter;
import com.fumiao.assistant.mvp.home.StoreTypeView;
import com.fumiao.assistant.ui.adapter.StoreTypeAdapter;
import com.fumiao.assistant.widget.MyGridView;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.Callback;
import com.fumiao.core.uitls.ToastUtil;
import java.util.ArrayList;
import butterknife.BindView;



public class StoreTypeActivity extends MvpActivity<StoreTypePresenter> implements StoreTypeView {
    @BindView(R.id.type_grid)
    MyGridView typeGrid;
    @BindView(R.id.select_layout)
    LinearLayout selectLayout;
    int selectType = 0; //选中一级标题的position
    Adapter cateItemAdapter;
    String cate_id;
    CateListBean cateListBean;
    ArrayList<CateListBean.CateBean> cateBeans;
    ArrayList<CateListBean.CateBean.ChildBean> childBeans;
    Callback<ArrayList<CateListBean.CateBean.ChildBean>> childBeanCallback;
    ArrayList<CateListBean.CateBean.ChildBean> selectChildBeans;
    @BindView(R.id.type_name_rcy)
    RecyclerView typeNameRcy;
    StoreTypeAdapter storeTypeAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_type, false);
        init();
        cate_id = getIntent().getStringExtra("cate_id");
    }

    private void init() {
        setTitle("主营类目");
        setRight("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectLayout.getChildCount() == 1) {
                    ToastUtil.show("至少添加一个分类");
                    return;
                }
                StringBuffer cate_id = new StringBuffer();
                StringBuffer cate_name = new StringBuffer();
                for (int i = 0; i < selectChildBeans.size(); i++) {
                    if (i != 0) {
                        cate_id.append(",");
                        cate_name.append(",");
                    }
                    cate_name.append(selectChildBeans.get(i).getName());
                    cate_id.append(selectChildBeans.get(i).getId());
                }
                Intent intent = new Intent();
                intent.putExtra("cate_name", cate_name.toString());
                intent.putExtra("cate_id", cate_id.toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mvpPresenter.getCateList();
        cateBeans = new ArrayList<>();
        storeTypeAdapter = new StoreTypeAdapter(this, R.layout.item_store_type, cateBeans);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        typeNameRcy.setLayoutManager(lm);
        typeNameRcy.setAdapter(storeTypeAdapter);
    }

    @Override
    protected StoreTypePresenter createPresenter() {
        return new StoreTypePresenter(this, this);
    }


    public void show(CateListBean cateListBean, String cateId, Callback<ArrayList<CateListBean.CateBean.ChildBean>> childBeanCallback) {
        this.cateListBean = cateListBean;
        this.childBeanCallback = childBeanCallback;
        initData(cateId);
    }

    public void initData(String cateId) {
        selectChildBeans = new ArrayList<>();
        childBeans = new ArrayList<>();
        cateItemAdapter = new Adapter();
        typeGrid.setAdapter(cateItemAdapter);
        typeGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
        typeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                if (selectLayout.getChildCount() == 4) {
                    ToastUtil.show("最多可添加三个分类");
                    return;
                }
                if (selectChildBeans.contains(childBeans.get(position))) {
                    ToastUtil.show("类目已存在");
                    return;
                }
                //add point
                cateBeans.get(selectType).count++;
                storeTypeAdapter.notifyDataSetChanged();
                //end
                selectChildBeans.add(childBeans.get(position));
                selectLayout.getChildAt(0).setVisibility(View.GONE);
                final View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_store_cate, null);
                final TextView textView = view1.findViewById(R.id.item_store_cate_name);
                textView.setText(childBeans.get(position).getName());
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < selectChildBeans.size(); i++) {
                            if (selectChildBeans.get(i).getName().equals(textView.getText().toString())) {
                                //add point
                                calculationCateCount(selectChildBeans.get(i).getPid(), 1);
                                //end
                                selectChildBeans.remove(i);
                            }
                        }
                        selectLayout.removeView(view1);
                        cateItemAdapter.notifyDataSetChanged();
                        if (selectLayout.getChildCount() == 1) {
                            selectLayout.getChildAt(0).setVisibility(View.VISIBLE);
                        }
                    }
                });
                selectLayout.addView(view1);
                cateItemAdapter.notifyDataSetChanged();
            }
        });
        cateBeans.addAll(cateListBean.getCate());
        if (cateBeans != null && cateBeans.size() > 0) {
            cateBeans.get(0).setSelect(true);
            childBeans.clear();
            if (cateBeans.get(0).get_child() != null) {
                childBeans.addAll(cateBeans.get(0).get_child());
            }
            cateItemAdapter.notifyDataSetChanged();
        }
        storeTypeAdapter.notifyDataSetChanged();
        storeTypeAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                selectType = position;
                setTypeSelect(position);
                childBeans.clear();
                if (cateBeans.get(position).get_child() != null) {
                    childBeans.addAll(cateBeans.get(position).get_child());
                }
                cateItemAdapter.notifyDataSetChanged();
            }
        });
        initSelect(cateId);
    }

    //pid:一级分类的id ，type： 0 增加1，1减少1
    private void calculationCateCount(int pid, int type){
        for(int i = 0; i < cateBeans.size(); i++){
            if(pid == cateBeans.get(i).getId()){
                if(type == 0){
                    cateBeans.get(i).count++;
                }else if(type == 1){
                    cateBeans.get(i).count--;
                }
            }
        }
        storeTypeAdapter.notifyDataSetChanged();
    }

    private void setTypeSelect(int position) {
        for (int i = 0; i < cateBeans.size(); i++) {
            cateBeans.get(i).setSelect(false);
        }
        cateBeans.get(position).setSelect(true);
        storeTypeAdapter.notifyDataSetChanged();
    }


    private void initSelect(String cate_id) {
        if (cate_id == null || cate_id.isEmpty()) {
            return;
        }
        String[] cateId = cate_id.split(",");
        for (String id : cateId) {
            for (int i = 0; i < cateListBean.getCate().size(); i++) {
                for (int j = 0; j < cateListBean.getCate().get(i).get_child().size(); j++) {
                    int id2 = cateListBean.getCate().get(i).get_child().get(j).getId();
                    if (id.equals(id2 + "")) {
                        selectChildBeans.add(cateListBean.getCate().get(i).get_child().get(j));
                        // add point
                        calculationCateCount(cateListBean.getCate().get(i).get_child().get(j).getPid(), 0);
                        //end
                    }
                }
            }
        }
        for (int i = 0; i < selectChildBeans.size(); i++) {
            selectLayout.getChildAt(0).setVisibility(View.GONE);
            final View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_store_cate, null);
            final TextView textView = view1.findViewById(R.id.item_store_cate_name);
            textView.setText(selectChildBeans.get(i).getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < selectChildBeans.size(); i++) {
                        if (selectChildBeans.get(i).getName().equals(textView.getText().toString())) {
                            // add point
                            calculationCateCount(selectChildBeans.get(i).getPid(), 1);
                            //end
                            selectChildBeans.remove(i);
                        }
                    }
                    selectLayout.removeView(view1);
                    cateItemAdapter.notifyDataSetChanged();
                    if (selectLayout.getChildCount() == 1) {
                        selectLayout.getChildAt(0).setVisibility(View.VISIBLE);
                    }
                }
            });
            selectLayout.addView(view1);
        }
    }

    @Override
    public void showCateList(CateListBean data) {
        show(data, cate_id, new Callback<ArrayList<CateListBean.CateBean.ChildBean>>() {
            @Override
            public void onSuccess(ArrayList<CateListBean.CateBean.ChildBean>[] childBeans) {

            }
        });
    }


    public class Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return childBeans.size();
        }

        @Override
        public CateListBean.CateBean.ChildBean getItem(int position) {
            return childBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return childBeans.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_text, null);
            TextView tvItem = convertView.findViewById(R.id.tv_item);
            tvItem.setText(getItem(position).getName());
            ImageView ivItemSelect = convertView.findViewById(R.id.iv_item_select);
            boolean itemState = false;

            for (int i = 0; i < selectChildBeans.size(); i++) {
                if (selectChildBeans.get(i).getId()==getItem(position).getId()) {
                    itemState = true;
                }
            }

            if (itemState) {
                tvItem.setBackgroundResource(R.drawable.round_select_bg);
                tvItem.setTextColor(getResources().getColor(R.color.font_press));
                ivItemSelect.setVisibility(View.VISIBLE);
            } else {
                tvItem.setBackgroundResource(R.drawable.round_gray_bg);
                tvItem.setTextColor(getResources().getColor(R.color.font_no_press));
                ivItemSelect.setVisibility(View.GONE);
            }

            return convertView;
        }
    }
}
