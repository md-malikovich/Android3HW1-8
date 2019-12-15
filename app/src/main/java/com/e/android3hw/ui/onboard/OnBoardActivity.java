package com.e.android3hw.ui.onboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.e.android3hw.R;
import com.e.android3hw.data.entity.OnBoardEntity;
import com.e.android3hw.ui.main.MainActivity;
import java.util.ArrayList;

public class OnBoardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private OnBoardAdapter adapter;
    private Button btnNext;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        initViews();
        initListeners();
        setupViewPager();
    }

    private void initViews() {
        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);
    }

    private void initListeners() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });
    }

    private void setupViewPager(){
        adapter = new OnBoardAdapter(getResource());
        viewPager.setAdapter(adapter);
    }

    private ArrayList<OnBoardEntity> getResource(){
        ArrayList<OnBoardEntity> list = new ArrayList<>();
        list.add(new OnBoardEntity( "Rainy",R.drawable.weather11));
        list.add(new OnBoardEntity( "Sunny",R.drawable.sun2));
        list.add(new OnBoardEntity( "Cloudy",R.drawable.weather11));
        list.add(new OnBoardEntity( "Snowy",R.drawable.snow2));
        return list;
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
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out); //TODO: anim
                finish();
                break;
        }
        return true;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }
}