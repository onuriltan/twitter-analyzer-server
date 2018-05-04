package com.onuriltan.twitteranalyzerserver.api.twitterstream.model;

public class TokenizedTweet {

    private String tweet;
    private String username;
    private String word;
    private String namedEntity;
    private Double latitude;
    private Double longitude;
    private Boolean isForStreamPanel;
    private String location;
    private String createDate;
    private String link;
    private String exception;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "TokenizedTweet{" +
                "tweet='" + tweet + '\'' +
                ", username='" + username + '\'' +
                ", word='" + word + '\'' +
                ", namedEntity='" + namedEntity + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isForStreamPanel=" + isForStreamPanel +
                ", location='" + location + '\'' +
                ", createDate='" + createDate + '\'' +
                ", link='" + link + '\'' +
                ", exception='" + exception + '\'' +
                '}';
    }
}
