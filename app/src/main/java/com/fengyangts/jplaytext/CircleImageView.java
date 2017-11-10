package com.fengyangts.jplaytext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Message:  圆形图片
 * Created by  ChenLong.
 * Created by Time on 2017/10/23.
 */

public class CircleImageView extends ImageView {
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    int color = Color.BLUE;
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Drawable drawable = getDrawable();
        Bitmap bitmap = drawableToBitmap(drawable,getWidth(),getHeight());


        Path path = new Path();
        path.addCircle(getWidth()/2, getHeight()/2, getWidth()/2-2, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
    public static Bitmap drawableToBitmap(Drawable drawable, int width, int height) {

        Bitmap bitmap = Bitmap.createBitmap(

                width,height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
        drawable.setBounds(0, 0, width, height);

        drawable.draw(canvas);

        return bitmap;

    }
}
