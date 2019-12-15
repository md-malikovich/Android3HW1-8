package com.e.android3hw.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.e.android3hw.R;
import com.e.android3hw.data.RetrofitBuilder;
import com.e.android3hw.data.entity.CurrentWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvDay, tvMonth, tvYear, tvCity, tvNow, tvToday, tvCurrentTemp, tvTodayMaxTemp, tvTodayMinTemp, tvWeatherDesc, tvMaxTemp, tvMinTemp;
    TextView tvWind, tvWindIndex, tvHumidity, tvHumidityIndex, tvSunrise, tvSunriseIndex, tvAirQuality, tvAirQualityIndex;
    TextView tvPressure, tvPressureIndex, tvCloudiness, tvCloudinessIndex, tvSunset, tvSunsetIndex, tvAirQuality2, tvAirQualityIndex2;
    ImageView imgLocation, imgLittleCloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchWeather();
    }

    public void initViews() {
        imgLocation = findViewById(R.id.imgLocation);
        imgLittleCloud = findViewById(R.id.imgLittleCloud);
        tvDay = findViewById(R.id.tvDay);
        tvMonth = findViewById(R.id.tvMonth);
        tvYear = findViewById(R.id.tvYear);
        tvCity = findViewById(R.id.tvCity);
        tvNow = findViewById(R.id.tvNow);
        tvToday = findViewById(R.id.tvToday);
        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
        tvTodayMaxTemp = findViewById(R.id.tvTodayMaxTemp);
        tvTodayMinTemp = findViewById(R.id.tvTodayMinTemp);
        tvWeatherDesc = findViewById(R.id.tvWeatherDesc);
        tvMaxTemp = findViewById(R.id.tvMaxTemp);
        tvMinTemp = findViewById(R.id.tvMinTemp);
        tvWind = findViewById(R.id.tvWind);
        tvWindIndex = findViewById(R.id.tvWindIndex);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvHumidityIndex = findViewById(R.id.tvHumidityIndex);
        tvSunrise = findViewById(R.id.tvSunrise);
        tvSunriseIndex = findViewById(R.id.tvSunriseIndex);
        tvAirQuality = findViewById(R.id.tvAirQuality);
        tvAirQualityIndex = findViewById(R.id.tvAirQualityIndex);
        tvPressure = findViewById(R.id.tvPressure);
        tvPressureIndex = findViewById(R.id.tvPressureIndex);
        tvCloudiness = findViewById(R.id.tvCloudiness);
        tvCloudinessIndex = findViewById(R.id.tvCloudinessIndex);
        tvSunset = findViewById(R.id.tvSunset);
        tvSunsetIndex = findViewById(R.id.tvSunsetIndex);
        tvAirQuality2 = findViewById(R.id.tvAirQuality2);
        tvAirQualityIndex2 = findViewById(R.id.tvAirQualityIndex2);
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
                            //textView.setText(response.body().getMain().getTempMax().toString());
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
