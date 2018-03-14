package com.onuriltan.twitteranalyzerserver.config;

import com.onuriltan.twitteranalyzerserver.api.stream.model.Tweet;
import com.onuriltan.twitteranalyzerserver.api.stream.repository.StreamRepository;
import com.onuriltan.twitteranalyzerserver.socket.TweetSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

@Configuration
public class TwitterListenerConfig implements StatusListener {

    @Autowired
    StreamRepository streamRepository;

    private Tweet tweet;

    public TwitterListenerConfig() {
        tweet = new Tweet();
    }

    @Override
    public void onStatus(Status status) {
       if(status.getLang().equals("tr") && !status.isRetweet()) {
           tweet.setText(status.getText());
           tweet.setGeoLocation(status.getGeoLocation());
           tweet.setUser(status.getUser());
           tweet.setLang(status.getLang());
           tweet.setPlace(status.getPlace());

           streamRepository.add(tweet);
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
