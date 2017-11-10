package com.fengyangts.jplaytext;

import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/10/30.
 */

public class ImgActivity extends AppCompatActivity {

    private ImageView bigImg;
    private int lastX;
    private int lastY;
    private int mTop;
    private int mLeft;
    private Matrix matrix = new Matrix();
    private BarChart barChart1;
    private BarChartManager barChartManager1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_activity);
        bigImg = (ImageView) findViewById(R.id.bigImg);
        barChart1 = (BarChart) findViewById(R.id.bar_chart1);
        barChartManager1 = new BarChartManager(barChart1);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yValue.add((float) (Math.random() * 100));
            }
            yValues.add(yValue);
        }
        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.RED);
        colours.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");
        names.add("折线二");
        names.add("折线三");
        names.add("折线四");
        //创建多条折线的图表
        barChartManager1.showBarChart(xValues, yValues.get(0), names.get(1), colours.get(3));
//        barChartManager1.showBarChart(xValues, yValues, names, colours);
        barChartManager1.setDescription("");
        barChartManager1.setXAxis((float) (xValues.size()-0.5), -0.5f, xValues.size()-1);
    }

    private void getImgMatrxi() {
        bigImg.setOnTouchListener(new View.OnTouchListener() {
            private int bottom;
            private int right;
            private int top;
            private int left;
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int screenW = dm.widthPixels;
            int screenH = dm.heightPixels;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        mTop = (int) event.getRawX();
                        mLeft = (int) event.getRawY();
                        float[] values = new float[9];
                        matrix.getValues(values);
                        left = view.getLeft();
                        top = view.getTop();
                        right = view.getRight();
                        bottom = view.getBottom();

                        break;
                    case MotionEvent.ACTION_UP:
                        onBackPressed();
                        endAlphAnimotion();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        int moH = (int) event.getRawY() - mLeft;
                        float bi = ((float) moH / screenH);

                        if (bi >= 0) {
                            Log.d("1111111111111", bi + "");
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                            layoutParams.leftMargin = layoutParams.leftMargin + dx;
                            layoutParams.topMargin = layoutParams.topMargin + dy;
                            view.setLayoutParams(layoutParams);
                            startAlphAnimotion(1 - bi);
                        }
                        left = view.getLeft() + dx;
                        top = view.getTop() + dy;
                        right = view.getRight() + dx;
                        bottom = view.getBottom() + dy;
                        view.layout(left, top, right, bottom);
                        view.postInvalidate();


                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();

                        break;
                }
                return true;
            }
        });
    }

    //改变屏幕透明度
    public void startAlphAnimotion(float alpha) {
        WindowManager.LayoutParams ll = getWindow().getAttributes();
        ll.alpha = alpha;
        getWindow().setAttributes(ll);
    }


    //恢复屏幕透明度
    public void endAlphAnimotion() {
        WindowManager.LayoutParams ll = getWindow().getAttributes();
        ll.alpha = 1;
        getWindow().setAttributes(ll);
    }

}
