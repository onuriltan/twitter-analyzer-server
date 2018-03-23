package com.onuriltan.twitteranalyzerserver.websocket.controller;


import com.onuriltan.twitteranalyzerserver.websocket.model.Request;
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

    @MessageMapping("/getTwitterStream")
    public String sendStream(Request request) throws Exception {
        Thread.sleep(1000); // simulated delay
        streamSocketService.startStream(request, webSocket);
        return "OK";
    }

}
