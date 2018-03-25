package com.onuriltan.twitteranalyzerserver.websocket.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Tweet")
public class Tweet {

    private String tweet;

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
