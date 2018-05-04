package com.onuriltan.twitteranalyzerserver.api.twitterstream.controller;


import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.model.TokenizedTweet;
import com.onuriltan.twitteranalyzerserver.api.twitterstream.service.StreamSocketService;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class StreamSocketController {

    @Inject
    private StreamSocketService streamSocketService;

    @Inject
    private SimpMessageSendingOperations webSocket;


    @MessageMapping("/manageTwitterStream")
    public void manageStream( @Payload StreamRequest streamRequest, SimpMessageHeaderAccessor headerAccessor){

        String sessionId = headerAccessor.getSessionId();
       /* if (bindingResult.hasErrors()) {
            TokenizedTweet tokenizedTweet = new TokenizedTweet();
            bindingResult.getAllErrors().forEach(error -> {
                tokenizedTweet.setException(error.toString());
                webSocket.convertAndSendToUser(sessionId, "/queue/fetchTwitterStream", tokenizedTweet , createHeaders(sessionId));

            });

        }*/
        streamSocketService.manageStream(streamRequest, sessionId);
    }

    private MessageHeaders createHeaders(String sessionId) { // TODO : Make this method static bcs it is used mult places
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }


}
