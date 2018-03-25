package com.onuriltan.twitteranalyzerserver.websocket.repository;

import com.onuriltan.twitteranalyzerserver.websocket.model.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, String> {}
