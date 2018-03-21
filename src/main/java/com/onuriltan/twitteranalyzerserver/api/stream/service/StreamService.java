package com.onuriltan.twitteranalyzerserver.api.stream.service;

import org.springframework.stereotype.Service;
import twitter4j.TwitterStream;

import javax.inject.Inject;

@Service
public class StreamService {

    @Inject
    TwitterStream twitterStream;

    public void startStream(String keyword){
        twitterStream.filter(keyword);
    }

    public void stopStream(){
        twitterStream.shutdown();

    }
}
