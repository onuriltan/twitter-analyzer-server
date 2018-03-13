package com.onuriltan.twitteranalyzerserver.stream.requestStream.controller;

import com.onuriltan.twitteranalyzerserver.stream.requestStream.model.RequestStreamModel;
import com.onuriltan.twitteranalyzerserver.stream.requestStream.service.RequestStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/twitter")
public class RequestStreamController {

    @Autowired
    RequestStreamService streamService;

    @RequestMapping(value = "/requestStream", method = RequestMethod.GET)
    public String index(@RequestBody RequestStreamModel requestStreamModel) {
        return streamService.stream(requestStreamModel.getKeyword());
    }
}
