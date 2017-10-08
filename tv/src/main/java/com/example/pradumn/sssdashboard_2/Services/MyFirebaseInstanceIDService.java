package com.example.pradumn.sssdashboard_2.Services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Pradumn on 04-Oct-17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    }
}