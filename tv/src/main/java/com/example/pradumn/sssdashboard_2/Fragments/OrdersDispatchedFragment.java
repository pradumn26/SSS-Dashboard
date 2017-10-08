package com.example.pradumn.sssdashboard_2.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pradumn.sssdashboard_2.R;

public class OrdersDispatchedFragment extends android.app.Fragment {
    public OrdersDispatchedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_dispatched, container, false);

        return view;
    }
}