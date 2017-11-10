package com.fengyangts.jplaytext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Message:  基类的封装
 * Created by  ChenLong.
 * Created by Time on 2017/11/6.
 */

public class Baseactivity extends AppCompatActivity {

    private ImageView topBack;
    private TextView topTitle;
    private ImageView topSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void findTopBar(){
        topBack = (ImageView) findViewById(R.id.topBack);
        topTitle = (TextView) findViewById(R.id.topTitle);
        topSearch = (ImageView) findViewById(R.id.topSearch);
        topBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBack();
            }
        });
        topSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSearch();
            }
        });
    }
    protected void setTopBack(int resId){
        if (topBack!=null){
            topBack.setImageResource(resId);
        }
    }
    protected void setTopTitle(int resId){
        if (topTitle!=null){
            topTitle.setText(getString(resId));
        }
    }
    protected void setTopSearch(int resId){
        if (topSearch!=null){
            topSearch.setImageResource(resId);
        }
    }
    protected void clickSearch() {
        Toast.makeText(this, "右侧图标", Toast.LENGTH_SHORT).show();
    }
    protected void clickBack() {
        finish();
    }
}
