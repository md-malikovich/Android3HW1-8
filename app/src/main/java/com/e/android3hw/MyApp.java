package com.e.android3hw;

import android.app.Application;
import com.e.android3hw.data.PreferenceHelper;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.init(this);
    }
}
