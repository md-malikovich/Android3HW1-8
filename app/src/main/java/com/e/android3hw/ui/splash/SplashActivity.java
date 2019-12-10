package com.e.android3hw.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.e.android3hw.R;
import com.e.android3hw.ui.main.MainActivity;
import com.e.android3hw.ui.onboard.OnBoardActivity;

public class SplashActivity extends AppCompatActivity {

    private Boolean isShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        selectActivity();
    }

    private void selectActivity() {
        SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
        isShown = preferences.getBoolean("isFirstLaunch", false);

        if(isShown) {
            MainActivity.start(this);
        } else {
            preferences.edit().putBoolean("isFirstLaunch", true).apply();
            OnBoardActivity.start(this);
        }
    }
}
