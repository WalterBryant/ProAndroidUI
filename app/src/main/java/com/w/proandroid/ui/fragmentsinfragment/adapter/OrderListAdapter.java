package com.w.proandroid.ui.fragmentsinfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.w.proandroid.R;

import java.util.List;

/**
 * Created by jp on 2018/10/26.
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private Context context;
    private List<String> orderItemList;

    public OrderListAdapter(Context context, List<String> orderItemList) {
        this.context = context;
        this.orderItemList = orderItemList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String orderName = orderItemList.get(position);
        holder.orderNameText.setText(orderName);
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderNameText;

        public ViewHolder(View itemView) {
            super(itemView);

            orderNameText = itemView.findViewById(R.id.order_name_text);
        }
    }
}
