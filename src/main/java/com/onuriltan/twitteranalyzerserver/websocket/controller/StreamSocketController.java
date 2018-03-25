package com.onuriltan.twitteranalyzerserver.websocket.controller;


import com.onuriltan.twitteranalyzerserver.websocket.model.StreamRequest;
import com.onuriltan.twitteranalyzerserver.websocket.service.StreamSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
public class StreamSocketController {

    @Inject
    private SimpMessageSendingOperations webSocket;

    @Inject
    private StreamSocketService streamSocketService;

    @MessageMapping("/startTwitterStream")
    public String sendStream(StreamRequest streamRequest) throws Exception {
        Thread.sleep(1000); // simulated delay
        streamSocketService.startStream(streamRequest, webSocket);
        return "OK";
    }

}
