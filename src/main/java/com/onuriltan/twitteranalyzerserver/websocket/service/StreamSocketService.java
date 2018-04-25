package com.onuriltan.twitteranalyzerserver.websocket.service;

import com.onuriltan.twitteranalyzerserver.base.BaseTwitterStream;
import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Inject;
import java.util.Map;

@Service
public class StreamSocketService {

    @Inject
    BaseTwitterStream baseTwitterStream;

    public void manageStream(StreamRequest streamRequest, String sessionId){
        baseTwitterStream.manageTwitterStream(streamRequest, sessionId);
    }


}
