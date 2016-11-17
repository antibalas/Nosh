package com.jkapps.android.noshapp.apigateway.deserializer;

import com.google.gson.GsonBuilder;
import com.jkapps.android.noshapp.apigateway.Business;
import com.jkapps.android.noshapp.apigateway.Response;

import android.util.Pair;

import java.util.List;
import java.util.Stack;

public class GetFromYelpDeserializer implements Deserializer {

    @Override
    public Response deserialize(String rawJson) {
        Stack<Business> businesses = new Stack<>();
        for (GetFromYelpFormat.Body body : getFormat(rawJson).getBody())
            businesses.push(generateBusiness(body));
        return (new Response.Builder()).withBusinesses(businesses).build();

    }

    private static Business generateBusiness(GetFromYelpFormat.Body body) {
        return (new Business.Builder()).withUrl(body.getUrl())
                                       .withName(body.getName())
                                       .withPrice(body.getPrice())
                                       .withRating(body.getRating())
                                       .withCoordinates(getCoordinates
                                                       (body.getCoordinates()))
                                       .withCategories(getCategories
                                                      (body.getCategories()))
                                       .build();
    }

    private static GetFromYelpFormat getFormat(String rawJson) {
        return (new GsonBuilder()).create().fromJson(rawJson,
                GetFromYelpFormat.class);
    }

    private static Pair<Double, Double> getCoordinates
            (GetFromYelpFormat.Body.Coordinates coordinates) {
        return new Pair<Double, Double>(coordinates.getLatitude(),
                coordinates.getLongitude()) {
            @Override
            public String toString() { //default is really ugly
                return "<" + this.first + ", " + this.second + ">";
            }
        };
    }

    private static List<String> getCategories
            (GetFromYelpFormat.Body.Category[] categories) {
        Stack<String> categoryStack = new Stack<>();
        for (GetFromYelpFormat.Body.Category category : categories)
            categoryStack.push(category.getTitle());
        return categoryStack;
    }

    private static class GetFromYelpFormat {

        private Body[] body;

        Body[] getBody() { return body; }

        private static class Body {

            private String name;
            private String url;
            private double rating;
            private String price;
            private Category[] categories;
            private Coordinates coordinates;

            String getName() { return name; }
            String getUrl() { return url; }
            double getRating() { return rating; }
            String getPrice() { return price; }
            Category[] getCategories() { return categories; }
            Coordinates getCoordinates() { return coordinates; }

            private static class Category {

                //we can either use the title or the alias here
                private String title;

                String getTitle() { return title; }
            }

            private static class Coordinates {

                private double latitude;
                private double longitude;

                double getLatitude() { return latitude; }
                double getLongitude() { return longitude; }
            }
        }
    }
}
