package com.jkapps.android.noshapp.apigateway.deserializer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetFromYelpDeserializerTest {

    @Test
    public void testGetName() {
        String expectedName = "Chocolate";
        GetFromYelpDeserializer.GetFromYelpFormat.Body expectedBody = new GetFromYelpDeserializer.GetFromYelpFormat.Body();
        expectedBody.name = expectedName;
        String deserializerResponse = expectedBody.getName();
        assertEquals(expectedName, deserializerResponse);
    }

    @Test
    public void testGetURL() {
        String expectedURL = "http://www.donnellychocolates.com/";
        GetFromYelpDeserializer.GetFromYelpFormat.Body expectedBody = new GetFromYelpDeserializer.GetFromYelpFormat.Body();
        expectedBody.url = expectedURL;
        String deserializerResponse = expectedBody.getUrl();
        assertEquals(expectedURL, deserializerResponse);
    }

    @Test
    public void testGetRating() {
        double expectedRating = 4.5;
        GetFromYelpDeserializer.GetFromYelpFormat.Body expectedBody = new GetFromYelpDeserializer.GetFromYelpFormat.Body();
        expectedBody.rating = expectedRating;
        double deserializerResponse = expectedBody.getRating();
        assertEquals(expectedRating, deserializerResponse, 0.1);
    }

    @Test
    public void testGetPrice() {
        String expectedPrice = "$$";
        GetFromYelpDeserializer.GetFromYelpFormat.Body expectedBody = new GetFromYelpDeserializer.GetFromYelpFormat.Body();
        expectedBody.price = expectedPrice;
        String deserializerResponse = expectedBody.getPrice();
        assertEquals(expectedPrice, deserializerResponse);
    }


    @Test
    public void testGetCategory() {
        String expectedTitle = "Desserts";
        GetFromYelpDeserializer.GetFromYelpFormat.Body expectedBody = new GetFromYelpDeserializer.GetFromYelpFormat.Body();
        expectedBody.categories = new GetFromYelpDeserializer.GetFromYelpFormat.Body.Category[10];
        expectedBody.categories[0] = new GetFromYelpDeserializer.GetFromYelpFormat.Body.Category();
        expectedBody.categories[0].title = expectedTitle;
        String deserializerResponse = expectedBody.categories[0].getTitle();
        assertEquals(expectedTitle, deserializerResponse);
    }

    @Test
    public void testGetCoordinates() {
        double expectedLatitude = 36.9757037;
        double expectedLongtigude = -122.0265058;
        GetFromYelpDeserializer.GetFromYelpFormat.Body expectedBody = new GetFromYelpDeserializer.GetFromYelpFormat.Body();
        expectedBody.coordinates = new GetFromYelpDeserializer.GetFromYelpFormat.Body.Coordinates();

        expectedBody.coordinates.latitude = expectedLatitude;
        double deserializedLatitude = expectedBody.coordinates.getLatitude();
        assertEquals(expectedLatitude, deserializedLatitude, 0.1);

        expectedBody.coordinates.longitude = expectedLongtigude;
        double deserializedLongitude = expectedBody.coordinates.getLongitude();
        assertEquals(expectedLongtigude, deserializedLongitude, 0.1);
    }
}
