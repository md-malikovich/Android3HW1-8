package com.e.android3hw.ui.map;

import java.io.Serializable;

public class Coord implements Serializable {

    public Double lat;
    public Double lng;

    public Coord(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
