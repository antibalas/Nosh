package com.jkapps.android.noshapp.apigateway.deserializer;

import com.jkapps.android.noshapp.apigateway.Response;

public class GetFromYelpDeserializer implements Deserializer {

    @Override
    public Response deserialize(String rawJson) {
        return (new Response.Builder()).withBody(rawJson).build();
    }
}
