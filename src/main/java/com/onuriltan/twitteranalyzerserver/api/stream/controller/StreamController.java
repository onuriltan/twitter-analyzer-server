package com.onuriltan.twitteranalyzerserver.api.stream.controller;

import com.onuriltan.twitteranalyzerserver.api.stream.service.StreamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/twitter")
public class StreamController {

    @Inject
    StreamService streamService;

    @RequestMapping(value = "/startStream" , method = RequestMethod.GET)
    public void startStream(@RequestParam(name = "keyword") String keyword){
        streamService.startStream(keyword);

    }

    @RequestMapping(value = "/stopStream" , method = RequestMethod.GET)
    public void stopStream(){
        streamService.stopStream();

    }

}
