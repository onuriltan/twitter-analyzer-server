package com.onuriltan.twitteranalyzerserver.api.trendtopic.controller;

import com.onuriltan.twitteranalyzerserver.api.trendtopic.model.TrendTopicModel;
import com.onuriltan.twitteranalyzerserver.api.trendtopic.service.TrendTopicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/twitteranalyzer/api")
public class TrendTopicController {

    @Inject
    TrendTopicService trendTopicService;

    @RequestMapping("/getTrendTopics")
    public TrendTopicModel getTrendTopics() {
        return trendTopicService.getTrendtopics();
    }
}
