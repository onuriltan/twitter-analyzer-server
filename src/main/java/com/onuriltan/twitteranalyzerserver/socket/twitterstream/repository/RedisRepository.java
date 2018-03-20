package com.onuriltan.twitteranalyzerserver.socket.twitterstream.repository;

import com.onuriltan.twitteranalyzerserver.socket.twitterstream.model.Tweet;

import java.util.Map;

public interface RedisRepository {
    Map<Object, Object> findAllMovies();
    void add(Tweet tweet);
    void delete(String id);
    Tweet findMovie(String id);
}
