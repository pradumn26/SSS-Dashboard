package com.example.pradumn.sssdashboard_2.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.pradumn.sssdashboard_2.Activities.MainActivity;
import com.example.pradumn.sssdashboard_2.R;
import com.skyfishjy.library.RippleBackground;

public class HealthCheckFragment extends android.app.Fragment {
    public ImageView websiteImageView, smsImageView, emailImageView, ordersImageView;
    public RippleBackground websiteRippleBackground, smsRippleBackground, emailRippleBackground, ordersRippleBackground;

    public HealthCheckFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_check, container, false);

        //Setting up views
        websiteImageView = (ImageView) view.findViewById(R.id.website_imageview);
        websiteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                websiteRippleBackground.stopRippleAnimation();
            }
        });
        smsImageView = (ImageView) view.findViewById(R.id.sms_imageview);
        smsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smsRippleBackground.stopRippleAnimation();
            }
        });
        emailImageView = (ImageView) view.findViewById(R.id.email_imageview);
        emailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailRippleBackground.stopRippleAnimation();
            }
        });
        ordersImageView = (ImageView) view.findViewById(R.id.order_imageview);
        ordersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordersRippleBackground.stopRippleAnimation();
            }
        });

        websiteRippleBackground = (RippleBackground) view.findViewById(R.id.website_pulse);
        if(((MainActivity) getActivity()).websiteRippleAnimation) {
            websiteImageView.setImageResource(R.drawable.cancel);
            websiteRippleBackground.startRippleAnimation();
        } else
            websiteImageView.setImageResource(R.drawable.check);
        smsRippleBackground = (RippleBackground) view.findViewById(R.id.sms_pulse);
        if(((MainActivity) getActivity()).smsRippleAnimation) {
            smsImageView.setImageResource(R.drawable.cancel);
            smsRippleBackground.startRippleAnimation();
        } else
            smsImageView.setImageResource(R.drawable.check);
        emailRippleBackground = (RippleBackground) view.findViewById(R.id.email_pulse);
        if(((MainActivity) getActivity()).emailRippleAnimation) {
            emailImageView.setImageResource(R.drawable.cancel);
            emailRippleBackground.startRippleAnimation();
        } else
            emailImageView.setImageResource(R.drawable.check);
        ordersRippleBackground = (RippleBackground) view.findViewById(R.id.order_pulse);
        if (((MainActivity) getActivity()).ordersRippleAnimation) {
            ordersImageView.setImageResource(R.drawable.cancel);
            ordersRippleBackground.startRippleAnimation();
        } else
            ordersImageView.setImageResource(R.drawable.check);

        return view;
    }
}