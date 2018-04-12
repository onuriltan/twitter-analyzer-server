package com.onuriltan.twitteranalyzerserver.websocket.model;

public class TokenizedTweet {

    private String tweet;
    private String username;
    private String word;
    private String namedEntity;
    private Double latitude;
    private Double longitude;
    private Boolean isForStreamPanel;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNamedEntity() {
        return namedEntity;
    }

    public void setNamedEntity(String namedEntity) {
        this.namedEntity = namedEntity;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Boolean getForStreamPanel() {
        return isForStreamPanel;
    }

    public void setForStreamPanel(Boolean forStreamPanel) {
        isForStreamPanel = forStreamPanel;
    }
}