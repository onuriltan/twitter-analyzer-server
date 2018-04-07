package com.onuriltan.twitteranalyzerserver.base.geocoding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onuriltan.twitteranalyzerserver.config.googlemaps.GoogleMapsConfig;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Service
public class GeocodeGenerator {

    @Inject
    GoogleMapsConfig googleMapsConfig;

    @Inject
    RestTemplate restTemplate;

    @Inject
    ObjectMapper objectMapper;

    public GeocodeResponse getLatLong(String address) {
        String url = googleMapsConfig.getUrl()+"?address="+address+"&key="+googleMapsConfig.getApiKey();

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        JSONObject jsonObject = null;
        try {
             jsonObject = new JSONObject(response.getBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(response.getStatusCode().is2xxSuccessful()){
           try {
                double lat=  jsonObject.getJSONArray("results")
                        .getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                double lng=  jsonObject.getJSONArray("results")
                        .getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                return new GeocodeResponse(lat, lng);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        return null;
    }
}
