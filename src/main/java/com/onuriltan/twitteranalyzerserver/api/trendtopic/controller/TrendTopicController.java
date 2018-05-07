package com.onuriltan.twitteranalyzerserver.api.trendtopic.controller;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.CustomErrorType;
import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationResponse;
import com.onuriltan.twitteranalyzerserver.api.geolocation.service.GeolocationService;
import com.onuriltan.twitteranalyzerserver.api.trendtopic.model.TrendTopicResponse;
import com.onuriltan.twitteranalyzerserver.api.trendtopic.service.TrendTopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class TrendTopicController {

    @Inject
    private TrendTopicService trendTopicService;
    @Inject
    private GeolocationService geolocationService;



    @RequestMapping(value = "/getTrendTopics/inArea" ,method = RequestMethod.GET)
    public ResponseEntity<?> getTrendTopicsInArea(@RequestParam(value="woeid") String woeid) {

        TrendTopicResponse trendTopicResponse = trendTopicService.getTrendtopics(Integer.valueOf(woeid));

        if(trendTopicResponse.getTrendTopics().size() == 0) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "trends not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trendTopicResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "/getTrendTopics/byGeolocation" ,method = RequestMethod.GET)
    public ResponseEntity<?> getTrendTopicsInAreaByLatLong(@RequestParam(value="lat") String lat,
                                                           @RequestParam(value="lng") String lng) {

        GeolocationResponse addressModel = geolocationService.getAdress(lat,lng);
        if(addressModel.getAddress() == null) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "address not found"), HttpStatus.NOT_FOUND);
        }
        GeolocationResponse woeidModel = geolocationService.getWoeid(addressModel.getAddress());
        if(woeidModel.getWoeid() == null) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "woeid not found"), HttpStatus.NOT_FOUND);
        }

        TrendTopicResponse trendTopicResponse = trendTopicService.getTrendtopics(Integer.valueOf(woeidModel.getWoeid()));
        if(trendTopicResponse.getTrendTopics().size() == 0) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "trends not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trendTopicResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "/getTrendTopics/byAddress" ,method = RequestMethod.GET)
    public ResponseEntity<?> getTrendTopicsByAddress(@RequestParam(value="address") String address
                                                        ) {
        GeolocationResponse woeidModel = geolocationService.getWoeid(address);

        if(woeidModel.getWoeid() == null) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "woeid not found"), HttpStatus.NOT_FOUND);
        }

        TrendTopicResponse trendTopicResponse = trendTopicService.getTrendtopics(Integer.valueOf(woeidModel.getWoeid()));

        if(trendTopicResponse.getTrendTopics().size() == 0) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "trends not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trendTopicResponse, HttpStatus.OK);
    }

    @RequestMapping("/getTrendTopics/inWorldWide")
    public ResponseEntity<?> getTrendTopicsInWorldWide() {
        TrendTopicResponse trendTopicResponse = trendTopicService.getTrendtopics(1);

        if(trendTopicResponse.getTrendTopics().size() == 0) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "trends not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trendTopicResponse, HttpStatus.OK);
    }
}
