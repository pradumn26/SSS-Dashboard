package com.example.pradumn.sssdashboard_2.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.pradumn.sssdashboard_2.Adapters.FragmentExpandableListAdapter;
import com.example.pradumn.sssdashboard_2.Adapters.FragmentListViewAdapter;
import com.example.pradumn.sssdashboard_2.Fragments.HealthCheckFragment;
import com.example.pradumn.sssdashboard_2.Fragments.OrdersCountFragment;
import com.example.pradumn.sssdashboard_2.Fragments.OrdersCountMonthlyFragment;
import com.example.pradumn.sssdashboard_2.Fragments.OrdersDispatchedFragment;
import com.example.pradumn.sssdashboard_2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skyfishjy.library.RippleBackground;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    public boolean emailRippleAnimation;
    public boolean ordersRippleAnimation;
    public boolean smsRippleAnimation;
    public boolean websiteRippleAnimation;
    AssetManager assets;
    AssetFileDescriptor assetFileDescriptor;
    MediaPlayer mediaPlayer;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference emailReference = rootReference.child("email-health");
    DatabaseReference ordersReference = rootReference.child("orders-health");
    DatabaseReference smsReference = rootReference.child("sms-health");
    DatabaseReference websiteReference = rootReference.child("website-health");
    HealthCheckFragment healthCheckFragment;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TabLayout tabLayout;
    private ListView fragmentNamesListView;
    private FragmentListViewAdapter fragmentListViewAdapter;
    private ArrayList<String> fragmentNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up ripple animations state
        emailRippleAnimation = false;
        ordersRippleAnimation = false;
        smsRippleAnimation = false;
        websiteRippleAnimation = false;

        //Setting up assets for media player
        assets = getAssets();
        try {
            assetFileDescriptor = assets.openFd("notification_tone.mp3");
            mediaPlayer = new MediaPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //setting up fragments array
        fragments = new Fragment[4];
        healthCheckFragment = new HealthCheckFragment();
        fragments[0] = healthCheckFragment;
        fragments[1] = new OrdersCountFragment();
        fragments[2] = new OrdersCountMonthlyFragment();
        fragments[3] = new OrdersDispatchedFragment();

        //setting up fragment manager
        fragmentManager = getFragmentManager();

        //setting up tab layout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getStringArray(R.array.orders_count_types)[0]));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getStringArray(R.array.orders_count_types)[1]));
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals(getResources().getStringArray(R.array.orders_count_types)[0])) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragments[1]);
                    fragmentTransaction.commit();
                } else if (tab.getText().equals(getResources().getStringArray(R.array.orders_count_types)[1])) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragments[2]);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //setting up listview
        fragmentNames = new ArrayList<>();
        for (String i : getResources().getStringArray(R.array.fragment_names))
            fragmentNames.add(i);
        fragmentNamesListView = (ListView) findViewById(R.id.fragment_listView);
        fragmentListViewAdapter = new FragmentListViewAdapter(MainActivity.this, fragmentNames, tabLayout, fragments);
        fragmentNamesListView.setAdapter(fragmentListViewAdapter);
        fragmentNamesListView.setDivider(null);
    }

    private void alert() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        emailReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean email_health = dataSnapshot.getValue(Boolean.class);
                if (!email_health) {
                    if (healthCheckFragment != null && healthCheckFragment.emailRippleBackground != null) {
                        healthCheckFragment.emailImageView.setImageResource(R.drawable.cancel);
                        healthCheckFragment.emailRippleBackground.startRippleAnimation();
                    }
                    emailRippleAnimation = true;
                    alert();
                } else {
                    if (healthCheckFragment != null && healthCheckFragment.emailRippleBackground != null) {
                        healthCheckFragment.emailImageView.setImageResource(R.drawable.check);
                        healthCheckFragment.emailRippleBackground.stopRippleAnimation();
                    }
                    emailRippleAnimation = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ordersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean orders_health = dataSnapshot.getValue(Boolean.class);
                if (!orders_health) {
                    if (healthCheckFragment != null && healthCheckFragment.ordersRippleBackground != null) {
                        healthCheckFragment.ordersImageView.setImageResource(R.drawable.cancel);
                        healthCheckFragment.ordersRippleBackground.startRippleAnimation();
                    }
                    ordersRippleAnimation = true;
                    alert();
                } else {
                    if (healthCheckFragment != null && healthCheckFragment.ordersRippleBackground != null) {
                        healthCheckFragment.ordersImageView.setImageResource(R.drawable.check);
                        healthCheckFragment.ordersRippleBackground.stopRippleAnimation();
                    }
                    ordersRippleAnimation = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        smsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean sms_health = dataSnapshot.getValue(Boolean.class);
                if (!sms_health) {
                    if (healthCheckFragment != null && healthCheckFragment.smsRippleBackground != null) {
                        healthCheckFragment.smsImageView.setImageResource(R.drawable.cancel);
                        healthCheckFragment.smsRippleBackground.startRippleAnimation();
                    }
                    smsRippleAnimation = true;
                    alert();
                } else {
                    if (healthCheckFragment != null && healthCheckFragment.smsRippleBackground != null) {
                        healthCheckFragment.smsImageView.setImageResource(R.drawable.check);
                        healthCheckFragment.smsRippleBackground.stopRippleAnimation();
                    }
                    smsRippleAnimation = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        websiteReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean website_health = dataSnapshot.getValue(Boolean.class);
                if (!website_health) {
                    if (healthCheckFragment != null && healthCheckFragment.websiteRippleBackground != null) {
                        healthCheckFragment.websiteImageView.setImageResource(R.drawable.cancel);
                        healthCheckFragment.websiteRippleBackground.startRippleAnimation();
                    }
                    websiteRippleAnimation = true;
                    alert();
                } else {
                    if (healthCheckFragment != null && healthCheckFragment.websiteRippleBackground != null) {
                        healthCheckFragment.websiteImageView.setImageResource(R.drawable.check);
                        healthCheckFragment.websiteRippleBackground.stopRippleAnimation();
                    }
                    websiteRippleAnimation = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}