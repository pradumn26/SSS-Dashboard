package com.example.pradumn.sssdashboard_2.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.pradumn.sssdashboard_2.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pradumn on 15-Sep-17.
 */

public class FragmentExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<String> mHeadersList;
    private HashMap<String, ArrayList<String>> mChildList;

    public FragmentExpandableListAdapter(Context context, ArrayList<String> headersList, HashMap<String, ArrayList<String>> childList) {
        mContext = context;
        mHeadersList = headersList;
        mChildList = childList;
    }

    @Override
    public int getGroupCount() {
        return mHeadersList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (mChildList.get(mHeadersList.get(i)) != null)
            return mChildList.get(mHeadersList.get(i)).size();
        else
            return 0;
    }

    @Override
    public Object getGroup(int i) {
        return mHeadersList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mChildList.get(mHeadersList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String groupString = (String) getGroup(i);

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_listview_item, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.fragment_listView_textView);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(groupString);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childString = (String) getChild(i, i1);

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_listview_item_child, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.fragment_expandableListView_textView);
        textView.setText(childString);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}