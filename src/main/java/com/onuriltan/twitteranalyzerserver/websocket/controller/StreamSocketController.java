package com.onuriltan.twitteranalyzerserver.websocket.controller;


import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.websocket.service.StreamSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
public class StreamSocketController {

    @Inject
    private StreamSocketService streamSocketService;

    @MessageMapping("/manageTwitterStream")
    public void manageStream(@Payload StreamRequest streamRequest, SimpMessageHeaderAccessor headerAccessor){
        String sessionId = headerAccessor.getSessionId();
        streamSocketService.manageStream(streamRequest, sessionId);
    }

}
