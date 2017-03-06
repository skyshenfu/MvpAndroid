package com.elearningpath.wetestx.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.elearningpath.wetestx.R;

/**
 * Created by zhangty on 2017/3/3.
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        init();
    }
    private void init() {
        setContentView(R.layout.dialog_layout);
        setCancelable(false);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
}
