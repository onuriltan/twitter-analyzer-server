package com.onuriltan.twitteranalyzerserver.api.trendtopic.controller;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationModel;
import com.onuriltan.twitteranalyzerserver.api.geolocation.service.GeolocationService;
import com.onuriltan.twitteranalyzerserver.api.trendtopic.model.TrendTopicModel;
import com.onuriltan.twitteranalyzerserver.api.trendtopic.service.TrendTopicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class TrendTopicController {

    @Inject
    TrendTopicService trendTopicService;

    @Inject
    GeolocationService geolocationService;

    @RequestMapping(value = "/getTrendTopics/inArea" ,method = RequestMethod.GET)
    public TrendTopicModel getTrendTopicsInArea(@RequestParam(value="woeid") String woeid) {

        return trendTopicService.getTrendtopics(Integer.valueOf(woeid));
    }
    @RequestMapping(value = "/getTrendTopics/byGeolocation" ,method = RequestMethod.GET)
    public TrendTopicModel getTrendTopicsInAreaByLatLong(@RequestParam(value="lat") String lat,
                                                         @RequestParam(value="lng") String lng) {

        GeolocationModel addressModel = geolocationService.getAdress(lat,lng);
        GeolocationModel woeidModel = geolocationService.getWoeid(addressModel.getAddress());

        return trendTopicService.getTrendtopics(Integer.valueOf(woeidModel.getWoeid()));


    }

    @RequestMapping("/getTrendTopics/inWorldWide")
    public TrendTopicModel getTrendTopicsInWorldWide() {

        return trendTopicService.getTrendtopics(1);
    }
}
