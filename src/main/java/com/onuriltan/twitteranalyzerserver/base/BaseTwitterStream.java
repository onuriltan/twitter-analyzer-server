package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeGenerator;
import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeResponse;
import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import org.springframework.stereotype.Service;
import twitter4j.*;
import javax.inject.Inject;


@Service
public class BaseTwitterStream {

    Logger logger = Logger.getLogger(BaseTwitterStream.class);

    @Inject
    TwitterStream twitterStream;

    @Inject
    TweetAnalyzer tweetAnalyzer;

    @Inject
    GeocodeGenerator geocodeGenerator;

    public void manageTwitterStream(StreamRequest request, String sessionId) {

        if ("start".equals(request.getCommand())) {
            logger.info("Start request: "+request.toString()+" made with session id :"+sessionId);

            StatusListener listener = new StatusListener() {
                public void onStatus(Status status) {
                    if (!status.isRetweet()) {

                        if (status.getGeoLocation() != null) {
                            tweetAnalyzer.applyNLP(sessionId,status,null);
                        }
                        if (status.getUser().getLocation() != null) {
                            GeocodeResponse geocodeResponse = geocodeGenerator.getLatLong(status.getUser().getLocation());
                            if (geocodeResponse != null) {
                                tweetAnalyzer.applyNLP(sessionId,status,geocodeResponse);
                            }
                        } else {
                            tweetAnalyzer.applyNLP(sessionId,status,null);

                        }

                    }
                }

                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                }

                public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                }

                public void onScrubGeo(long userId, long upToStatusId) {
                }

                public void onStallWarning(StallWarning warning) {
                }

                public void onException(Exception ex) {
                    ex.printStackTrace();
                }
            };
            twitterStream.addListener(listener);
            twitterStream.filter(request.getMessage());
        }
        if ("stop".equals(request.getCommand())) {
            logger.info("Stop request: "+request.toString()+" made with session id :"+sessionId);
            twitterStream.clearListeners();
            twitterStream.shutdown();

        }

    }


}
