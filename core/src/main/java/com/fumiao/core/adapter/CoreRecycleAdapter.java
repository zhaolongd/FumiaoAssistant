package com.fumiao.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by yunshi on 2017/11/20.
 */

public abstract class CoreRecycleAdapter extends RecyclerView.Adapter<CoreViewHolder> {
    private int layoutId;
    public List<? extends CoreBean> data;
    public Context context;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    /**
     * viewType--分别为item以及空view
     */
    public static final int VIEW_TYPE_ITEM = 1;
    public static final int VIEW_TYPE_EMPTY = 0;

    /**
     *
     * @param context //上下文
     * @param layoutId  //布局id
     * @param data  //数据源
     */
    public CoreRecycleAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        this.layoutId = layoutId;
        this.data = data;
        this.context = context;
    }

    @Override
    public CoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //空view显示
        if (viewType == VIEW_TYPE_EMPTY) {
           return new CoreViewHolder(emptyView, context, true);
        }

        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        final CoreViewHolder holder = new CoreViewHolder(v, context);
//        //单击事件回调
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListner==null){
                    return;
                }
                if (clickFlag) {
                    onItemClickListner.onItemClickListner(v,holder.getLayoutPosition());
                }
                clickFlag = true;
            }
        });
        //单击长按事件回调
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListner==null){
                    return false;
                }
                onItemLongClickListner.onItemLongClickListner(v,holder.getLayoutPosition());
                clickFlag = false;
                return false;
            }
        });
        return holder;
    }


    public String getString(int id){
        return context.getString(id);
    }

    @Override
    public void onBindViewHolder(CoreViewHolder holder, int position) {
        if(holder.isEmptyView()){
            return;
        }
        convert(holder, data.get(position));
        convert(holder, data.get(position),position);
    }

    protected   <T extends CoreBean> void convert(CoreViewHolder holder, T bean){

    };
    protected   <T extends CoreBean> void convert(CoreViewHolder holder, T bean,int position){

    }

    @Override
    public int getItemCount() {
        //如果data.size()为0的话，只引入一个布局，就是emptyView
        // 那么，这个recyclerView的itemCount为1
        if (data.size() == 0 && emptyView != null) {
            return 1;
        }
        //如果不为0，按正常的流程跑
        return data.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (data.size() == 0  && emptyView != null) {
            return VIEW_TYPE_EMPTY;
        }
        //如果有数据，则使用ITEM的布局
        return VIEW_TYPE_ITEM;
    }

    private View emptyView;
    public void setEmptyView(View emptyView){
        this.emptyView = emptyView;
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}
