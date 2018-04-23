package com.onuriltan.twitteranalyzerserver.api.trendtopic.controller;

import com.onuriltan.twitteranalyzerserver.api.trendtopic.model.TrendTopicModel;
import com.onuriltan.twitteranalyzerserver.api.trendtopic.service.TrendTopicService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://onuriltan.com:5000")
public class TrendTopicController {

    @Inject
    TrendTopicService trendTopicService;

    @RequestMapping("/getTrendTopics")
    public TrendTopicModel getTrendTopics() {
        return trendTopicService.getTrendtopics();
    }
}
