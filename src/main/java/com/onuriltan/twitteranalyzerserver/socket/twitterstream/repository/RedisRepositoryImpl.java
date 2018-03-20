package com.onuriltan.twitteranalyzerserver.socket.twitterstream.repository;

import com.onuriltan.twitteranalyzerserver.socket.twitterstream.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private static final String KEY = "Tweet";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
    public void add(final Tweet tweet) {
        hashOperations.put(KEY, tweet, tweet);
    }
    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }
    public Tweet findMovie(final String id){
        return (Tweet) hashOperations.get(KEY, id);
    }
    public Map<Object, Object> findAllMovies(){
        return hashOperations.entries(KEY);
    }
}
