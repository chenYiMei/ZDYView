package com.fengyangts.jplaytext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/2.
 */

public class DrawerlayoutActivity extends Baseactivity  {
    RecyclerView mListview;
    ListView leftListView;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draweractivity);
        findViews(); //获取控件
        findTopBar();
        setTopTitle(R.string.chcd);
        mListview.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter adapter = new DataAdapter(this);
        mListview.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("陈龙"+i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        leftListView.setAdapter(arrayAdapter);
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DrawerlayoutActivity.this, "点击的是第"+position+"个", Toast.LENGTH_SHORT).show();
                closeMenu();
            }
        });
    }

    @Override
    protected void clickBack() {
        closeMenu();
    }

    private void closeMenu() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 重写返回键, 当抽屉是打开的时候关闭抽屉,否则则不作处理,让系统处理
     */
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void findViews() {
        mListview = (RecyclerView) findViewById(R.id.fl_container);
        leftListView = (ListView) findViewById(R.id.leftListView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}
