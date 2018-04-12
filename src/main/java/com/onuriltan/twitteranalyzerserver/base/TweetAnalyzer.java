package com.onuriltan.twitteranalyzerserver.base;

import com.onuriltan.twitteranalyzerserver.websocket.model.TokenizedTweet;
import com.onuriltan.twitteranalyzerserver.websocket.model.Tweet;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;

import edu.stanford.nlp.util.CoreMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Locale;


@Service
public class TweetAnalyzer {

    @Inject
    private SimpMessageSendingOperations webSocket;

    @Inject
    RedisTemplate redisTemplate;

    @Inject
    StanfordCoreNLP stanfordCoreNLP;

    public void applyNLP() {

        Tweet tweet = (Tweet) redisTemplate.boundListOps("tweet").leftPop();
        String text = tweet.getTweet();
        text = text.toLowerCase(Locale.ENGLISH);
        text = text.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");
        text = text.replaceAll("\\p{Punct}+", " ");
        text = text.trim().replaceAll(" +", " ");// eliminate double spaces, special words, http and hashtags

        String cleanTweetBody = text.toString().toLowerCase(Locale.ENGLISH);
        String cleansTweetBody = cleanTweetBody.substring(0, 1).toUpperCase() + cleanTweetBody.substring(1);


        if(cleansTweetBody != null) {

            Annotation document = new Annotation(cleansTweetBody);

            if (tweet.getLatitude() != null || tweet.getLongitude() != null) {
                TokenizedTweet location = new TokenizedTweet();

                location.setTweet(cleansTweetBody);
                location.setLatitude(tweet.getLatitude());
                location.setLongitude(tweet.getLongitude());

                webSocket.convertAndSend("/topic/fetchTwitterStream", location);
            }

            TokenizedTweet mainTweet = new TokenizedTweet();

            mainTweet.setUsername(tweet.getUsername());
            mainTweet.setTweet(tweet.getTweet().toString());
            mainTweet.setForStreamPanel(true);

            webSocket.convertAndSend("/topic/fetchTwitterStream", mainTweet);

            String serializedClassifier = "classifiers/ner-eng-ie.crf-3-all2008.ser.gz";


            // run all Annotators on this text
            stanfordCoreNLP.annotate(document);

            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

            for (CoreMap sentence : sentences) {
                // traversing the words in the current sentence
                // a CoreLabel is a CoreMap with additional token-specific methods
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    // this is the text of the token
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    // this is the NER label of the token
                    String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                    if (!ne.equals("O") && !ne.equals("MISC")) {
                        TokenizedTweet tokenizedTweet = new TokenizedTweet();
                        tokenizedTweet.setWord(word);
                        tokenizedTweet.setNamedEntity(ne);
                        System.out.println(String.format("word: [%s] ne: [%s]", word, ne));
                        webSocket.convertAndSend("/topic/fetchTwitterStream", tokenizedTweet);

                    }

                }

            }
        }
    }
}
