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
            if(!webSocketSessionHandler.getSessionRequests().containsKey(sessionId)){
                if(streamRequest.getCommand().equals("start")){
                    BaseTwitterStream bt = new BaseTwitterStream();
                    bt.manageTwitterStream(streamRequest, sessionId);
                    webSocketSessionHandler.getSessions().put(sessionId, bt);
                    webSocketSessionHandler.getSessionRequests().put(sessionId, streamRequest);
                }
            }

        }else {
            if(!webSocketSessionHandler.getSessionRequests().get(sessionId).equals(streamRequest)){
                webSocketSessionHandler.getSessions().get(sessionId).manageTwitterStream(streamRequest,sessionId);
            }

        }

    }


}
