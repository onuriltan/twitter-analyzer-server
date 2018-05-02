package com.onuriltan.twitteranalyzerserver.api.geolocation.service;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationModel;
import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeGenerator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class GeolocationService {

    @Inject
    GeocodeGenerator geocodeGenerator;

    public GeolocationModel getAdress(String lat, String lng) {
        GeolocationModel geolocationModel = new GeolocationModel();
        String address = geocodeGenerator.getAddress(lat, lng);
        geolocationModel.setAddress(address);
        return geolocationModel;
    }

    public GeolocationModel getWoeid(String address) {
        GeolocationModel geolocationModel = new GeolocationModel();
        String woeid = geocodeGenerator.getWoeid(address);
        geolocationModel.setWoeid(woeid);
        return geolocationModel;
    }
}
