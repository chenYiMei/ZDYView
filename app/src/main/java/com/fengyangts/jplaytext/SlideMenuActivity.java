package com.fengyangts.jplaytext;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/2.
 */

public class SlideMenuActivity extends Baseactivity {

    private SlidingMenu slideMenu1;
    private ListView menu_list;
    private ImageView scaleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidemenu);
//        Utils.initState(this);
        findTopBar();
        scaleView = (ImageView) findViewById(R.id.scaleView);
        scaleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlideMenuActivity.this, ScaleActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SlideMenuActivity.this,scaleView,"sharedView").toBundle());
            }
        });
        slideMenu1 = (SlidingMenu) findViewById(R.id.slideMenu1);
        findViewById(R.id.imgMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenu1.toggle();
            }
        });
        menu_list = (ListView) findViewById(R.id.menu_list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("陈龙"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        menu_list.setAdapter(adapter);
    }

    @Override
    protected void clickSearch() {
        Toast.makeText(this, "点击了右图标", Toast.LENGTH_SHORT).show();
    }
}
