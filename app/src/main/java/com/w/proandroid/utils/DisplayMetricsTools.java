package com.w.proandroid.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.w.proandroid.MainApplication;

/**
 * Created by jp on 2018/10/26.
 */
public class DisplayMetricsTools {

    private static Resources getResources() {
        return MainApplication.getApp().getResources();
    }

    private static DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    private static float getDensity() {
        return getDisplayMetrics().density;
    }

    public static int dip2px(float dpValue) {
        return (int) (dpValue * getDensity() + 0.5f);
    }
}
