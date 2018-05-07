package com.onuriltan.twitteranalyzerserver.api.geolocation.model;

public class GeolocationResponse {

    private String address;
    private String woeid;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }
}
