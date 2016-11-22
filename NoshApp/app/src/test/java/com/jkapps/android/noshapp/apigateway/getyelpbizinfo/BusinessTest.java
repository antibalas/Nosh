package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BusinessTest {

    @Test
    public void testGetIsClaimed() {
        Boolean expectedIsClaimed = false;
        Business business = new Business();
        business.setIsClaimed(expectedIsClaimed);
        Boolean businessResponse = business.getIsClaimed();
        assertEquals(expectedIsClaimed, businessResponse);
    }

    @Test
    public void testGetRating() {
        Double expectedRating = 4.5;
        Business business = new Business();
        business.setRating(expectedRating);
        Double businessResponse = business.getRating();
        assertEquals(expectedRating, businessResponse);
    }

    @Test
    public void testGetReviewCount() {
        Integer expectedReviewCount = 4521;
        Business business = new Business();
        business.setReviewCount(expectedReviewCount);
        Integer businessResponse = business.getReviewCount();
        assertEquals(expectedReviewCount, businessResponse);
    }

    @Test
    public void testGetPhone() {
        String expectedPhone = "+14152520800";
        Business business = new Business();
        business.setPhone(expectedPhone);
        String businessResponse = business.getPhone();
        assertEquals(expectedPhone, businessResponse);
    }

    @Test
    public void testGetURL() {
        String expectedURL = "https://www.yelp.com/biz/gary-danko-san-francisco";
        Business business = new Business();
        business.setUrl(expectedURL);
        String businessResponse = business.getUrl();
        assertEquals(expectedURL, businessResponse);
    }

    @Test
    public void testGetPrice() {
        String expectedPrice = "$$$$";
        Business business = new Business();
        business.setPrice(expectedPrice);
        String businessResponse = business.getPrice();
        assertEquals(expectedPrice, businessResponse);
    }

    @Test
    public void testGetIsClosed() {
        Boolean expectedIsClosed = false;
        Business business = new Business();
        business.setIsClosed(expectedIsClosed);
        Boolean businessResponse = business.getIsClosed();
        assertEquals(expectedIsClosed, businessResponse);
    }

    @Test
    public void testGetCoordinates() {
        Coordinates expectedCoordinates = new Coordinates();
        expectedCoordinates.setLatitude(37.80587);
        expectedCoordinates.setLongitude(-122.42058);
        Business business = new Business();
        business.setCoordinates(expectedCoordinates);
        Coordinates businessResponse = business.getCoordinates();
        Double expectedLatitude = expectedCoordinates.getLatitude();
        Double businessLatitude = businessResponse.getLatitude();
        assertEquals(expectedLatitude, businessLatitude);
    }

    @Test
    public void testGetHours() {
        List<Hour> expectedHours = new ArrayList<>();
        Hour hour = new Hour();
        hour.setHoursType("REGULAR");
        hour.setOpen(new ArrayList<Open>());
        hour.setIsOpenNow(false);
        expectedHours.add(hour);
        Business business = new Business();
        business.setHours(expectedHours);
        List<Hour> businessResponse = business.getHours();

        String expectedHoursType = expectedHours.get(0).getHoursType();
        String businessHoursType = businessResponse.get(0).getHoursType();
        assertEquals(expectedHoursType, businessHoursType);

        List<Open> expectedHoursOpen = expectedHours.get(0).getOpen();
        List<Open> businessHoursOpen = businessResponse.get(0).getOpen();
        assertEquals(expectedHoursOpen, businessHoursOpen);

        Boolean expectedHoursOpenNow = expectedHours.get(0).getIsOpenNow();
        Boolean businessHoursOpenNow = businessResponse.get(0).getIsOpenNow();
        assertEquals(expectedHoursOpenNow, businessHoursOpenNow);
    }

    @Test
    public void testGetPhotos() {
        List<String> expectedPhotos = new ArrayList<>();
        expectedPhotos.add("http://s3-media3.fl.yelpcdn.com/bphoto/--8oiPVp0AsjoWHqaY1rDQ/o.jpg");
        expectedPhotos.add("http://s3-media2.fl.yelpcdn.com/bphoto/ybXbObsm7QGw3SGPA1_WXA/o.jpg");
        expectedPhotos.add("http://s3-media3.fl.yelpcdn.com/bphoto/7rZ061Wm4tRZ-iwAhkRSFA/o.jpg");
        Business business = new Business();
        business.setPhotos(expectedPhotos);
        List<String> businessResponse = business.getPhotos();

        for (int index=0; index < expectedPhotos.size(); index++) {
            String expectedPhotosPhoto = expectedPhotos.get(index);
            String businessResponsePhoto = businessResponse.get(index);
            assertEquals(expectedPhotosPhoto, businessResponsePhoto);
        }
    }

    @Test
    public void testGetImageURL() {
        String expectedImageURL = "https://s3-media4.fl.yelpcdn.com/bphoto/--8oiPVp0AsjoWHqaY1rDQ/o.jpg";
        Business business = new Business();
        business.setImageUrl(expectedImageURL);
        String businessResponse = business.getImageUrl();
        assertEquals(expectedImageURL, businessResponse);
    }

    @Test
    public void testGetCategories() {
        List<Category> expectedCategories = new ArrayList<>();
        Category category = new Category();
        category.setTitle("American (New)");
        category.setAlias("newamerican");
        expectedCategories.add(category);
        Business business = new Business();
        business.setCategories(expectedCategories);
        List<Category> businessResponse = business.getCategories();

        String expectedTitle = expectedCategories.get(0).getTitle();
        String businessTitle = businessResponse.get(0).getTitle();
        assertEquals(expectedTitle, businessTitle);

        String expectedAlias = expectedCategories.get(0).getAlias();
        String businessAlias = businessResponse.get(0).getAlias();
        assertEquals(expectedAlias, businessAlias);
    }

    @Test
    public void testGetLocation() {
        Location expectedLocation = new Location();
        expectedLocation.setCity("San Francisco");
        Business business = new Business();
        business.setLocation(expectedLocation);
        Location businessResponse = business.getLocation();
        String expectedCity = expectedLocation.getCity();
        String businessCity = businessResponse.getCity();
        assertEquals(expectedCity, businessCity);
    }
}
