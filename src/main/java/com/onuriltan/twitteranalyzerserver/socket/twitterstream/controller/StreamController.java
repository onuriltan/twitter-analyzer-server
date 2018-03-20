package com.onuriltan.twitteranalyzerserver.socket.twitterstream.controller;

import com.onuriltan.twitteranalyzerserver.socket.twitterstream.model.KeyWord;
import com.onuriltan.twitteranalyzerserver.socket.twitterstream.model.Tweet;
import com.onuriltan.twitteranalyzerserver.socket.twitterstream.service.StreamService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
public class StreamController {

    @Inject
    StreamService streamService;

    @MessageMapping("/getStream")
    @SendTo("/twitter/fetchStream")
    public Tweet getTweetsAsStream(KeyWord keyWord) throws Exception {
        Thread.sleep(1000); // simulated delay
        //return new Greeting("Hello, " + message.getName() + "!");
        streamService.startStream(keyWord);

        return null;
    }


}
