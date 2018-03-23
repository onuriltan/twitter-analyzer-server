package com.onuriltan.twitteranalyzerserver.websocket.model;

public class Response {

    private String tweet;

    public Response(String tweet){
        this.tweet = tweet;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
}
