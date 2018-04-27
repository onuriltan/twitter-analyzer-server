package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeGenerator;
import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeResponse;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.config.spring.SpringApplicationContext;
import com.onuriltan.twitteranalyzerserver.config.twitter.TwitterConfig;
import com.onuriltan.twitteranalyzerserver.config.websocket.WebSocketSessionHandler;
import org.springframework.context.ApplicationContext;
import twitter4j.*;

import java.io.IOException;


public class BaseTwitterStream {

    Logger logger = Logger.getLogger(BaseTwitterStream.class);

    ApplicationContext context = SpringApplicationContext.getApplicationContext(); // get application context to inject beans to non managed spring class(this)

    private TweetAnalyzer tweetAnalyzer = (TweetAnalyzer) context.getBean("tweetAnalyzer");
    private GeocodeGenerator geocodeGenerator = (GeocodeGenerator) context.getBean("geocodeGenerator");
    private TwitterConfig twitterConfig = (TwitterConfig) context.getBean("twitterConfig");
    private WebSocketSessionHandler webSocketSessionHandler = (WebSocketSessionHandler) context.getBean("webSocketSessionHandler");
    private TwitterStream twitterStream = twitterStream();


    public void manageTwitterStream(StreamRequest request, String sessionId) {


        if ("start".equals(request.getCommand())) {
            logger.info("Start request: " + request.toString() + " made with session id :" + sessionId);

            StatusListener listener = new StatusListener() {
                public void onStatus(Status status) {
                    if (!status.isRetweet()) {

                        if (status.getGeoLocation() != null) {
                            tweetAnalyzer.applyNLP(sessionId, status, null);
                        }
                        if (status.getUser().getLocation() != null) {
                            GeocodeResponse geocodeResponse = geocodeGenerator.getLatLong(status.getUser().getLocation());
                            if (geocodeResponse != null) {
                                tweetAnalyzer.applyNLP(sessionId, status, geocodeResponse);
                            }
                        } else {
                            tweetAnalyzer.applyNLP(sessionId, status, null);

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
            logger.info("Stop request: " + request.toString() + " made with session id :" + sessionId);
            try {
                webSocketSessionHandler.getWebSocketSessions().get(sessionId).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            webSocketSessionHandler.getWebSocketSessions().remove(sessionId);
            twitterStream.clearListeners();
            twitterStream.shutdown();

        }

    }


    public TwitterStream twitterStream() {
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(twitterConfig.getConfiguration());
        twitter4j.TwitterStream twitterStream = twitterStreamFactory.getInstance();
        return twitterStream;

    }


}
