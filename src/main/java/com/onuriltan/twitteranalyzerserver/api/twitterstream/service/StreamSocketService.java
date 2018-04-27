package com.onuriltan.twitteranalyzerserver.api.twitterstream.service;

import com.onuriltan.twitteranalyzerserver.base.BaseTwitterStream;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.config.websocket.WebSocketSessionHandler;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class StreamSocketService {

    @Inject
    WebSocketSessionHandler webSocketSessionHandler;

    public void manageStream(StreamRequest streamRequest, String sessionId){

        if(!webSocketSessionHandler.getSessions().containsKey(sessionId)){
            BaseTwitterStream bt = new BaseTwitterStream();
            bt.manageTwitterStream(streamRequest, sessionId);
            webSocketSessionHandler.getSessions().put(sessionId, bt);
        }else {
            webSocketSessionHandler.getSessions().get(sessionId).manageTwitterStream(streamRequest,sessionId);
        }

    }


}
