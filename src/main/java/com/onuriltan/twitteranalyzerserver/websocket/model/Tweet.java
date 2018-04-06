package com.onuriltan.twitteranalyzerserver.websocket.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Tweet")
public class Tweet implements Serializable{

    private String tweet;
    private Double latitude;
    private Double longitude;

    private String word;
    private String pos;

    public Tweet(String tweet, Double latitude, Double longitude){
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

    public void setPos(String pos) {
        this.pos = pos;
    }
}
