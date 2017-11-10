package com.fengyangts.jplaytext;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Message:  贝塞尔曲线  及绘制动画
 * Created by  ChenLong.
 * Created by Time on 2017/10/26.
 */

public class BasRView extends View {

    private Paint paint;
    private Paint arcPaint;
    private Paint textPaint;
    private PointF start;
    private PointF end;
    private PointF center;
    private ValueAnimator valueAnimator;
    private float value;

    public BasRView(Context context) {
        super(context);
        init();
    }

    public BasRView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BasRView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);

        arcPaint = new Paint();
        arcPaint.setColor(Color.RED);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(3);
        arcPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(3);
        textPaint.setTextSize(44);
        textPaint.setAntiAlias(true);

        start = new PointF(0,0);
        end = new PointF(0,0);
        center = new PointF(0,0);
        valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                Log.d("111111",value+"");
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.x = w/2+200;
        center.y = h/2-400;

        start.x = w/2-200;
        start.y = h/2;

        end.x = w/2+200;
        end.y = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(center.x,center.y+200);
        PathMeasure pathMeasure = new PathMeasure();
        canvas.drawPoint(start.x,start.y,paint);
        canvas.drawPoint(end.x,end.y,paint);
        canvas.drawPoint(center.x,center.y,paint);

        canvas.drawLine(start.x,start.y,center.x,center.y,paint);
        canvas.drawLine(end.x,end.y,center.x,center.y,paint);

        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(center.x,center.y,end.x,end.y);
        pathMeasure.setPath(path,false);
        Path des = new Path();
        pathMeasure.getSegment(0,pathMeasure.getLength()*value,des,true);
        canvas.drawPath(des, arcPaint);
        canvas.drawTextOnPath("陈龙陈龙陈龙陈龙",des,0,44,textPaint);

        Path path1 = new Path();
        path1.addCircle(center.x,center.y,200, Path.Direction.CW);
        pathMeasure.setPath(path1,false);
        Path des1 = new Path();
        pathMeasure.getSegment(0,pathMeasure.getLength()*value,des1,true);
        canvas.drawPath(des1, arcPaint);

        RectF rectF = new RectF(0,0,400,400);
        canvas.drawArc(rectF,360*value,360,true,arcPaint);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        center.x = event.getX();
        center.y = event.getY();
        invalidate();
        return true;
    }
}
