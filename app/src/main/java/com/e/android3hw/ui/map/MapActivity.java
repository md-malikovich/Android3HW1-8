package com.e.android3hw.ui.map;

import androidx.annotation.NonNull;
import android.os.Bundle;
import com.e.android3hw.R;
import com.e.android3hw.ui.base.BaseActivity;
import com.e.android3hw.ui.main.MainActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.sources.VectorSource;

import static com.e.android3hw.BuildConfig.MAPBOX_KEY;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

    protected MapView mapView;
    private MapboxMap map;

    @Override
    protected int getLayoutId() {
        Mapbox.getInstance(this, MAPBOX_KEY);
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initMap();
        mapView.onCreate(savedInstanceState);
    }

    private void initMap() {
        mapView.getMapAsync(this);
    }

    private void initViews() {
        mapView = findViewById(R.id.mapView);
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.DARK, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                map = mapboxMap;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}


//Сделать по клику на карту получение координат и следом запустить диалог "Вы Уверены " (текст на ваше усмотрение)
// если нажимает да то возвращаемся на Mainactivity и
// загружаем погоду current и forecast по координатам и соответственно меняем город на тот который вернет нам openweatgermap в json'ке
//Активити с картой открывается по клику на значок возле названия города