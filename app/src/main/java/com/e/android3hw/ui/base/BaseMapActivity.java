package com.e.android3hw.ui.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.e.android3hw.R;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class BaseMapActivity { //extends BaseActivity implements OnMapReadyCallback

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        initViews();
//        initMap();
//        mapView.onCreate(savedInstanceState);
//        super.onCreate(savedInstanceState);
//    }
//
//    protected MapView mapView;
//    private MapboxMap map;
//
//    @Override
//    protected int getLayoutId() {
//        return 0;
//    }
//
//    private void initMap() {
//        mapView.getMapAsync(this);
//    }
//
//    private void initViews() {
//        mapView = findViewById(R.id.mapView);
//    }
//
//    @Override
//    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
//        mapboxMap.setStyle(Style.DARK, new Style.OnStyleLoaded() {
//            @Override
//            public void onStyleLoaded(@NonNull Style style) {
//                map = mapboxMap;
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
}
