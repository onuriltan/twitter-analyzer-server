package com.onuriltan.twitteranalyzerserver.api.stream.repository;


import com.onuriltan.twitteranalyzerserver.api.stream.model.Tweet;
import com.onuriltan.twitteranalyzerserver.redis.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class StreamRepository implements RedisRepository {

    private static final String KEY = "Tweet";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public StreamRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void add(final Tweet tweet) {
        hashOperations.put(KEY, tweet.getText(), tweet.getUser());
    }

    @Override
    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    @Override

    public Tweet findTweet(final String id) {
        return (Tweet) hashOperations.get(KEY, id);
    }

    @Override
    public Map<Object, Object> findAllTweets() {
        return hashOperations.entries(KEY);
    }

}
