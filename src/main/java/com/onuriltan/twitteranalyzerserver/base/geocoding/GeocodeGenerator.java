package com.onuriltan.twitteranalyzerserver.base.geocoding;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.onuriltan.twitteranalyzerserver.config.locationiq.LocationIqConfig;
import com.onuriltan.twitteranalyzerserver.config.yahoo.YahooConfig;
import java.io.IOException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GeocodeGenerator {

    @Inject
    GeoApiContext geoApiContext;

    @Inject
    YahooConfig yahooConfig;

    @Inject
    RestTemplate restTemplate;

    @Inject
    LocationIqConfig locationIqConfig;

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


    public String getAddress(String lat, String lon) {

        if (lat != null || lon != null) {

            String apiKey = locationIqConfig.getApiKey();
            String baseUrl = locationIqConfig.getUrl();
            String fullUrlStr = baseUrl+"?key="+apiKey+"&lat="+lat+"&lon="+lon+"&format=json";


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
                        return (jsonObject.getJSONObject("address").getString("country"));
                    } catch (JSONException e) {
                        logger.error("ErrorMessage: "+e.getLocalizedMessage());

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
