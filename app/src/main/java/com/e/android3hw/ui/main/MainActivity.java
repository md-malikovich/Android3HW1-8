package com.e.android3hw.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.e.android3hw.R;
import com.e.android3hw.data.RetrofitBuilder;
import com.e.android3hw.data.entity.CurrentWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchWeather();
    }

    public void initViews() {
        textView = findViewById(R.id.textView_main);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void fetchWeather() { //TODO: Получение данных
        RetrofitBuilder.getService()
                .currentWeather("Bishkek","metric", getResources().getString(R.string.weather_key))
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            textView.setText(response.body().getMain().getTempMax().toString());
                            //Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
