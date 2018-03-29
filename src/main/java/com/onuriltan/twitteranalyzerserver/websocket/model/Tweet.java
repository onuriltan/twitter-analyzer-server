package com.onuriltan.twitteranalyzerserver.websocket.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Tweet")
public class Tweet implements Serializable{

    private String tweet;

    private String word;
    private String pos;

    public Tweet(String tweet){
        this.tweet = tweet;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

}
