package com.e.android3hw.ui.onboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.e.android3hw.R;
import com.e.android3hw.ui.main.MainActivity;

public class OnBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }
}
