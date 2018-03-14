package com.onuriltan.twitteranalyzerserver.redis;

import com.onuriltan.twitteranalyzerserver.api.stream.model.Tweet;

import java.util.Map;

public interface RedisRepository {

    /**
     * Return all tweets
     */
    Map<Object, Object> findAllTweets();

    /**
     * Add key-value pair to Redis.
     */
    void add(Tweet tweet);

    /**
     * Delete a key-value pair in Redis.
     */
    void delete(String id);

    /**
     * find a tweet
     */
    Tweet findTweet(String id);

}
