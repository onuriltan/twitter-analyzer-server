package com.onuriltan.twitteranalyzerserver.websocket.controller;


import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.websocket.service.StreamSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class StreamSocketController {

    @Inject
    private StreamSocketService streamSocketService;


    @MessageMapping("/manageTwitterStream")
    @SendToUser(destinations="/queue/fetchTwitterStream", broadcast=false)
    public void manageStream(@Payload StreamRequest streamRequest, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
        Thread.sleep(1000); // simulated delay
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        String sessionId = headerAccessor.getSessionId();
        streamSocketService.manageStream(streamRequest, sessionId);
    }

}
