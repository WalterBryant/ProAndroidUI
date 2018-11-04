package com.w.proandroid.ui.fragmentsinfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.w.proandroid.R;
import com.w.proandroid.ui.fragmentsinfragment.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;


public class OrderHistoryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private OrderListAdapter mAdapter;
    private List<String> orderList = new ArrayList<>();

    public static OrderHistoryFragment getInstance(String stateType) {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_type", stateType);
        fragment.setArguments(bundle);
        return fragment;
    }

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
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = view.findViewById(R.id.order_list_recycler);
    }

    private void initData() {

        mAdapter = new OrderListAdapter(getActivity(), orderList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));


    }


}
