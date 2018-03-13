package com.onuriltan.twitteranalyzerserver.stream.startStream.service;

import com.onuriltan.twitteranalyzerserver.config.TwitterConfig;
import com.onuriltan.twitteranalyzerserver.stream.startStream.model.StartStreamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

@Service
public class StartStreamService {

    @Autowired
    TwitterConfig twitterConfig;

    public String stream(String keyword){

        startTwitterStream(keyword);
        return keyword;

    }

    private StartStreamModel startTwitterStream(String keyword){

        StartStreamModel startStreamModel = new StartStreamModel();

        StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
                startStreamModel.setText(status.getText());
                startStreamModel.setGeoLocation(status.getGeoLocation());
                startStreamModel.setUser(status.getUser());
                startStreamModel.setLang(status.getLang());
                startStreamModel.setPlace(status.getPlace());
                System.out.println(status.getUser().getName() + " : " + status.getText());
            }
            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            @Override
            public void onScrubGeo(long userId, long upToStatusId) {}
            @Override
            public void onStallWarning(StallWarning warning) {}
            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(twitterConfig.getConfigurationBuilder().build());
        TwitterStream  twitterStream = twitterStreamFactory.getInstance();
        twitterStream.addListener(listener);
        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
        twitterStream.sample();

        return startStreamModel;
    }



}
