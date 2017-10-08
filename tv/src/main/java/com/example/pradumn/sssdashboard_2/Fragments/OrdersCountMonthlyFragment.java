package com.example.pradumn.sssdashboard_2.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.daasuu.cat.CountAnimationTextView;
import com.example.pradumn.sssdashboard_2.Adapters.CounterHistoryListViewAdapter;
import com.example.pradumn.sssdashboard_2.Model.Order;
import com.example.pradumn.sssdashboard_2.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;

public class OrdersCountMonthlyFragment extends android.app.Fragment {
    private CounterHistoryListViewAdapter counterHistoryListViewAdapter;
    private ListView historyListView;
    private XAxis xAxis;
    private YAxis yAxis, yAxisRight;
    private CountAnimationTextView countAnimationTextView;
    private LineChart lineChart;
    private LineData data;
    private ArrayList<ILineDataSet> dataSets;

    public OrdersCountMonthlyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_count_monthly_2, container, false);

        // initialising view
        countAnimationTextView = (CountAnimationTextView) view.findViewById(R.id.countTextView);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);

        //setting up count textview
        countAnimationTextView.setAnimationDuration(3000).countAnimation(0, 9999);

        //setting up line chart
        ArrayList<Entry> valsComp1 = new ArrayList<>();
        ArrayList<Entry> valsComp2 = new ArrayList<>();
        Entry c1e1 = new Entry(0f, 100000f);
        Entry c1e2 = new Entry(0.2f, 140000f);
        Entry c1e3 = new Entry(0.4f, 120000f);
        Entry c1e4 = new Entry(0.6f, 140000f);
        valsComp1.add(c1e1);
        valsComp1.add(c1e2);
        valsComp1.add(c1e3);
        valsComp1.add(c1e4);
        Entry c2e1 = new Entry(0f, 130000f);
        Entry c2e2 = new Entry(0.2f, 115000f);
        Entry c2e3 = new Entry(0.4f, 90000f);
        Entry c2e4 = new Entry(0.6f, 105000f);
        valsComp2.add(c2e1);
        valsComp2.add(c2e2);
        valsComp2.add(c2e3);
        valsComp2.add(c2e4);

        LineDataSet setComp1 = new LineDataSet(valsComp1, "");
        setComp1.setColor(getResources().getColor(R.color.blue_400));
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "");
        setComp2.setColor(getResources().getColor(R.color.red_400));
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        dataSets = new ArrayList<>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        data = new LineData(dataSets);
        lineChart.setData(data);

        //setting up linechart axes and portview
        lineChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartLongPressed(MotionEvent me) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {

            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {

            }
        });
        lineChart.setAutoScaleMinMaxEnabled(true);
        lineChart.setKeepPositionOnRotation(true);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(null);
        yAxis = lineChart.getAxisLeft();
        yAxisRight = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();
        yAxis.setAxisMaximum(150000f);
        yAxisRight.setAxisMaximum(150000f);
        xAxis.setAxisMaximum(1f);
        lineChart.fitScreen();
        lineChart.invalidate();

        //setting up history listview
        historyListView = (ListView) view.findViewById(R.id.historyListView);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(201701, 100));
        orders.add(new Order(201702, 150));
        orders.add(new Order(201703, 150));
        orders.add(new Order(201704, 150));
        orders.add(new Order(201701, 100));
        orders.add(new Order(201701, 100));
        orders.add(new Order(201702, 150));
        orders.add(new Order(201703, 150));
        orders.add(new Order(201704, 150));
        orders.add(new Order(201701, 100));
        orders.add(new Order(201701, 100));
        orders.add(new Order(201702, 150));
        orders.add(new Order(201703, 150));
        orders.add(new Order(201704, 150));
        orders.add(new Order(201701, 100));
        counterHistoryListViewAdapter = new CounterHistoryListViewAdapter(getActivity(), orders);
        historyListView.setAdapter(counterHistoryListViewAdapter);
//        historyListView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                int action = motionEvent.getAction();
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN:
//                        view.getParent().requestDisallowInterceptTouchEvent(true);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        view.getParent().requestDisallowInterceptTouchEvent(false);
//                        break;
//                }
//                view.onTouchEvent(motionEvent);
//                return true;
//            }
//        });
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), i + "", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}