package com.w.proandroid;

import android.app.Application;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.w.proandroid.utils.Utils;

/**
 * Created by jp on 2018/10/25.
 */

public class MainApplication extends Application {

    private static volatile MainApplication mApp = null;

    public static MainApplication getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        mApp = this;
        super.onCreate();

        initlize();
    }

    private void initlize() {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Configuration cf = getResources().getConfiguration();
        int ori = cf.orientation;
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            Utils.mWidthPixels = displayMetrics.heightPixels;
            Utils.mHeightPixels = displayMetrics.widthPixels;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {//竖屏
            Utils.mHeightPixels = displayMetrics.heightPixels;
            Utils.mWidthPixels = displayMetrics.widthPixels;
        }
    }
}
