package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class HourTest {

    @Test
    public void testHoursType() {
        String expectedHoursType = "REGULAR";
        Hour hour = new Hour();
        hour.setHoursType(expectedHoursType);
        String hourResponse = hour.getHoursType();
        assertEquals(expectedHoursType, hourResponse);
    }

    @Test
    public void testGetOpen() {
        List<Open> expectedGetOpenList = new ArrayList<>();
        Hour hour = new Hour();
        hour.setOpen(expectedGetOpenList);
        List<Open> hourResponse = hour.getOpen();
        assertEquals(expectedGetOpenList, hourResponse);
    }

    @Test
    public void testIsOpenNow() {
        Boolean expectedIsOpenNow = false;
        Hour hour = new Hour();
        hour.setIsOpenNow(expectedIsOpenNow);
        Boolean hourResponse = hour.getIsOpenNow();
        assertEquals(expectedIsOpenNow, hourResponse);
    }

}
