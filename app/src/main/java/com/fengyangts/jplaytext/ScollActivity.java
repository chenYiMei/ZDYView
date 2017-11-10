package com.fengyangts.jplaytext;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/9.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class ScollActivity extends Baseactivity {

    private ScrollView scollView;
    private RelativeLayout topBar;
    private RecyclerView recycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoll_activity);
        findTopBar();
        scollView = (ScrollView) findViewById(R.id.scollView);
        topBar = (RelativeLayout) findViewById(R.id.topBar);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        topBar.getBackground().setAlpha(0);
//        setTopBarAlphe();
        recycle.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter adapter = new DataAdapter(this);
        recycle.setAdapter(adapter);
        recycle.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                float i = (getScollYDistance()*1.0f )/ (topBar.getHeight()*3.0f);
                if (i>1){
                    i = 1;
                }
                topBar.getBackground().setAlpha((int)(i*255));
                Log.d("111111","scrollY"+getScollYDistance());
                Log.d("111111","getHeight"+recycle.getHeight());
            }
        });
    }

    @Override
    protected void onDestroy() {
        topBar.getBackground().setAlpha(255);
        super.onDestroy();
    }

    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recycle.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    //ScrollView 的滑动
    private void setTopBarAlphe() {
        scollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float i = (scrollY*1.0f )/ (topBar.getHeight()*3.0f);
                if (i>1){
                    i = 1;
                }
                int i1 = Utils.px2dip(ScollActivity.this, topBar.getHeight());
                topBar.getBackground().setAlpha((int)(i*255));
                Log.d("111111","scrollY"+scrollY);
                Log.d("111111","backView"+i1);
            }
        });
    }
}
