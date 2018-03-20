package com.onuriltan.twitteranalyzerserver.config.twitter;


import com.onuriltan.twitteranalyzerserver.socket.twitterstream.model.Tweet;
import com.onuriltan.twitteranalyzerserver.socket.twitterstream.repository.RedisRepository;
import org.springframework.context.annotation.Configuration;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import javax.inject.Inject;

@Configuration
public class TwitterListenerConfig implements StatusListener {

    @Inject
    private RedisRepository redisRepository;

    @Override
    public void onStatus(Status status) {
        Tweet tweet = new Tweet();
       if(status.getLang().equals("tr") && !status.isRetweet()) {
           tweet.setBody(status.getText());
           tweet.setLanguage(status.getLang());
           redisRepository.add(tweet);

           System.out.println(status.getLang() + " : " + status.getText());
       }

    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {

    }

    @Override
    public void onStallWarning(StallWarning warning) {

    }

    @Override
    public void onException(Exception ex) {

    }
}
