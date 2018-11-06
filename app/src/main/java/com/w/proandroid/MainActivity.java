package com.w.proandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.w.proandroid.ui.BaseActivity;
import com.w.proandroid.ui.main.MainBottonView;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    public enum MainMenu {
        HomePage,
        Category,
        ShoppigCart,
        MinePage;
    }

    private MainBottonView mBottonView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mBottonView = findViewById(R.id.main_bottom);
        mBottonView.setClickTabCallbackListener(new MainBottonView.MainBottomClickTabCallbackListener() {
            @Override
            public void onClickTab(int var1) {
//                MainMenu menu = MainMenu
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setTabSelection(MainMenu.HomePage);
    }

    public void setTabSelection(MainMenu menu) {

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }



    private void toFlowActivity() {
        Intent intent = new Intent(this, FlowActivity.class);
        startActivity(intent);
    }

    private void toMinionActivity() {
        Intent intent = new Intent(this, MinionActivity.class);
        startActivity(intent);
    }
}
