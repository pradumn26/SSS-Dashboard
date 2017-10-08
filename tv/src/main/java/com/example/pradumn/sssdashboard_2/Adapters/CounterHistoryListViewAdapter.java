package com.example.pradumn.sssdashboard_2.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pradumn.sssdashboard_2.Model.Order;
import com.example.pradumn.sssdashboard_2.R;

import java.util.ArrayList;

/**
 * Created by Pradumn on 25-Sep-17.
 */

public class CounterHistoryListViewAdapter extends ArrayAdapter {
    Context mContext;
    ArrayList<Order> mOrders;

    public CounterHistoryListViewAdapter(Context context, ArrayList<Order> orders) {
        super(context, 0);
        mContext = context;
        mOrders = orders;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.count_history_list_item, parent, false);

        TextView orderIdTextView = (TextView) convertView.findViewById(R.id.orderIdTextView);
        orderIdTextView.setText(mOrders.get(position).getOrderId() + "");
        TextView countTextView = (TextView) convertView.findViewById(R.id.historyCountTextView);
        countTextView.setText(mOrders.get(position).getCount() + "");

        return convertView;
    }

    @Override
    public int getCount() {
        return mOrders.size();
    }
}
