package com.w.proandroid.ui.view;

import android.app.Dialog;
import android.content.Context;

import com.w.proandroid.R;

public class LoadingDialog {
    private final Dialog mLoadingDialog;

    public LoadingDialog(Context context) {
        mLoadingDialog = new Dialog(context, R.style.MyDialogStyle);
    }
}
