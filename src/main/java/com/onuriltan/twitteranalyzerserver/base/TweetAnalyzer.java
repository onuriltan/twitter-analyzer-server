package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeResponse;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.TokenizedTweet;


import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;
import twitter4j.Logger;
import twitter4j.Status;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class TweetAnalyzer {

    @Inject
    private SimpMessageSendingOperations webSocket;

    Logger logger = Logger.getLogger(BaseTwitterStream.class);

    public void applyNLP(String sessionId, Status status, GeocodeResponse geocodeResponse) {

        try {
            Thread.sleep(1000); // simulated delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String tweetBody = status.getText();


        if (tweetBody != null) {
            TokenizedTweet location = new TokenizedTweet();
            location.setTweet(status.getText());

            if (status.getGeoLocation() != null) {

                location.setLatitude(status.getGeoLocation().getLatitude());
                location.setLongitude(status.getGeoLocation().getLongitude());

                webSocket.convertAndSendToUser(sessionId, "/queue/fetchTwitterStream", location, createHeaders(sessionId));
                logger.info("Message sent to session : " + sessionId + ", Message: " + location.toString());

            } else if (geocodeResponse != null) {

                location.setLatitude(geocodeResponse.getLat());
                location.setLongitude(geocodeResponse.getLng());

                webSocket.convertAndSendToUser(sessionId, "/queue/fetchTwitterStream", location, createHeaders(sessionId));
                logger.info("Message sent to session : " + sessionId + ", Message: " + location.toString());
                System.out.println();

            }

            TokenizedTweet tweetForPanel = new TokenizedTweet();

            tweetForPanel.setUsername(status.getUser().getName());
            tweetForPanel.setTweet(tweetBody);
            tweetForPanel.setCreateDate(buildCreatedAt(status.getCreatedAt()));
            tweetForPanel.setLink("https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());

            if (status.getPlace() != null) {
                if (status.getPlace().getCountry() != null)
                    tweetForPanel.setLocation(status.getPlace().getCountry());
                else if (status.getPlace().getFullName() != null)
                    tweetForPanel.setLocation(status.getPlace().getFullName());


            } else if (status.getUser().getLocation() != null) {
                tweetForPanel.setLocation(status.getUser().getLocation());

            }
            tweetForPanel.setForStreamPanel(true);
            webSocket.convertAndSendToUser(sessionId, "/queue/fetchTwitterStream", tweetForPanel, createHeaders(sessionId));
            logger.info("Message sent to session : " + sessionId + ", Message: " + tweetForPanel.toString());


            /*Sentence sent = new Sentence(tweetBody);
            List<String> nerTags = sent.nerTags();
            for (int i = 0; i < nerTags.size(); i++) {
                if (!nerTags.get(i).equals("O") && !nerTags.get(i).equals("MISC")) {
                    String word = sent.word(i);
                    String ner = nerTags.get(i);

                    TokenizedTweet tokenizedTweet = new TokenizedTweet();
                    tokenizedTweet.setNamedEntity(ner);

                    String cleanTweetToken = word.toString().toLowerCase(Locale.ENGLISH);
                    String cleanTweetToken1 = cleanTweetToken.substring(0, 1).toUpperCase() + cleanTweetToken.substring(1);

                    tokenizedTweet.setWord(cleanTweetToken1);

                    webSocket.convertAndSend("/queue/fetchTwitterStream", tokenizedTweet);

                }
            }*/ // TODO: Find solution to java heap memory exception to run nlp
        }

    }


    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

    private String buildCreatedAt(Date date) {
        DateFormat dt = new SimpleDateFormat("dd MMMMMM yyyy, HH:mm:ss");
        return dt.format(date);


    }


}
