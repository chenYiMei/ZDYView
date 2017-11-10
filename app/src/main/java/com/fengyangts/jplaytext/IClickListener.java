package com.fengyangts.jplaytext;

import android.view.View;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/7.
 */

public interface IClickListener {
    void onItemClick(View v,int position);
    void onItemLongClick(View v,int position);
}
