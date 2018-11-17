package com.w.proandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.w.proandroid.ui.FlowLayout;

public class FlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        FlowLayout flowLayout = findViewById(R.id.flowlayout);

        flowLayout.setOnItemClickListener(new FlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int index) {
                Toast.makeText(FlowActivity.this, "this is the " + index, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
