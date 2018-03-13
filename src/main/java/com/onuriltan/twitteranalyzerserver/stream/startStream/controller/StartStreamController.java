package com.onuriltan.twitteranalyzerserver.stream.startStream.controller;

import com.onuriltan.twitteranalyzerserver.stream.requestStream.model.RequestStreamModel;
import com.onuriltan.twitteranalyzerserver.stream.startStream.service.StartStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StartStreamController {

    @Autowired
    StartStreamService startStreamService;

        @MessageMapping("/twitter/requestStream")
        @SendTo("/twitter/getStream")
        public String greeting(RequestStreamModel requestStreamModel) throws Exception {
            Thread.sleep(1000); // simulated delay
            startStreamService.stream(requestStreamModel.getKeyword());

            return requestStreamModel.getKeyword();
        }


}
