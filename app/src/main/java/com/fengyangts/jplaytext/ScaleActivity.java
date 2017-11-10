package com.fengyangts.jplaytext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

/**
 * Message:
 * Created by  ChenLong.
 * Created by Time on 2017/11/8.
 */

public class ScaleActivity extends Baseactivity {


    private VDHLinearLayout2 vdhLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scalelayout);
        getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        vdhLayout = (VDHLinearLayout2) findViewById(R.id.vdhLayout);

        vdhLayout.setOnOffseLost(new VDHLinearLayout2.OnViewClose() {
            @Override
            public void onClose(float offse) {
                if (offse >= 0.5) {
                    onBackPressed();
                }
            }

            @Override
            public void onMove(float offse) {
                if ((1 - offse) > 0)
                Log.d("attributes", "" + offse);
            }

            @Override
            public void onDis() {
                onBackPressed();
            }
        });
    }
}
