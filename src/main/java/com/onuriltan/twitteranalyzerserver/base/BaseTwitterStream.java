package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.websocket.model.Tweet;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import twitter4j.*;

import javax.inject.Inject;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BaseTwitterStream {

    @Inject
    TwitterStream twitterStream;

    @Inject
    RedisTemplate redisTemplate;

    @Inject
    TweetAnalyzer tweetAnalyzer;

    public void manageTwitterStream(StreamRequest request, SimpMessageSendingOperations webSocket) {

        if("start".equals(request.getCommand())) {
            StatusListener listener = new StatusListener() {
                public void onStatus(Status status) {
                    if (status.getLang().equals("en") && !status.isRetweet()) {
                        redisTemplate.boundListOps("tweet").leftPush(new Tweet(status.getText()));

                        System.out.println(status.getText());
                        tweetAnalyzer.applyNLP();
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
        if("stop".equals(request.getCommand())){
            twitterStream.shutdown();

        }

    }


}
