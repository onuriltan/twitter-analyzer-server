package com.onuriltan.twitteranalyzerserver.websocket.service;

import com.onuriltan.twitteranalyzerserver.base.BaseTwitterStream;
import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class StreamSocketService {

    @Inject
    BaseTwitterStream baseTwitterStream;

    public void startStream(StreamRequest streamRequest, SimpMessageSendingOperations webSocket){
        baseTwitterStream.manageTwitterStream(streamRequest,webSocket);
    }


}
