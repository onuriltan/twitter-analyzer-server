package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.websocket.model.TokenizedTweet;
import com.onuriltan.twitteranalyzerserver.websocket.model.Tweet;


import edu.stanford.nlp.simple.Sentence;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Locale;
import java.util.Stack;


@Service
public class TweetAnalyzer {

    @Inject
    private SimpMessageSendingOperations webSocket;

    @Inject
    Stack<Tweet> stack;

    public void applyNLP() {

        if (!stack.empty()) {
            Tweet tweet = stack.pop();
            String tweetBody = tweet.getTweet();

            if (tweetBody != null) {

                if (tweet.getLatitude() != null || tweet.getLongitude() != null) {
                    TokenizedTweet location = new TokenizedTweet();

                    location.setTweet(tweetBody);
                    location.setLatitude(tweet.getLatitude());
                    location.setLongitude(tweet.getLongitude());

                    webSocket.convertAndSend("/topic/fetchTwitterStream", location);
                }

                TokenizedTweet mainTweet = new TokenizedTweet();

                mainTweet.setUsername(tweet.getUsername());
                mainTweet.setTweet(tweetBody);
                mainTweet.setForStreamPanel(true);

                webSocket.convertAndSend("/topic/fetchTwitterStream", mainTweet);



                Sentence sent = new Sentence(tweetBody);
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

                        webSocket.convertAndSend("/topic/fetchTwitterStream", tokenizedTweet);

                    }
                }
            }
        }
    }
}
