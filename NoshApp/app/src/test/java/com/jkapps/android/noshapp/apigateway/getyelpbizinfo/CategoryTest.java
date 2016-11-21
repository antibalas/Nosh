package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import static org.junit.Assert.*;


public class CategoryTest {

    //Test the test
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetAlias() {
        String expectedAlias = "newamerican";
        Category category = new Category();
        category.setAlias(expectedAlias);
        String categoryResponse = category.getAlias();
        assertEquals(expectedAlias, categoryResponse);
    }

    @Test
    public void testGetTitle() {
        String expectedTitle = "American (New)";
        Category category = new Category();
        category.setTitle(expectedTitle);
        String categoryResponse = category.getTitle();
        assertEquals(expectedTitle, categoryResponse);
    }

}
