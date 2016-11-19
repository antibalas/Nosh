package com.jkapps.android.noshapp.apigateway;

import android.util.Pair;

import java.util.List;

//we can use the builder pattern here
public class Business {


    private final String url;
    private final String name;
    private final double rating;
    private final String price;
    private final Pair<Double, Double> coordinates;
    private final List<String> categories;
    private final Location location;

    private Business(final Builder builder) {
        this.url         = builder.url;
        this.rating      = builder.rating;
        this.name        = builder.name;
        this.price       = builder.price;
        this.coordinates = builder.coordinates;
        this.categories  = builder.categories;
        this.location    = builder.location;
    }

    public String getUrl() { return url; }
    public String getName() { return name; }
    public double getRating() { return rating; }
    public String getPrice() { return price; }
    public Pair<Double, Double> getCoordinates() { return coordinates; }
    public List<String> getCategories() { return categories; }
    public Location getLocation() { return location; }

    public static class Location {

        private String zip_code;
        private String city;
        private String state;
        private String country;
        private String address1;
        private String address2;
        private String address3;

        public String getZipCode() { return zip_code; }
        public String getCity() { return city; }
        public String getState() { return state; }
        public String getCountry() { return country; }
        public String getAddress1() { return address1; }
        public String getAddress2() { return address2; }
        public String getAddress3() { return address3; }
    }

    public static class Builder {

        private String url;
        private String name;
        private double rating;
        private String price;
        private Pair<Double, Double> coordinates;
        private List<String> categories;
        private Location location;

        public Builder withUrl(final String url) {
            this.url = url;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withRating(final double rating) {
            this.rating = rating;
            return this;
        }

        public Builder withPrice(final String price) {
            this.price = price;
            return this;
        }

        public Builder withCoordinates(final Pair<Double, Double> coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder withCategories(final List<String> categories) {
            this.categories = categories;
            return this;
        }

        public Builder withLocation(final Location location) {
            this.location = location;
            return this;
        }


        public Business build() { return new Business(this); }
    }
}
