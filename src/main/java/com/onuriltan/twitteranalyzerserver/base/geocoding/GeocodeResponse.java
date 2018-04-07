package com.onuriltan.twitteranalyzerserver.base.geocoding;

public class GeocodeResponse {

    private double lat;
    private double lng;

    public GeocodeResponse(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
