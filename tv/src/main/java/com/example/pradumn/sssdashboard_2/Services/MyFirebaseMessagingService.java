package com.example.pradumn.sssdashboard_2.Services;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.example.pradumn.sssdashboard_2.Activities.MainActivity;
import com.example.pradumn.sssdashboard_2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Pradumn on 04-Oct-17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Notification notification = new NotificationCompat.BigPictureStyle(new NotificationCompat.Builder(this)
                                                            .setAutoCancel(true)
                                                            .setContentTitle(remoteMessage.getData().get("data"))
                                                            .setContentText(remoteMessage.getData().get("data"))
                                                            .setPriority(Notification.PRIORITY_HIGH)
                                                            .setLocalOnly(true)
                                                            .setOngoing(true)
                                                            .setGroup("rec")
                                                            .setSortKey("rec1")
                                                            .setColor(Color.RED)
                                                            .setCategory(Notification.CATEGORY_RECOMMENDATION)
                                                            .setSmallIcon(R.drawable.app_icon_your_company)
                                                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable                                                                                       .app_icon_your_company))
                                                            .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity                                                                             .class), PendingIntent.FLAG_ONE_SHOT))
                                                             ).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(100);
        notificationManager.notify(100, notification);
        Log.d("noti", "noti rcvd");
    }
}