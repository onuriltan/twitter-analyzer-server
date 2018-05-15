package com.onuriltan.twitteranalyzerserver.api.geolocation.controller;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.CustomErrorType;
import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationResponse;
import com.onuriltan.twitteranalyzerserver.api.geolocation.service.GeolocationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class GeolocationControllerTest {

    @Mock
    private GeolocationService geolocationService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAdress_OK_Test() {

        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setAddress("test");

        Mockito.when(geolocationService.getAdress(Mockito.anyString(), Mockito.anyString())).thenReturn(geolocationResponse);

        GeolocationController geolocationController = new GeolocationController(geolocationService);
        ResponseEntity<GeolocationResponse> mockResponse = (ResponseEntity<GeolocationResponse>) geolocationController.getAdress(Mockito.anyString(),Mockito.anyString());

        Assert.assertEquals(HttpStatus.OK,mockResponse.getStatusCode());
        Assert.assertEquals("test",mockResponse.getBody().getAddress());


    }

    @Test
    public void getAdress_NOT_FOUND_Test() {

        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setAddress(null);

        Mockito.when(geolocationService.getAdress(Mockito.anyString(), Mockito.anyString())).thenReturn(geolocationResponse);

        GeolocationController geolocationController = new GeolocationController(geolocationService);
        ResponseEntity<CustomErrorType> mockResponse = (ResponseEntity<CustomErrorType>) geolocationController.getAdress(Mockito.anyString(),Mockito.anyString());

        Assert.assertEquals(HttpStatus.NOT_FOUND, mockResponse.getStatusCode());
        Assert.assertEquals("address not found",mockResponse.getBody().getErrorMessage());


    }

    @Test
    public void getWoeid_OK_Test() {

        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setWoeid("test");

        Mockito.when(geolocationService.getWoeid(Mockito.anyString())).thenReturn(geolocationResponse);

        GeolocationController geolocationController = new GeolocationController(geolocationService);
        ResponseEntity<GeolocationResponse> mockResponse = (ResponseEntity<GeolocationResponse>) geolocationController.getWoeid(Mockito.anyString());

        Assert.assertEquals(HttpStatus.OK,mockResponse.getStatusCode());
        Assert.assertEquals("test",mockResponse.getBody().getWoeid());


    }

    @Test
    public void getWoeid_NOT_FOUND_Test() {

        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setWoeid(null);

        Mockito.when(geolocationService.getWoeid(Mockito.anyString())).thenReturn(geolocationResponse);

        GeolocationController geolocationController = new GeolocationController(geolocationService);
        ResponseEntity<CustomErrorType> mockResponse = (ResponseEntity<CustomErrorType>) geolocationController.getWoeid(Mockito.anyString());

        Assert.assertEquals(HttpStatus.NOT_FOUND, mockResponse.getStatusCode());
        Assert.assertEquals("woeid not found",mockResponse.getBody().getErrorMessage());


    }





}
