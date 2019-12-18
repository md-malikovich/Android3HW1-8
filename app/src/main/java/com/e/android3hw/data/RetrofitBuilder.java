package com.e.android3hw.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.e.android3hw.BuildConfig.BASE_URL;

public class RetrofitBuilder {

    private static WeatherService weatherService;

    public static WeatherService getService() {
        if(weatherService == null) {
            weatherService = buildRetrofit();
        }
        return weatherService;
    }

    private static WeatherService buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);
    }
}
