package com.onuriltan.twitteranalyzerserver.api.twitterstream.service;

import com.onuriltan.twitteranalyzerserver.base.BaseTwitterStream;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.StreamRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class StreamSocketService {

    @Inject
    BaseTwitterStream baseTwitterStream;

    public void manageStream(StreamRequest streamRequest, String sessionId){
        baseTwitterStream.manageTwitterStream(streamRequest, sessionId);
    }


}
