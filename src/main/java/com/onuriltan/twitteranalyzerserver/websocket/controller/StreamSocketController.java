package com.onuriltan.twitteranalyzerserver.websocket.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
public class StreamSocketController {

    @Inject
    private SimpMessagingTemplate webSocket;

    @MessageMapping("/getTwitterStream")
    @SendTo("/sendStream")
    public String sendStream(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        webSocket.
        return message;
    }

}
