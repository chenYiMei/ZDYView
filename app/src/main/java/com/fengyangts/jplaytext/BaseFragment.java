package com.fengyangts.jplaytext;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by wangjian on 2017/4/6.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private View contView;
    private boolean isInitView=false;//是否初始化控件
    private boolean isVisible=false;//是否可见
    private boolean isFiastLoad=true;//是否第一次加载
    private SparseArray<View> mView;//存控件的集合
    public FragmentActivity activity;
    public int PAGE=1;
    public int SIZE = 15;
    private ProgressDialog dialog;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //切换的时候判断是否对用户可见
        if (isVisibleToUser){
            isVisible=true;
            lazyLoad();
        }else {
            isVisible=false;
        }
    }
    //懒加载
    private void lazyLoad() {
        //不是第一次加载 不是初始化View 不可见 则不加载数据
        if (!isFiastLoad || !isInitView || !isVisible){
            return;
        }
        initData();
        //设置不是第一次加载
        isFiastLoad=false;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = new SparseArray<>();
        activity = getActivity();
        //初始化布局
        contView = inflater.inflate(getLayoutId(), container, false);
        //初始化控件
        initViews();
        initListener();
        isInitView=true;
        lazyLoad();
        return contView;
    }
    public void showProgress(boolean show) {
        if (show) {
            dialog = new ProgressDialog(activity);
            dialog.setMessage("正在加载 ...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
    //找控件
    public <T extends View>T findView(int viewId){
        if (contView != null){
            T view = (T) mView.get(viewId);
            if (view == null){
                view = (T) contView.findViewById(viewId);
                mView.put(viewId,view);
            }
            return view;
        }
        return null;
    }
    //点击事件
    public <T extends View>void setOnClick(T view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onViewClick(view);
    }

    public abstract int getLayoutId();
    public abstract void initViews();
    public abstract void initListener();
    public abstract void initData();
    public abstract void onViewClick(View view);
}
