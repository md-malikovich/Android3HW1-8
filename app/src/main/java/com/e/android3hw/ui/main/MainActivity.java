package com.e.android3hw.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.e.android3hw.R;
import com.e.android3hw.data.RetrofitBuilder;
import com.e.android3hw.data.entity.CurrentWeather;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    private void calFormat() {
        Calendar calendarDay = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat day = new SimpleDateFormat("dd");
        String formatted_day = day.format(calendarDay.getTime());
        tvDay.setText(formatted_day);

        Calendar calendarMonth = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat month = new SimpleDateFormat("MMMM");
        String formatted_month = month.format(calendarMonth.getTime());
        tvMonth.setText(formatted_month);

        Calendar calendarYear = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat year = new SimpleDateFormat("YYYY");
        String formatted_year = year.format(calendarYear.getTime());
        tvYear.setText(formatted_year);
    }

    private void fillViews(Response<CurrentWeather> response) {
        calFormat();
        tvCity.setText(response.body().getName().toString());
        tvNow.setText("Now");
        tvToday.setText("Today");
        tvCurrentTemp.setText(response.body().getMain().getTemp().toString());
        tvTodayMaxTemp.setText(response.body().getMain().getTempMax().toString());
        tvTodayMinTemp.setText(response.body().getMain().getTempMin().toString());
        tvWeatherDesc.setText("Little cloud");
        tvMaxTemp.setText("Max");
        tvMinTemp.setText("Min");
        //textView.setText(response.body().getMain().getTempMax().toString());
        tvWind.setText("Wind");
        tvWindIndex.setText((response.body().getWind().getSpeed().toString()));
        tvHumidity.setText("Humidity");
        tvHumidityIndex.setText(response.body().getMain().getHumidity().toString());
        tvSunrise.setText("Sunrise");
        tvSunriseIndex.setText(response.body().getSys().getSunrise().toString());
        //tvSunriseIndex.setText("08:26");
        tvAirQuality.setText("Air Quality Index");
        tvAirQualityIndex.setText("N/a");
        tvPressure.setText("Pressure");
        tvPressureIndex.setText(response.body().getMain().getPressure().toString());
        tvCloudiness.setText("Cloudiness");
        tvCloudinessIndex.setText(response.body().getClouds().getAll().toString());
        tvSunset.setText("Sunset");
        tvSunsetIndex.setText(response.body().getSys().getSunset().toString());
        //tvSunsetIndex.setText("17:28");
        tvAirQuality2.setText("Air Quality");
        tvAirQualityIndex2.setText("N/a");
    }

    private void fetchWeather() { //TODO: Получение данных
        RetrofitBuilder.getService()
                .currentWeather("Bishkek","metric", getResources().getString(R.string.weather_key))
                .enqueue(new Callback<CurrentWeather>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillViews(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}