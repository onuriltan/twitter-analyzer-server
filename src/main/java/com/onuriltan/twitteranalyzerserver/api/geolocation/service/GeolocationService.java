package com.onuriltan.twitteranalyzerserver.api.geolocation.service;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationResponse;
import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeGenerator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class GeolocationService {

    final GeocodeGenerator geocodeGenerator;

    public GeolocationService(final GeocodeGenerator geocodeGenerator) {
        this.geocodeGenerator = geocodeGenerator;

    }

    public GeolocationResponse getAdress(String lat, String lng) {
        String address = geocodeGenerator.getAddress(lat, lng);

        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setAddress(address);

        return geolocationResponse;
    }

    public GeolocationResponse getWoeid(String address) {

        String woeid = geocodeGenerator.getWoeid(address);

        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setWoeid(woeid);

        return geolocationResponse;

    }
}
