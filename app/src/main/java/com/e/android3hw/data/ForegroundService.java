package com.e.android3hw.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ForegroundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, NotificationHelper.createNotification(getApplicationContext(), "title", "body"));
        return START_STICKY;
    }
}
