package com.onuriltan.twitteranalyzerserver.config.googlemaps;

import com.google.maps.GeoApiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class GoogleMapsBeans {

    @Inject
    GoogleMapsConfig googleMapsConfig;

    @Bean
    public GeoApiContext getContext() {
        return new GeoApiContext.Builder()
                .apiKey(googleMapsConfig.getApiKey())
                .build();
    }

}
