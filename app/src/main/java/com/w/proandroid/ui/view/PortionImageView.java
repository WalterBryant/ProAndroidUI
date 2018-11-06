package com.w.proandroid.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by jp on 2018/10/29.
 */
public class PortionImageView extends AppCompatImageView {
    private Bitmap showPic = null;
    private int startY = 0;
    private int startX = 0;

    public PortionImageView(Context context) {
        super(context);
    }

    public PortionImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showPic != null) {
            canvas.drawBitmap(showPic, startX, startY, null);
        }
    }

    public void setBitmapShow(Bitmap b, int x, int y) {
        if (b == null)
            return;

        showPic = b;
        startX = x;
        startY = y;
        postInvalidate();
    }
}
