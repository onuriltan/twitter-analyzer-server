package com.onuriltan.twitteranalyzerserver.api.stream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterStream;

@Service
public class StreamService {

    @Autowired
    TwitterStream twitterStream;

    public String startTwitterStream(String keyword){
        twitterStream.filter(keyword);
        twitterStream.sample();
        return "Stream started";
    }

    public String stopTwitterStream(){
        twitterStream.shutdown();
        return "Stream stopped";

    }

}
