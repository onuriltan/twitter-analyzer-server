package com.onuriltan.twitteranalyzerserver.api.twitterstream.controller;


import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.service.StreamSocketService;
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
    public void manageStream(@Payload StreamRequest streamRequest, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();

        if (streamRequest.getMessage() != null && !streamRequest.getMessage().isEmpty() && streamRequest.getMessage().length() >= 4) {
            streamSocketService.manageStream(streamRequest, sessionId);
        }

    }


}
