package com.fengyangts.jplaytext;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/8.
 */

public class VDHLinearLayout2 extends RelativeLayout {

    ViewDragHelper dragHelper;
    private float mDragOffset;
    int autoBackViewOriginLeft;
    int autoBackViewOriginTop;
    private int mDragRange;

    public VDHLinearLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == autoBackView;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                mDragOffset = ((top - autoBackViewOriginTop) * 1.0f) / (mDragRange * 1.0f);
                if (mDragOffset <= 0) {
                    mDragOffset = 0;
                }
                if (mDragOffset >= 1) {
                    mDragOffset = 1;
                }
                if ((1 - mDragOffset * 2) > 0)
                    childAt.setAlpha(1 - mDragOffset * 2);
                autoBackView.setPivotX(autoBackView.getWidth() / 2);
                autoBackView.setPivotY(autoBackView.getHeight() / 2);
                autoBackView.setScaleX(1- mDragOffset );
                autoBackView.setScaleY(1-mDragOffset);
                invalidate();
                Log.d("111111", "mDragOffset" + mDragOffset);
                Log.d("111111", "top" + top);
                Log.d("111111", "mDragRange" + mDragRange);
                Log.d("111111", "autoBackViewOriginTop" + autoBackViewOriginTop);
            }

            // 当前被捕获的View释放之后回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild == autoBackView) {
                    if (mDragOffset < 0.5) {
                        dragHelper.settleCapturedViewAt(autoBackViewOriginLeft, autoBackViewOriginTop);
                        invalidate();
                    }
                }
            }


            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            if (offseLost != null) {
                offseLost.onClose(mDragOffset);
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            if (offseLost != null) {
                offseLost.onMove(mDragOffset);
            }
        }
        dragHelper.processTouchEvent(event);
        return true;
    }

    public interface OnViewClose {
        void onClose(float offse);
        void onMove(float offse);
        void onDis();
    }

    OnViewClose offseLost;

    public void setOnOffseLost(OnViewClose l) {
        offseLost = l;
    }

    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    View autoBackView;
    View childAt;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        childAt = getChildAt(0);
        autoBackView = getChildAt(1);
        autoBackView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offseLost != null) {
                    offseLost.onDis();
                }
            }
        });
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mDragRange = getHeight();
        autoBackViewOriginLeft = autoBackView.getLeft();
        autoBackViewOriginTop = autoBackView.getTop();
    }
}
