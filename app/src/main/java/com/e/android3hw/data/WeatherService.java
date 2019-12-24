package com.e.android3hw.data;

import com.e.android3hw.data.entity.CurrentWeather;
import com.e.android3hw.data.entity.ForecastEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import static com.e.android3hw.data.ApiEndpoints.CURRENT_WEATHER;
import static com.e.android3hw.data.ApiEndpoints.FORECAST_WEATHER;

public interface WeatherService {

    @GET (CURRENT_WEATHER)
    Call<CurrentWeather> currentWeather(@Query("q") String city,
                                        @Query("units") String metric,
                                        @Query("appid") String key);

    @GET(FORECAST_WEATHER)
    Call<ForecastEntity> forecastWeather (@Query("q") String city,
                                          @Query("units") String metric,
                                          @Query("appid") String key);

    @GET(FORECAST_WEATHER)
    Call<ForecastEntity> mapCoord (@Query("lat") Double lat,
                                   @Query("lon") Double lng,
                                   @Query("units") String metric,
                                   @Query("appid") String key);
}