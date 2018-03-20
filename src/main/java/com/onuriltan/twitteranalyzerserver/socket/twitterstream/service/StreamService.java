package com.onuriltan.twitteranalyzerserver.socket.twitterstream.service;

import com.onuriltan.twitteranalyzerserver.socket.twitterstream.model.KeyWord;
import org.springframework.stereotype.Service;
import twitter4j.TwitterStream;

import javax.inject.Inject;

@Service
public class StreamService {

    @Inject
    TwitterStream twitterStream;

    public void startStream(KeyWord keyWord){
        twitterStream.filter(keyWord.getKeyWord());
    }
}
