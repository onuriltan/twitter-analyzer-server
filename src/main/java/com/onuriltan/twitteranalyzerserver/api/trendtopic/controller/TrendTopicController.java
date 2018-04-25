package com.onuriltan.twitteranalyzerserver.api.trendtopic.controller;

import com.onuriltan.twitteranalyzerserver.api.trendtopic.model.TrendTopicModel;
import com.onuriltan.twitteranalyzerserver.api.trendtopic.service.TrendTopicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class TrendTopicController {

    @Inject
    TrendTopicService trendTopicService;

    @RequestMapping("/getTrendTopics/inArea")
    public TrendTopicModel getTrendTopicsInArea() {
        return trendTopicService.getTrendtopics(23424969);
    }

    @RequestMapping("/getTrendTopics/inWorldWide")
    public TrendTopicModel getTrendTopicsInWorldWide() {
        return trendTopicService.getTrendtopics(1);
    }
}
