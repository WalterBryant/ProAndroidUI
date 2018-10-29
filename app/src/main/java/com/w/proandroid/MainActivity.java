package com.w.proandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.w.proandroid.ui.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    public enum MainMenu {
        HomePage,
        Category,
        ShoppigCart,
        MinePage;
    }

    private Button flowButton;
    private Button minionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        flowButton = findViewById(R.id.flowlayout_button);
        minionButton = findViewById(R.id.minion_button);
    }

    private void initData() {
        flowButton.setOnClickListener(this);
        minionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flowlayout_button://流布局
                toFlowActivity();
                break;
            case R.id.minion_button://小黄人
                toMinionActivity();
                break;
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
