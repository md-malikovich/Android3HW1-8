package com.e.android3hw.ui.map;

import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.e.android3hw.R;
import com.e.android3hw.data.ForegroundService;
import com.e.android3hw.ui.base.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import java.util.Objects;
import static com.e.android3hw.BuildConfig.MAPBOX_KEY;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

    protected MapView mapView;
    private MapboxMap map;
    SymbolManager symbolManager;
    private Symbol symbol;
    private TextView tvMap;
    private Boolean isStarted = false;                                                              //TODO:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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

        FloatingActionButton fab = findViewById(R.id.fab);                                          //TODO:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        fab.setOnClickListener(view -> {
//            if (isStarted) {
                Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
                startService(intent);
//                Log.d("ololo", "Start!");
//            } else {
//                Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
//                stopService(intent);
//                Log.d("ololo", "Stop");
//            }
        });
    }

    private void initMap() {
        mapView.getMapAsync(this);
    }

    private void initViews() {
        mapView = findViewById(R.id.mapView);
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.DARK, style -> {
            map = mapboxMap;
            symbolManager = new SymbolManager(mapView, mapboxMap, style);
            symbolManager.addClickListener(symbol -> {
                //
            });

            map.addOnMapLongClickListener(point -> {
                createSymbol(point);
                AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                builder.setTitle("Hello!").setMessage("Do you want to choose this coordinates?")
                        .setPositiveButton("Yes", (dialog, which) -> {
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
                        }).setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            });
        });
    }

    private void createSymbol(LatLng latLng) {
        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(latLng)
                .withIconImage("icon");
        if (symbol != null) symbolManager.delete(symbol);
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
}