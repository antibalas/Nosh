package com.jkapps.android.noshapp.apigateway;

import java.util.List;

//we can use the builder pattern here
public class Response {

    private final List<Business> businesses;

    private Response(final Builder builder) {
        businesses = builder.businesses;
    }

    public List<Business> getBusinesses() { return businesses; }

    public static class Builder {

        private List<Business> businesses;

        public Builder withBusinesses(final List<Business> businesses) {
            this.businesses = businesses;
            return this;
        }

        public Response build() { return new Response(this); }

    }
}
