package com.example.pradumn.sssdashboard_2.Adapters;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pradumn.sssdashboard_2.Fragments.HealthCheckFragment;
import com.example.pradumn.sssdashboard_2.Fragments.OrdersCountFragment;
import com.example.pradumn.sssdashboard_2.Fragments.OrdersDispatchedFragment;
import com.example.pradumn.sssdashboard_2.R;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;

/**
 * Created by Pradumn on 11-Sep-17.
 */

public class FragmentListViewAdapter extends ArrayAdapter<String> {
    private Fragment[] mFragments;
    private TabLayout mTabLayout;
    private View currentlySelectedItemView;
    private ArrayList<String> mFragmentNames;
    private Context mContext;

    public FragmentListViewAdapter(Context context, ArrayList<String> fragmentNames, TabLayout tabLayout, Fragment[] fragments) {
        super(context, 0);
        mFragmentNames = fragmentNames;
        mContext = context;
        mTabLayout = tabLayout;
        mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragmentNames.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_listview_item, parent, false);
        }

        //setting up fragment name in textView
        TextView fragmentNameTextView = (TextView) convertView.findViewById(R.id.fragment_listView_textView);
        fragmentNameTextView.setText(mFragmentNames.get(position));

        //setting up onClick listener on listview item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //invoking the respective fragment
                FragmentManager fragmentManager = ((Activity) mContext).getFragmentManager();
                FragmentTransaction fragmentTransaction;
                switch (position) {
                    case 0:
                        mTabLayout.setVisibility(View.GONE);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, mFragments[0]);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        mTabLayout.setVisibility(View.VISIBLE);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, mFragments[1]);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        mTabLayout.setVisibility(View.GONE);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, mFragments[3]);
                        fragmentTransaction.commit();
                        break;
                }

                //changing view background
                currentlySelectedItemView.setBackgroundColor(mContext.getResources().getColor(R.color.listview_item_not_selected_color));
                currentlySelectedItemView = view;
                currentlySelectedItemView.post(new Runnable() {
                    @Override
                    public void run() {
                        Animator anim = ViewAnimationUtils.createCircularReveal(currentlySelectedItemView, currentlySelectedItemView.getWidth() / 2, currentlySelectedItemView.getHeight() / 2, 0, (int) Math.hypot(currentlySelectedItemView.getWidth() / 2, currentlySelectedItemView.getHeight() / 2));
                        currentlySelectedItemView.setBackgroundColor(mContext.getResources().getColor(R.color.listview_item_selected_color));
                        anim.start();
                    }
                });
            }
        });


        //below code makes Health Check Selected
        if (currentlySelectedItemView == null) {
            currentlySelectedItemView = convertView;
            currentlySelectedItemView.performClick();
        }
        return convertView;
    }
}