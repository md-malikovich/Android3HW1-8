package com.e.android3hw.ui.onboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.e.android3hw.R;
import com.e.android3hw.ui.main.MainActivity;

public class OnBoardActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        initViews();
        initListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
    }

    private void initListeners() {
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_onboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.skip:
                MainActivity.start(this);
                finish();
                break;
        }
        return true;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }
}
//Дизайн скинет преподователь из Фигмы
//Онборард - скип в верхнем правом углу (меню - ifroom)