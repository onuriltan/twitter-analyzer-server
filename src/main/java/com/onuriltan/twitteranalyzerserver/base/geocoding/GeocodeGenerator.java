package com.onuriltan.twitteranalyzerserver.base.geocoding;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.onuriltan.twitteranalyzerserver.config.yahoo.YahooConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.IOException;


@Service
public class GeocodeGenerator {

    @Inject
    GeoApiContext geoApiContext;

    @Inject
    YahooConfig yahooConfig;

    @Inject
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(GeocodeGenerator.class);


    public GeocodeResponse getLatLong(String address) {

        if (address != null) {

            GeocodingResult[] results = new GeocodingResult[0];
            try {
                results = GeocodingApi.geocode(geoApiContext, address).await();
            } catch (ApiException  | InterruptedException  | IOException e) {
                logger.error("ErrorMessage: " + e.getLocalizedMessage());


            }

            Double lat = results[0].geometry.location.lat;
            Double lng = results[0].geometry.location.lng;
            return new GeocodeResponse(lat, lng);

        }

        return null;

    }


    public String getAddress(String lat, String lng) {

        if (lat != null || lng != null) {

            GeocodingResult[] results;
            try {
                results = GeocodingApi.reverseGeocode(geoApiContext, new LatLng(Double.valueOf(lat), Double.valueOf(lng))).await();
            } catch (ApiException  | InterruptedException  | IOException e) {
                logger.error("ErrorMessage: " + e.getLocalizedMessage());

                return e.getLocalizedMessage();
            }


            AddressComponent[] addressComponents = results[0].addressComponents;

            if (addressComponents != null) {
                for (AddressComponent component : addressComponents) {
                    for (AddressComponentType addressComponentType : component.types) {
                        if (addressComponentType.equals(AddressComponentType.LOCALITY)) {
                            return component.shortName; // return city or town name
                        }
                        else if(addressComponentType.equals(AddressComponentType.SUBLOCALITY)) {
                            return component.shortName; // return city or town name

                        }
                        else if(addressComponentType.equals(AddressComponentType.COUNTRY)) {
                            return component.shortName; // return city or town name

                        }

                    }
                }
            }
        }

        return null;


    }

    public String getWoeid(String address) {

        if (address != null) {

            String baseUrl = yahooConfig.getUrl() + "?q=";
            String query = "select * from geo.places where text=\"" + address + "\"";
            String fullUrlStr = baseUrl + query + "&format=json";

            ResponseEntity<String> response = restTemplate.getForEntity(fullUrlStr, String.class);


            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response.getBody());
            } catch (JSONException e) {

                logger.error("ErrorMessage: "+e.getLocalizedMessage());

            }
            if (response.getStatusCode().is2xxSuccessful()) {
                if (jsonObject != null) {

                    try {
                        return (jsonObject.getJSONObject("query").getJSONObject("results").getJSONArray("place").getJSONObject(0).getString("woeid"));
                    } catch (JSONException e) {
                        logger.error("ErrorMessage: "+e.getLocalizedMessage());


                    }
                }
            }

        }

        return null;
    }





}
