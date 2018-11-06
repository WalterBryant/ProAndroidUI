package com.w.proandroid.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.w.proandroid.MainActivity;
import com.w.proandroid.R;

/**
 * Created by jp on 2018/10/29.
 */
public class MainBottonView extends LinearLayout implements View.OnClickListener {

    private MainBottomClickTabCallbackListener mClickTabCallbackListener;
    private MainBottomMenu mBottomMenu;

    int mCurrentFocus = -1;
    int lastIndex = -1;

    public MainBottonView(Context context) {
        super(context);
        init();
    }

    public MainBottonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.main_bottom_menu, this);
        mBottomMenu = new MainBottomMenu();

        for (int i = 0; i < MainBottomMenu.ITEM_COUNT; i++) {
            ViewGroup container = findViewById(MainBottomMenu.linears_id[i]);
            container.setOnClickListener(this);
            mBottomMenu.containers[i] = container;
            mBottomMenu.images[i] = findViewById(MainBottomMenu.images_id[i]);
            mBottomMenu.texts[i] = findViewById(MainBottomMenu.texts_id[i]);
        }
    }

    public void setClickTabCallbackListener(MainBottomClickTabCallbackListener listener) {
        this.mClickTabCallbackListener = listener;
    }

    public int getCurrentFocus() {
        return mCurrentFocus;
    }

    public void setCurrentFocus(int mCurrentFocus) {
        this.lastIndex = this.mCurrentFocus;
        this.mCurrentFocus = mCurrentFocus;
    }

    public void refreshUI(int index) {
        setCurrentFocus(index);
        mBottomMenu.resetMenu();
        mBottomMenu.setSelectionMenu(index);
    }

    @Override
    public void onClick(View v) {
        onBottomMenuClick(v);
    }

    private void onBottomMenuClick(View view) {
        for (int i = 0; i < MainBottomMenu.ITEM_COUNT; i++) {
            if (view.getId() == MainBottomMenu.linears_id[i]) {
                if (i != mCurrentFocus) {
                    mClickTabCallbackListener.onClickTab(i);
                }
            }
        }
    }

    public interface MainBottomClickTabCallbackListener {
        void onClickTab(int var1);
    }
}
