package com.onuriltan.twitteranalyzerserver.stream.requestStream.service;

import com.onuriltan.twitteranalyzerserver.config.TwitterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestStreamService {

    @Autowired
    TwitterConfig twitterConfig;

    public String stream(String keyword){
        return keyword;
    }

}
