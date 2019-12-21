package com.e.android3hw.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import android.os.Bundle;
import com.e.android3hw.R;
import com.e.android3hw.ui.base.BaseActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolLongClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

import java.util.Objects;

import static com.e.android3hw.BuildConfig.MAPBOX_KEY;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ROTATION_ALIGNMENT_VIEWPORT;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

    protected MapView mapView;
    private MapboxMap map;
    SymbolManager symbolManager;
    private Symbol symbol;

    private static final LatLng locationFirst = new LatLng(44.000, 55.000);
    private static final LatLng locationSecond = new LatLng(45.000, 56.000);

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
        mapboxMap.setStyle(Style.SATELLITE_STREETS, style -> {
            map = mapboxMap;
            symbolManager = new SymbolManager(mapView, mapboxMap, style);

            symbolManager.addClickListener(symbol -> {
                //
            });

            map.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                @Override
                public boolean onMapClick(@NonNull LatLng point) {
                    LatLngBounds latLngBounds = new LatLngBounds.Builder()
                            .include(locationFirst)
                            .include(locationSecond)
                            .build();
                    createSymbol(point);
                    return true;
                }
            });
        });
    }

    private void createSymbol(LatLng latLng) {
        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(latLng)
                .withIconImage("icon");
        if(symbol != null) symbolManager.delete(symbol);
        symbol = symbolManager.create(symbolOptions);

        map.getStyle().addImageAsync("icon", Objects.requireNonNull(BitmapUtils.getBitmapFromDrawable(
                getResources().getDrawable(R.drawable.ic_location))));
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
//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />