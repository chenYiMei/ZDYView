package com.fengyangts.jplaytext;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Message:  侧滑菜单
 * Created by  ChenLong.
 * Created by Time on 2017/10/27.
 */

public class SlidingMenu extends FrameLayout {


    private ViewDragHelper mdDragHelper;
    private View menuView;
    private View mainView;
    private int menuWidth;
    OpenLisrener listener;
    Status preStatus =Status.Close;
    Status status=Status.Close;
    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mdDragHelper = ViewDragHelper.create(this, callback);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View view, int arg1) {
            return true;
        }

        public int getViewHorizontalDragRange(View child) {
            return menuWidth;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == mainView) {
                if (left < 0)
                    return 0;
                else if (left > menuWidth)
                    return menuWidth;
                else
                    return left;
            } else if (child == menuView) {
                if (left > 0)
                    return 0;
                else if (left > menuWidth)
                    return menuWidth;
                else
                    return left;
            }
            return 0;
        }

        public void onViewPositionChanged(View changedView, int left, int top,int dx, int dy) {
            if (changedView == mainView)
                menuView.offsetLeftAndRight(dx);
            else
                mainView.offsetLeftAndRight(dx);
            invalidate();
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild == mainView) {
                if (status == Status.Open) {
                    // 关闭侧滑菜单
                    close();
                    return;
                }
                if (xvel == 0
                        && Math.abs(mainView.getLeft()) > menuWidth / 2.0f) {
                    // 打开侧滑菜单
                    open();
                } else if (xvel > 0) {
                    open();
                } else {
                    close();
                }
            } else {
                if (xvel == 0
                        && Math.abs(mainView.getLeft()) > menuWidth / 2.0f) {
                    // 打开侧滑菜单
                    open();
                } else if (xvel > 0) {
                    open();
                } else {
                    // 关闭侧滑菜单
                    close();
                }
            }
        }

    };

    /**
     * 打开菜单
     */
    public void open() {
        if (mdDragHelper.smoothSlideViewTo(mainView, menuWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        preStatus = status;
        status = Status.Open;
        if (listener != null && preStatus == Status.Close) {
            listener.statusChanged(status);
        }
    }

    public enum Status{
        Close,Open
    }
    public interface OpenLisrener{
        void statusChanged(Status status);
    }
    public void setOnStatusListener(OpenLisrener listener) {
        this.listener = listener;
    }
    /**
     * 关闭菜单
     */
    public void close() {
        if (mdDragHelper.smoothSlideViewTo(mainView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        preStatus = status;
        status = Status.Close;
        if (listener != null && preStatus == Status.Open) {
            listener.statusChanged(status);
        }
    }

    /**
     * 切换菜单状态
     */
    public void toggle() {
        if (status == Status.Close) {
            open();
        } else {
            close();
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 开始执行动画
        if (mdDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new IllegalArgumentException("子view的数量必须为2个");
        }
        menuView = getChildAt(0);
        mainView = getChildAt(1);
        menuWidth = menuView.getLayoutParams().width;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        menuView.layout(-menuWidth, 0, 0, menuView.getMeasuredHeight());
        mainView.layout(0, 0, right, bottom);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mdDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mdDragHelper.processTouchEvent(event);
        return true;
    }
}
