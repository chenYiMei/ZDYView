package com.fengyangts.jplaytext;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Xiaoming on 2017/7/4 20:17.
 * Email:xiaoming_huo@163.com
 * Description:popupWindow弹窗工具
 */

public class PopupUtil {

    private static PopupUtil mUtil;
    private PopupWindow mPopupWindow;
    private List<CommonType> mData;
    private TypeControl mTypeControl;
    private TextView mAllView;

    public static PopupUtil getInstance() {
        if (mUtil == null) {
            mUtil = new PopupUtil();
        }
        return mUtil;
    }
    public void showPopListWindow(Context context, View dropView, TypeControl typeControl, List<CommonType> typeList) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) return;
        }
        mTypeControl = typeControl;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_layout, null);
        final ListView lv = (ListView) view.findViewById(R.id.pop_list_view);
        //设置全屏
        mPopupWindow = new Solve7PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setContentView(view);

        //设置背景
        mPopupWindow.setBackgroundDrawable(context.getResources().getDrawable(
                R.color.partTranslucent));
        mPopupWindow.setOutsideTouchable(true);

        //点击灰色背景则关闭弹窗
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("onTouch", "onTouch: " + event.getY() + "," + lv.getBottom());
                if (event.getY() > lv.getBottom()) {
                    mPopupWindow.dismiss();
                }
                return false;
            }
        });
        mData = typeList;
        final TypeAdapter adapter = new TypeAdapter(context, R.layout.item_plain, mData);
        lv.setAdapter(adapter);
        mPopupWindow.showAsDropDown(dropView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mData.size(); i++) {
                    mData.get(i).setSelected(position == i);
                }
                mPopupWindow.dismiss();
                mTypeControl.onPopItemClick(position);
            }
        });
    }
    private class TypeAdapter extends ArrayAdapter<CommonType> {

        private Context mContext;
        private int mResourceId;

        public TypeAdapter(Context context, int resource, List<CommonType> objects) {
            super(context, resource, objects);
            mContext = context;
            mResourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) LayoutInflater.from(mContext).inflate(mResourceId, parent, false);
            CommonType type = getItem(position);
            if (type.isSelected()) {
                textView.setTextColor(mContext.getResources().getColor(R.color.colorBlueText));
            } else {
                textView.setTextColor(mContext.getResources().getColor(R.color.colorNormText));
            }
            textView.setText(type.getName());
            return textView;
        }
    }
    public interface TypeControl {
        void onPopItemClick(int position);
    }

    public interface DateListener {
        void onDateSelector(String date);
    }
}
