package com.onuriltan.twitteranalyzerserver.config.twitter;

import com.onuriltan.twitteranalyzerserver.config.cometd.CometdService;
import org.springframework.context.annotation.Configuration;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import javax.inject.Inject;

@Configuration
public class TwitterListenerConfig implements StatusListener {


    @Inject
    CometdService cometdService;

    @Override
    public void onStatus(Status status) {
       if(status.getLang().equals("en") && !status.isRetweet()) {
           System.out.println(status.getLang() + " : " + status.getText());
           cometdService.publishMessage(status.getText());
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
