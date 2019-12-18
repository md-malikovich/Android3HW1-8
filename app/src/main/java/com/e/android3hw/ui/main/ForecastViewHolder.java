package com.e.android3hw.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.android3hw.R;
import com.e.android3hw.data.entity.CurrentWeather;
import com.e.android3hw.data.entity.ForecastEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    ForecastEntity forecastEntity;

    @BindView(R.id.imgForecastIcon) ImageView imgForecastIcon;
    @BindView(R.id.tvForecastDay) TextView tvForecastDay;
    @BindView(R.id.tvForecastMaxTemp)TextView tvForecastMaxTemp;
    @BindView(R.id.tvForecastMinTemp)TextView tvForecastMinTemp;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(ForecastEntity forecastEntity) {
        tvForecastDay.setText((forecastEntity.getList().get(0).getDt_txt().toString()));
        tvForecastMinTemp.setText((forecastEntity.getList().get(0).getMain().getTempMin().toString()));
        tvForecastMaxTemp.setText((forecastEntity.getList().get(0).getMain().getTempMax().toString()));
        Picasso.get().load("https://www.openweathermap.org/img/w/" + forecastEntity.getList().get(0)
                .getWeather().get(0).getIcon() + ".png").into(imgForecastIcon);
    }
}
