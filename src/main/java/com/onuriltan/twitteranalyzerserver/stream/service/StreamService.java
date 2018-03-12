package com.onuriltan.twitteranalyzerserver.stream.service;

import com.onuriltan.twitteranalyzerserver.config.TwitterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamService {

    @Autowired
    TwitterConfig twitterConfig;

    public String stream(String keyword){

        twitterConfig.twitterConfiguration();
        return keyword;

    }

}
