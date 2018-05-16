package com.onuriltan.twitteranalyzerserver.base.geocoding;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.onuriltan.twitteranalyzerserver.config.googlemaps.GoogleMapsConfig;
import com.onuriltan.twitteranalyzerserver.config.yahoo.YahooConfig;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.json.JsonArray;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Service
public class GeocodeGenerator {

    @Inject
    GoogleMapsConfig googleMapsConfig;

    @Inject
    YahooConfig yahooConfig;

    @Inject
    RestTemplate restTemplate;

    public GeocodeResponse getLatLong(String address) {

        if (address != null) {
            address = address.replaceAll(" ", "");
            String url = googleMapsConfig.getUrl() + "?address=" + address + "&key=" + googleMapsConfig.getApiKey();

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response.getBody());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (response.getStatusCode().is2xxSuccessful()) {
                try {
                    if (jsonObject != null)
                        if (jsonObject.getString("status").equals("OK"))
                            try {
                                double lat = jsonObject.getJSONArray("results")
                                        .getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                                double lng = jsonObject.getJSONArray("results")
                                        .getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                                return new GeocodeResponse(lat, lng);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }



        return null;
    }

    public String getAddress(String lat, String lng) {
        if (lat != null || lng != null) {

            /*GeoApiContext context = new GeoApiContext.Builder() // TODO : implement geocode client library
                    .apiKey(googleMapsConfig.getApiKey())
                    .build();
            GeocodingResult[] results = new GeocodingResult[0];
            try {
                results = GeocodingApi.reverseGeocode(context, new LatLng(Double.valueOf(lat), Double.valueOf(lng))).await();
            } catch (ApiException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(results[0].addressComponents));*/


            String url = googleMapsConfig.getUrl() + "?latlng=" + lat + "," + lng + "&key=" + googleMapsConfig.getApiKey();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response.getBody());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (response.getStatusCode().is2xxSuccessful()) {
                try {
                    if (jsonObject != null)
                        if (jsonObject.getString("status").equals("OK")) {
                            try {
                                JSONArray array = jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("address_components");
                                for (int i = 0; i < array.length(); i++) {
                                    if (array.getJSONObject(i).getJSONArray("types").get(0).equals("country")) {
                                        return array.getJSONObject(i).getString("long_name");
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            return "error, "+jsonObject.getString("status");
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                e.printStackTrace();
            }
            if (response.getStatusCode().is2xxSuccessful()) {
                if (jsonObject != null) {
                    try {
                        return jsonObject.getJSONObject("query").getJSONObject("results").getJSONArray("place").getJSONObject(0).getString("woeid");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return null;
    }
}
