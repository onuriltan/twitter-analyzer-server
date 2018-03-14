package com.onuriltan.twitteranalyzerserver.api.stream.model;

import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.User;

public class Tweet {

    private String text;
    private GeoLocation geoLocation;
    private Place place;
    private User user;
    private String lang;

    public Tweet(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
