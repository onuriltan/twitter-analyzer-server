package com.onuriltan.twitteranalyzerserver.websocket.service;

import com.onuriltan.twitteranalyzerserver.base.BaseTwitterStream;
import com.onuriltan.twitteranalyzerserver.websocket.model.Request;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class StreamSocketService {

    @Inject
    BaseTwitterStream baseTwitterStream;

    public void startStream(Request request, SimpMessageSendingOperations webSocket){
        baseTwitterStream.manageTwitterStream(request,webSocket);
    }


}
