package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeResponse;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.TokenizedTweet;


import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;
import twitter4j.Status;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class TweetAnalyzer {

    @Inject
    private SimpMessageSendingOperations webSocket;


    public void tweetForWorldMap(String sessionId, Status status, GeocodeResponse geocodeResponse) {

        try {
            Thread.sleep(1000); // simulated delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TokenizedTweet tweetForWorldMap = new TokenizedTweet();
        String tweetBody = status.getText();
        tweetForWorldMap.setTweet(tweetBody);

        if (status.getGeoLocation() != null) {

            tweetForWorldMap.setLatitude(status.getGeoLocation().getLatitude());
            tweetForWorldMap.setLongitude(status.getGeoLocation().getLongitude());

            webSocket.convertAndSendToUser(sessionId, "/queue/fetchTwitterStream", tweetForWorldMap, createHeaders(sessionId));

        } else if (geocodeResponse != null) {

            tweetForWorldMap.setLatitude(geocodeResponse.getLat());
            tweetForWorldMap.setLongitude(geocodeResponse.getLng());

            webSocket.convertAndSendToUser(sessionId, "/queue/fetchTwitterStream", tweetForWorldMap, createHeaders(sessionId));

        }
    }

    public void tweetForPanel(String sessionId, Status status) {

        try {
            Thread.sleep(1000); // simulated delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TokenizedTweet tweetForPanel = new TokenizedTweet();
        String tweetBody = status.getText();

        tweetForPanel.setUsername(status.getUser().getName());
        tweetForPanel.setTweet(tweetBody);
        tweetForPanel.setCreateDate(buildCreatedAt(status.getCreatedAt()));
        tweetForPanel.setLink("https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId());


        if (status.getUser().getLocation() != null) {
            tweetForPanel.setLocation(status.getUser().getLocation());
        }

        tweetForPanel.setForStreamPanel(true);
        webSocket.convertAndSendToUser(sessionId, "/queue/fetchTwitterStream", tweetForPanel, createHeaders(sessionId));
    }

        private void tweetForNLP () {
            // TODO: Find solution to java heap memory exception to run nlp
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
            }*/
        }


        private MessageHeaders createHeaders (String sessionId){
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(sessionId);
            headerAccessor.setLeaveMutable(true);
            return headerAccessor.getMessageHeaders();
        }

        private String buildCreatedAt (Date date){
            DateFormat dt = new SimpleDateFormat("dd MMMMMM yyyy, HH:mm:ss");
            return dt.format(date);


        }


    }
