package com.onuriltan.twitteranalyzerserver.api.geolocation.controller;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationModel;
import com.onuriltan.twitteranalyzerserver.api.geolocation.service.GeolocationService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class GeolocationController {

    @Inject
    GeolocationService geolocationService;

    @RequestMapping(value = "/getAddress" ,method = RequestMethod.GET)
    public GeolocationModel getAdress(@RequestParam(value="lat") String lat,
                                      @RequestParam(value="lng") String lng) {
        return geolocationService.getAdress(lat, lng);
    }

    @RequestMapping(value = "/getWoeid" ,method = RequestMethod.GET)
    public GeolocationModel getWoeid(@RequestParam(value="address") String address) {
        return geolocationService.getWoeid(address);
    }

}
