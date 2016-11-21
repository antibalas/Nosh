package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeadersTest {

    // Testing the test
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetContentType() {
        String expectedContentType = "content-type";
        Headers headers = new Headers();
        headers.setContentType(expectedContentType);
        String headersResponse = headers.getContentType();
        assertEquals(expectedContentType, headersResponse);
    }

}

