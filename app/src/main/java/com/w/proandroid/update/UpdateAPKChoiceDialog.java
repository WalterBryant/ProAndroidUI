package com.w.proandroid.update;

import android.app.Dialog;
import android.content.Context;

public class UpdateAPKChoiceDialog {
    private Context mContext;
    private Dialog mUDialog = null;
    private UpdateApk updateApk;
    private boolean mShowLoading = false;

    public UpdateAPKChoiceDialog(Context mContext, boolean mShowLoading) {
        this.mContext = mContext;
        this.mShowLoading = mShowLoading;
    }
}
