package com.fengyangts.jplaytext;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ImageView img;
    private ProgressBar button;
    private TextView textNum;
    private TextView textWen;
    private int width;
    private int index= 0;
    private ImageView imgZhen;
    private Button slide;
    private ImageView botImg;
    private Button popWindow;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        findViewById(R.id.slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SlideMenuActivity.class));
            }
        });
        findViewById(R.id.slide1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DrawerlayoutActivity.class));
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImgActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,img,"sharedView").toBundle());
            }
        });
        button = (ProgressBar) findViewById(R.id.button);
        textNum = (TextView) findViewById(R.id.textNum);
        ValueAnimator valueAnimator = new ValueAnimator().ofInt(0, 86).setDuration(5000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                button.setProgress(animatedValue);
                textNum.setText(animatedValue+"%");
            }
        });
        valueAnimator.start();
        textWen = (TextView) findViewById(R.id.textWen);

        final List<String> list = new ArrayList<>();
        list.add("1发布时间东方闪电发生的纠纷或时代峰峻上的");
        list.add("2费手机的发布的数据发绑定手机腹背受敌发生部分");
        list.add("3数据库编数据发发斯蒂芬开始的部分空间少点放声大哭");
        list.add("4你急死风不大数据发表达式快捷方式贷款见风使舵");
        list.add("5你发的世纪东方绑定手机发布时间的发布当升科技发送的");

        textWen.setText(list.get(0));
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
         width = defaultDisplay.getWidth();
        ObjectAnimator animator = ObjectAnimator.ofFloat(textWen,"translationX",width,-width);
        animator.setDuration(5000);
        animator.setRepeatCount(-1);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                index++;
                if (index>list.size()-1){
                    index = 0;
                }
                textWen.setText(list.get(index));
            }
        });
        ObjectAnimator anima = ObjectAnimator.ofInt(textWen, "textColor", Color.parseColor("#000000"),Color.parseColor("#0000ff"));
        anima.setDuration(5000);
        anima.setRepeatCount(-1);
        anima.setEvaluator(new ArgbEvaluator());
        anima.start();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textWen,"translationX",0,-width);
        animator1.setDuration(5000);
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                index=1;
                textWen.setText(list.get(index));
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator1).before(animator).with(anima);
        animatorSet.start();
        textWen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, list.get(index), Toast.LENGTH_SHORT).show();
            }
        });
        botImg = (ImageView) findViewById(R.id.botImg);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(botImg,"scaleX",1,1.5f,1);
        objectAnimator.setDuration(500);
        final ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(botImg,"scaleY",1,1.5f,1);
        objectAnimator1.setDuration(500);

        botImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.start();
                objectAnimator1.start();
            }
        });
        findViewById(R.id.scolllayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScollActivity.class));
            }
        });
        popWindow = (Button) findViewById(R.id.popWindow);
        final List<CommonType> mList = new ArrayList<>();
        mList.add(new CommonType("陈龙1","1"));
        mList.add(new CommonType("陈龙2","2"));
        mList.add(new CommonType("陈龙3","3"));
        popWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupUtil.getInstance().showPopListWindow(MainActivity.this, popWindow, new PopupUtil.TypeControl() {
                    @Override
                    public void onPopItemClick(int position) {
                        Toast.makeText(MainActivity.this, mList.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }
                },mList);
            }
        });
    }
}
