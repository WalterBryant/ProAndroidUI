package com.w.proandroid.ui.fragmentsinfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.w.proandroid.R;


public class OrderHistoryFragment extends Fragment {


    public static OrderHistoryFragment getIntance(String stateType) {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_type", stateType);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {

    }

    private void initData() {

    }


}
