package com.onuriltan.twitteranalyzerserver.api.geolocation.controller;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.CustomErrorType;
import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationResponse;
import com.onuriltan.twitteranalyzerserver.api.geolocation.service.GeolocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class GeolocationController {

    @Inject
    GeolocationService geolocationService;

    @RequestMapping(value = "/getAddress", method = RequestMethod.GET)
    public ResponseEntity<?> getAdress(@RequestParam(value = "lat") String lat,
                                       @RequestParam(value = "lng") String lng) {

        GeolocationResponse geolocationResponse =  geolocationService.getAdress(lat, lng);

        if(geolocationResponse.getAddress() == null) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "address not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(geolocationResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "/getWoeid", method = RequestMethod.GET)
    public ResponseEntity<?> getWoeid(@RequestParam(value = "address") String address) {

        GeolocationResponse geolocationResponse = geolocationService.getWoeid(address);

        if(geolocationResponse.getWoeid() == null) {
            return new ResponseEntity<>(new CustomErrorType("400 ", "woeid not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(geolocationResponse, HttpStatus.OK);
    }

}
