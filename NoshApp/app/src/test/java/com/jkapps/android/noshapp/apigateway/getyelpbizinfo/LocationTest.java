package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    @Test
    public void testGetCity() {
        String expectedCity = "San Francisco";
        Location location = new Location();
        location.setCity(expectedCity);
        String locationResponse = location.getCity();
        assertEquals(expectedCity, locationResponse);
    }

    @Test
    public void testGetCountry() {
        String expectecCountry = "United States";
        Location location = new Location();
        location.setCountry(expectecCountry);
        String locationResponse = location.getCountry();
        assertEquals(expectecCountry, locationResponse);
    }

    @Test
    public void testGetAddress1() {
        String expectedAddress1 = "800 N Point St";
        Location location = new Location();
        location.setAddress1(expectedAddress1);
        String locationResponse = location.getAddress1();
        assertEquals(expectedAddress1, locationResponse);
    }

    @Test
    public void testGetAddress2() {
        String expectedAddress2 = "";
        Location location = new Location();
        location.setAddress2(expectedAddress2);
        String locationResponse = location.getAddress2();
        assertEquals(expectedAddress2, locationResponse);
    }

    @Test
    public void testGetAddress3() {
        String expectedAddress3 = "";
        Location location = new Location();
        location.setAddress3(expectedAddress3);
        String locationResponse = location.getAddress3();
        assertEquals(expectedAddress3, locationResponse);
    }

    @Test
    public void testGetState() {
        String expectedState = "CA";
        Location location = new Location();
        location.setState(expectedState);
        String locationResponse = location.getState();
        assertEquals(expectedState, locationResponse);
    }

    @Test
    public void testGetZipCode() {
        String expectedZipCode = "";
        Location location = new Location();
        location.setZipCode(expectedZipCode);
        String locationResponse = location.getZipCode();
        assertEquals(expectedZipCode, locationResponse);
    }
}
