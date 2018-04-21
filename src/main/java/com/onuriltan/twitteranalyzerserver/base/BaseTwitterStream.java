package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeGenerator;
import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeResponse;
import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.websocket.model.TokenizedTweet;
import com.onuriltan.twitteranalyzerserver.websocket.model.Tweet;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import twitter4j.*;

import javax.inject.Inject;
import java.util.Stack;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BaseTwitterStream {

    @Inject
    TwitterStream twitterStream;

    @Inject
    TweetAnalyzer tweetAnalyzer;

    @Inject
    GeocodeGenerator geocodeGenerator;

    public void manageTwitterStream(StreamRequest request, SimpMessageSendingOperations webSocket) {

        if ("start".equals(request.getCommand())) {
            twitterStream.clearListeners();
            TokenizedTweet mainTweet = new TokenizedTweet();

            mainTweet.setUsername("asdasd");
            mainTweet.setTweet("asdasd");
            mainTweet.setForStreamPanel(true);

            webSocket.convertAndSend("/topic/fetchTwitterStream", mainTweet);

            StatusListener listener = new StatusListener() {
                public void onStatus(Status status) {
                    System.out.println(status.getText());
                    if (!status.isRetweet()) {
                        if (status.getGeoLocation() != null) {
                            tweetAnalyzer.applyNLP(new Tweet(status.getUser().getName(), status.getText(), status.getGeoLocation().getLatitude(), status.getGeoLocation().getLongitude()));
                        } else if (status.getUser().getLocation() != null) {
                            GeocodeResponse geocodeResponse = geocodeGenerator.getLatLong(status.getUser().getLocation());
                            if (geocodeResponse != null) {
                                tweetAnalyzer.applyNLP(new Tweet(status.getUser().getName(), status.getText(), geocodeResponse.getLat(), geocodeResponse.getLng()));
                            }
                        } else {
                            tweetAnalyzer.applyNLP(new Tweet(status.getUser().getName(), status.getText(), null, null));

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
            twitterStream.clearListeners();
            twitterStream.shutdown();

        }

    }


}
