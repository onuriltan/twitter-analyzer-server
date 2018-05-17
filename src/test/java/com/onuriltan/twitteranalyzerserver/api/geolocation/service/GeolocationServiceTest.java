package com.onuriltan.twitteranalyzerserver.api.geolocation.service;

import com.onuriltan.twitteranalyzerserver.api.geolocation.model.GeolocationResponse;
import com.onuriltan.twitteranalyzerserver.base.geocoding.GeocodeGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GeolocationServiceTest {

    @Mock
    private GeocodeGenerator geocodeGenerator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAdress_OK_Test() {

        String address = "test";


        Mockito.when(geocodeGenerator.getAddress(Mockito.anyString(), Mockito.anyString())).thenReturn(address);

        GeolocationService geolocationService = new GeolocationService(geocodeGenerator);
        GeolocationResponse mockResponse = geolocationService.getAdress(Mockito.anyString(), Mockito.anyString());

        Assert.assertEquals("test",mockResponse.getAddress());

    }

    @Test
    public void getWoeid_OK_Test() {

        String woeid = "test";
        Mockito.when(geocodeGenerator.getWoeid(Mockito.anyString())).thenReturn(woeid);

        GeolocationService geolocationService = new GeolocationService(geocodeGenerator);
        GeolocationResponse mockResponse = geolocationService.getWoeid(Mockito.anyString());

        Assert.assertEquals("test",mockResponse.getWoeid());

    }

    @Test
    public void getAdress_null_Test() {

        String address = null;
        Mockito.when(geocodeGenerator.getAddress(Mockito.isNull(), Mockito.anyString())).thenReturn(address);

        GeolocationService geolocationService = new GeolocationService(geocodeGenerator);
        GeolocationResponse mockResponse = geolocationService.getAdress(Mockito.isNull(), Mockito.anyString());

        Assert.assertEquals(null, mockResponse.getAddress());

    }

    @Test
    public void getWoeid_null_Test() {

        String woeid = null;
        Mockito.when(geocodeGenerator.getWoeid(Mockito.isNull())).thenReturn(woeid);

        GeolocationService geolocationService = new GeolocationService(geocodeGenerator);
        GeolocationResponse mockResponse = geolocationService.getWoeid(Mockito.isNull());

        Assert.assertEquals(null, mockResponse.getWoeid());

    }





}
