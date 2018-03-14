package com.onuriltan.twitteranalyzerserver.api.stream.controller;

import com.onuriltan.twitteranalyzerserver.api.stream.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/twitter")
public class StreamController {

    @Autowired
    StreamService startStreamService;

    @RequestMapping(value = "/startStream", method = RequestMethod.GET)
    public String stream(@RequestParam("keyword") String keyword){
        return startStreamService.startTwitterStream(keyword);
    }

    @RequestMapping(value = "/stopStream", method = RequestMethod.GET)
    public String stream(){
        return startStreamService.stopTwitterStream();

    }

}
