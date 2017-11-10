package com.fengyangts.jplaytext;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/7.
 */

public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
    private View contView;
    private SparseArray<View> mView = new SparseArray<>();//存控件的集合
    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        contView = itemView;
    }
    //找控件
    public <T extends View>T findView(int viewId){
        if (contView != null){
            T view = (T) mView.get(viewId);
            if (view == null){
                view = (T) contView.findViewById(viewId);
                mView.put(viewId,view);
            }
            return view;
        }
        return null;
    }
}
