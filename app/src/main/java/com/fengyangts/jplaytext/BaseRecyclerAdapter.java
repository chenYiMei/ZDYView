package com.fengyangts.jplaytext;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/7.
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerHolder> {
    protected IClickItem clickItem;
    public BaseRecyclerAdapter(IClickItem clickItem) {
        this.clickItem = clickItem;
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder( parent,viewType);
    }
    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, final int position) {
        setViewData(holder,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickItem!=null){
                    clickItem.onItemClick(v,position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (clickItem!=null){
                    clickItem.onItemLongClick(v,position);
                }
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return getCount();
    }

    protected abstract BaseRecyclerHolder getHolder(ViewGroup parent,int viewType1);
    protected abstract void setViewData(BaseRecyclerHolder holder, int position);
    protected abstract int getCount();
}
