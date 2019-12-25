package com.e.android3hw.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.e.android3hw.R;
import com.e.android3hw.data.RetrofitBuilder;
import com.e.android3hw.data.entity.CurrentWeather;
import com.e.android3hw.data.entity.ForecastEntity;
import com.e.android3hw.ui.base.BaseActivity;
import com.e.android3hw.ui.map.Coord;
import com.e.android3hw.ui.map.MapActivity;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.e.android3hw.BuildConfig.API_KEY;

public class MainActivity extends BaseActivity {

    public static final int REQUEST_CODE = 444;
    public Double lat;
    public Double lng;

    @BindView(R.id.tvDay) TextView tvDay;
    @BindView(R.id.tvMonth) TextView tvMonth;
    @BindView(R.id.tvYear) TextView tvYear;
    @BindView(R.id.tvCity) TextView tvCity;
    @BindView(R.id.tvNow) TextView tvNow;
    @BindView(R.id.tvToday) TextView tvToday;
    @BindView(R.id.tvCurrentTemp) TextView tvCurrentTemp;
    @BindView(R.id.tvTodayMaxTemp) TextView tvTodayMaxTemp;
    @BindView(R.id.tvTodayMinTemp) TextView tvTodayMinTemp;
    @BindView(R.id.tvWeatherDesc) TextView tvWeatherDesc;
    @BindView(R.id.tvMaxTemp) TextView tvMaxTemp;
    @BindView(R.id.tvMinTemp) TextView tvMinTemp;
    @BindView(R.id.tvWind) TextView tvWind;
    @BindView(R.id.tvWindIndex) TextView tvWindIndex;
    @BindView(R.id.tvHumidity) TextView tvHumidity;
    @BindView(R.id.tvHumidityIndex) TextView tvHumidityIndex;
    @BindView(R.id.tvSunrise) TextView tvSunrise;
    @BindView(R.id.tvSunriseIndex) TextView tvSunriseIndex;
    @BindView(R.id.tvPressure) TextView tvPressure;
    @BindView(R.id.tvPressureIndex) TextView tvPressureIndex;
    @BindView(R.id.tvCloudiness) TextView tvCloudiness;
    @BindView(R.id.tvCloudinessIndex) TextView tvCloudinessIndex;
    @BindView(R.id.tvSunset) TextView tvSunset;
    @BindView(R.id.tvSunsetIndex) TextView tvSunsetIndex;
    @BindView(R.id.imgLocation) ImageView imgLocation;
    @BindView(R.id.imgLittleCloud) ImageView imgLittleCloud;
    @BindView(R.id.imgSend) ImageView imgSend;
    //@BindView(R.id.imgLittleCloud) static ImageView imgLittleCloud;
    //private static ImageView imgLittleCloud;

    private RecyclerView recyclerView;
    private ForecastAdapter adapter;
    private ProgressBar progressBar;
    private boolean isProgress = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchCurrentWeather();
        fetchForecastWeather();

        initRecyclerView();
        initForecastAdapter();
        initViews();
    }

    public void initViews() {
        progressBar = findViewById(R.id.progressBar);
    }

    protected void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgress() {
        if (isProgress) {
            progressBar.setVisibility(View.GONE);
            isProgress = false;
        } else {
            isProgress = true;
        }
        progressBar.setVisibility(View.GONE);
    }

    public void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    public void initForecastAdapter() {
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void showForecastWeather(List<CurrentWeather> weather) {
        adapter.updateWeather(weather);
    }

    @OnClick(R.id.imgLittleCloud)
    public void onClickUpdate(View view) {
        fetchCurrentWeather();
        fetchForecastWeather();
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
        @SuppressLint("SimpleDateFormat") SimpleDateFormat month = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        String formatted_month = month.format(calendarMonth.getTime());
        tvMonth.setText(formatted_month);

        Calendar calendarYear = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat year = new SimpleDateFormat("YYYY");
        String formatted_year = year.format(calendarYear.getTime());
        tvYear.setText(formatted_year);
    }

    public String parseDateToTime(double time) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date();
        date.setTime((long) time * 1000);
        return dateFormat.format(date.getTime());
    }

    private void fillViews(CurrentWeather response) { //TODO: вынести все в strings - tvWindIndex.setText(getString((R.string.wind_speed), 7));
        calFormat();
        tvCity.setText(response.getName());
        tvNow.setText(("Now"));
        tvToday.setText(("Today"));
        tvCurrentTemp.setText((response.getMain().getTemp().toString() + "º"));
        tvTodayMaxTemp.setText((response.getMain().getTempMax().toString() + "º"));
        tvTodayMinTemp.setText((response.getMain().getTempMin().toString() + "º"));
        tvWeatherDesc.setText((response.getWeather().get(0).getDescription()));
        tvMaxTemp.setText(("Max"));
        tvMinTemp.setText(("Min"));
        tvWind.setText(("Wind"));
        tvWindIndex.setText((response.getWind().getSpeed().toString() + " m/s"));
        tvHumidity.setText(("Humidity"));
        tvHumidityIndex.setText((response.getMain().getHumidity().toString() + "%"));
        tvSunrise.setText(("Sunrise"));
        tvSunriseIndex.setText(parseDateToTime(response.getSys().getSunrise()));
        tvPressure.setText(("Pressure"));
        tvPressureIndex.setText((response.getMain().getPressure().toString() + " mb"));
        tvCloudiness.setText(("Cloudiness"));
        tvCloudinessIndex.setText((response.getClouds().getAll().toString() + "%"));
        tvSunset.setText(("Sunset"));
        tvSunsetIndex.setText(parseDateToTime(response.getSys().getSunset()));
        Picasso.get().load("https://www.openweathermap.org/img/wn/" + response.getWeather()
                .get(0).getIcon() + ".png").into(imgLittleCloud);
    }

    private void fetchCurrentWeather() {
        RetrofitBuilder.getService()
                .currentWeather("Bishkek", "metric", API_KEY)
                .enqueue(new Callback<CurrentWeather>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillViews(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        toast(t.getLocalizedMessage());
                    }
                });
    }

    private void fetchForecastWeather() {
        RetrofitBuilder.getService()
                .forecastWeather("Bishkek", "metric", API_KEY)
                .enqueue(new Callback<ForecastEntity>() {
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            showForecastWeather(response.body().getList());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        toast(t.getLocalizedMessage());
                    }
                });
    }

    private void fetchWeatherByCoord(Double lat, Double lng) {
        calFormat();
        RetrofitBuilder.getService()
                .mapCoord(lat, lng, "metric", API_KEY)
                .enqueue(new Callback<ForecastEntity>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            showForecastWeather(response.body().getList());
                            calFormat();
                            //tvCity.setText(response.body().getList().get(0).getName());
                            tvNow.setText(("Now"));
                            tvToday.setText(("Today"));
                            tvCurrentTemp.setText((response.body().list.get(0).getMain().getTemp().toString() + "º"));
                            tvTodayMaxTemp.setText((response.body().list.get(0).getMain().getTempMax().toString() + "º"));
                            tvTodayMinTemp.setText((response.body().list.get(0).getMain().getTempMin().toString() + "º"));
                            tvWeatherDesc.setText((response.body().list.get(0).getWeather().get(0).getDescription().toString()));
                            tvMaxTemp.setText(("Max"));
                            tvMinTemp.setText(("Min"));
                            tvWind.setText(("Wind"));
                            tvWindIndex.setText((response.body().list.get(0).getWind().getSpeed().toString() + " m/s"));
                            tvHumidity.setText(("Humidity"));
                            tvHumidityIndex.setText((response.body().list.get(0).getMain().getHumidity().toString() + "%"));
                            tvSunrise.setText(("Sunrise"));
                            //tvSunriseIndex.setText(parseDateToTime(response.body().list.get(0).getSys().getSunrise()));
                            tvPressure.setText(("Pressure"));
                            tvPressureIndex.setText((response.body().list.get(0).getMain().getPressure().toString() + " mb"));
                            tvCloudiness.setText(("Cloudiness"));
                            tvCloudinessIndex.setText((response.body().list.get(0).getClouds().getAll().toString() + "%"));
                            tvSunset.setText(("Sunset"));
                            //tvSunsetIndex.setText(parseDateToTime(response.body().list.get(0).getSys().getSunset()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        toast(t.getLocalizedMessage());
                    }
                });
    }

    public void openMapActivity(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Coord coord = (Coord) data.getSerializableExtra("LatLng");
                Double lat = coord.getLat();
                Double lng = coord.getLng();
                fetchWeatherByCoord(lat, lng);
                
            }
        }
    }

    public void onClickSendNotification(View view) {
        imgSend.setOnClickListener(v -> {
            //NotificationHelper.createNotification(getApplicationContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            Log.d("ololo", "Send notification");
        });
    }
}

