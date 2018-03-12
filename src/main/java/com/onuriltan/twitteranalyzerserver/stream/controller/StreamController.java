package com.onuriltan.twitteranalyzerserver.stream.controller;

import com.onuriltan.twitteranalyzerserver.stream.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twitter")
public class StreamController {

    @Autowired
    StreamService streamService;


    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    public String index(@RequestParam String keyword) {
        return streamService.stream(keyword);
    }
}
