package com.e.android3hw.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.e.android3hw.R;
import com.e.android3hw.data.ForegroundService;
import com.e.android3hw.ui.base.BaseActivity;
import com.google.gson.JsonElement;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
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

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static com.e.android3hw.BuildConfig.MAPBOX_KEY;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ROTATION_ALIGNMENT_VIEWPORT;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

    protected MapView mapView;
    private MapboxMap map;
    SymbolManager symbolManager;
    private Symbol symbol;
    private TextView tvMap;

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

            map.addOnMapLongClickListener(point -> {
                createSymbol(point);
                Double lat = point.getLatitude();
                Double lng = point.getLongitude();
                Coord coord = new Coord(lat, lng);
                Intent intent = new Intent();
                intent.putExtra("LatLng", coord);
                setResult(RESULT_OK, intent);
                //Log.d("ololo", "intent");
                Log.d("ololo", coord.lat.toString());
                Log.d("ololo", coord.lng.toString());
                finish();
                return true;
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
                getResources().getDrawable(R.drawable.ic_place_black_24dp))));
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


//    button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            //NotificationHelper.createNotification(getApplicationContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
//        }
//    });
//
//    btnStart.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
//            startService(intent);
//        }
//    });
//
//    btnStop.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
//            stopService(intent);
//        }
//    });
}
//Сделать по клику на карту получение координат и следом запустить диалог "Вы Уверены " (текст на ваше усмотрение)
// если нажимает да то возвращаемся на Mainactivity и
// загружаем погоду current и forecast по координатам и соответственно меняем город на тот который вернет нам openweatgermap в json'ке
//Активити с картой открывается по клику на значок возле названия города
//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />