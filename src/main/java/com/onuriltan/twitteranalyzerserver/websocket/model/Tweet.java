package com.onuriltan.twitteranalyzerserver.websocket.model;

import java.io.Serializable;

public class Tweet implements Serializable{

    private String tweet;
    private String username;
    private Double latitude;
    private Double longitude;

    private String word;
    private String pos;

    public Tweet(String username, String tweet, Double latitude, Double longitude){
        this.username = username;
        this.tweet = tweet;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPos() {
        return pos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweet='" + tweet + '\'' +
                ", username='" + username + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", word='" + word + '\'' +
                ", pos='" + pos + '\'' +
                '}';
    }
}
