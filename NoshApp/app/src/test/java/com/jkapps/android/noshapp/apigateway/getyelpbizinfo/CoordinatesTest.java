package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CoordinatesTest {

    @Test
    public void testGetLatitude() {
        Double expectedLatitude = 37.80587;
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(expectedLatitude);
        Double coordinatesResponse = coordinates.getLatitude();
        assertEquals(expectedLatitude, coordinatesResponse);
    }

    @Test
    public void testGetLongitude() {
        Double expectedLongitude = -122.42058;
        Coordinates coordinates = new Coordinates();
        coordinates.setLongitude(expectedLongitude);
        Double coordinatesResponse = coordinates.getLongitude();
        assertEquals(expectedLongitude, coordinatesResponse);
    }

}
