package com.jkapps.android.noshapp.yelppage;

public class DisplayParams {

    private final String category;
    private final String rating;
    private final String dollars;
    private final String latitude;
    private final String longitude;
    private final YelpWebView yelpView;

    private DisplayParams(final Builder builder) {
        category  = builder.category;
        rating    = builder.rating;
        dollars   = builder.dollars;
        longitude = builder.longitude;
        latitude  = builder.latitude;
        yelpView  = builder.yelpView;
    }

    public String getCategory () { return category; }
    public String getRating() { return rating; }
    public String getDollars() { return dollars; }
    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
    public YelpWebView getYelpView() { return yelpView; }

    public static class Builder {

        private String category;
        private String rating;
        private String dollars;
        private String longitude;
        private String latitude;
        private YelpWebView yelpView;

        public Builder withCategory(final String category) {
            this.category = category;
            return this;
        }

        public Builder withRating(final String rating) {
            this.rating = rating;
            return this;
        }

        public Builder withDollars(final String dollars) {
            this.dollars = dollars;
            return this;
        }

        public Builder withLongitude(final String longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder withLatitude(final String latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder withYelpView(final YelpWebView yelpView) {
            this.yelpView = yelpView;
            return this;
        }

        public DisplayParams build() { return new DisplayParams(this); }
    }
}
