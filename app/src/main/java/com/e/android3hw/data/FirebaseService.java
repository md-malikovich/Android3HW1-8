package com.e.android3hw.data;

import android.app.Notification;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Notification notification = NotificationHelper.createNotification(getApplicationContext(),
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody());
        NotificationHelper.showNotification(getApplicationContext(), notification);
    }
}
