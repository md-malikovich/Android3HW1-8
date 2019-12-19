package com.e.android3hw.ui.main;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e.android3hw.R;
import com.e.android3hw.data.entity.CurrentWeather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgForecastIcon) ImageView imgForecastIcon;
    @BindView(R.id.tvForecastDayTime) TextView tvForecastDayTime;
    @BindView(R.id.tvForecastMaxTemp)TextView tvForecastMaxTemp;
    @BindView(R.id.tvForecastMinTemp)TextView tvForecastMinTemp;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(CurrentWeather forecastEntity) {
        tvForecastDayTime.setText((forecastEntity.getDt_txt().toString()));
        //tvForecastDayTime.setText((forecastEntity.getDt().toString()));
        Calendar calendarDayForecast = Calendar.getInstance(); //TODO: позже вынести в отдельный класс!!!
        @SuppressLint("SimpleDateFormat") SimpleDateFormat day = new SimpleDateFormat("dd-MMM \n HH:MM ", Locale.ENGLISH);
        String formatted_day = day.format(calendarDayForecast.getTime());
        tvForecastDayTime.setText(formatted_day);

        tvForecastMinTemp.setText((forecastEntity.getMain().getTempMin().toString()));
        tvForecastMaxTemp.setText((forecastEntity.getMain().getTempMax().toString()));
        Picasso.get().load("https://www.openweathermap.org/img/w/" + forecastEntity.getWeather()
                .get(0).getIcon() + ".png").into(imgForecastIcon);
    }
}
