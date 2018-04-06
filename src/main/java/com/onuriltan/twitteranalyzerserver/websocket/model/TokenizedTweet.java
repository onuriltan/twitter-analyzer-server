package com.onuriltan.twitteranalyzerserver.websocket.model;

public class TokenizedTweet {

    private String tweet;
    private String word;
    private String namedEntity;
    private Double latitude;
    private Double longitude;
    private Boolean isForStreamPanel;

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
