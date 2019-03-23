package com.application.tuanlv.comicapp.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


import com.application.tuanlv.comicapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();

        if(remoteMessage.getData().isEmpty()) {
            showNotification(title, message);
        } else {
            showNotification(remoteMessage.getData());
        }

    }

    private void showNotification(Map<String, String> data) {
        String title = data.get("title");
        String message = data.get("message");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String CHANNEL_ID = "com.application.tuanlv.comicapp";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Comic Life");
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setSound(null, null);

            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify();
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setAutoCancel(true)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_logo_app)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setContentText(message);
        notificationManager.notify(new Random().nextInt(), builder.build());
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String CHANNEL_ID = "com.application.tuanlv.comicapp";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Comic Life");
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setSound(null, null);

            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify();
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setAutoCancel(true)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_logo_app)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setContentText(message);
        notificationManager.notify(new Random().nextInt(), builder.build());
    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
