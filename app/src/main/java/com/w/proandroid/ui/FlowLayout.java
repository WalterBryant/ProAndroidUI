package com.w.proandroid.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jp on 2018/9/25.
 */

public class FlowLayout extends ViewGroup {

    /**
     * 用来保存每行view的列表
     */
    private List<List<View>> mViewLinesList = new ArrayList<>();

    /**
     * 用来保存行高的列表
     */
    private List<Integer> mLineHeights = new ArrayList<>();

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取父容器为FlowLayout设置的测量模式和大小
        int iWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int iHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int iWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int iHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;
        int iCurLineW = 0;
        int iCurLineH = 0;
        if (iWidthMode == MeasureSpec.EXACTLY && iHeightMode == MeasureSpec.EXACTLY) {
            measureWidth = iWidthSpecSize;
            measureHeight = iHeightSpecSize;
        } else {
            int iChildWidth;
            int iChildHeight;
            int childCount = getChildCount();
            List<View> viewList = new ArrayList<>();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                iChildWidth = childView.getMeasuredWidth() + layoutParams.leftMargin +
                        layoutParams.rightMargin;
                iChildHeight = childView.getMeasuredHeight() + layoutParams.topMargin +
                        layoutParams.bottomMargin;

                if (iCurLineW + iChildWidth > iWidthSpecSize) {
                    /** 1、记录当前行的信息 ***/

                    //1、记录当前行的最大宽度，高度累加
                    measureWidth = Math.max(measureWidth, iCurLineW);
                    measureHeight += iCurLineH;
                    //2、将当前行的viewList添加至总的mViewList， 将行高添加至总的行高List
                    mViewLinesList.add(viewList);
                    mLineHeights.add(iCurLineH);


                    /** 2、记录新一行的信息***/

                    //1、重新赋值新一行的宽、高
                    iCurLineW = iChildWidth;
                    iCurLineH = iChildHeight;

                    //2、新建一行的viewList，添加新一行的view
                    viewList = new ArrayList<>();
                    viewList.add(childView);

                } else {

                    //记录某行内的信息
                    //1、行内宽度的叠加、高度比较
                    iCurLineW += iChildWidth;
                    iCurLineH = Math.max(iCurLineH, iChildHeight);

                    //2、添加至当前行的viewList中
                    viewList.add(childView);
                }

                /** 3、如果正好是最后一行需要换行***/
                if (i == childCount - 1) {
                    //1、记录当前行的最大宽度，高度累加
                    measureWidth = Math.max(measureWidth, iCurLineW);
                    measureHeight += iCurLineH;

                    //2、将当前行的viewList添加至总的mViewList，将行高添加至总的行高List
                    mViewLinesList.add(viewList);
                    mLineHeights.add(iCurLineH);
                }
            }
        }

        setMeasuredDimension(measureWidth, measureHeight);
    }

    /**
     * onLayout的作用就是确定子View的位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top, right, bottom;
        int curTop = 0;
        int curLeft = 0;
        int lineCount = mViewLinesList.size();
        for (int i = 0; i < lineCount; i++) {
            List<View> viewList = mViewLinesList.get(i);
            int lineViewSize = viewList.size();
            for (int j = 0; j < lineViewSize; j++) {
                View childView = viewList.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

                left = curLeft + layoutParams.leftMargin;
                top = curTop + layoutParams.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
                childView.layout(left, top, right, bottom);
                curLeft += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            curLeft = 0;
            curTop += mLineHeights.get(i);
        }
        mViewLinesList.clear();
        mLineHeights.clear();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int index);
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            final int finalI = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, finalI);
                }
            });
        }
    }
}
