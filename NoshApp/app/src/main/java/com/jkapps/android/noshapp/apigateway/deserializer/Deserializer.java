package com.jkapps.android.noshapp.apigateway.deserializer;

import com.jkapps.android.noshapp.apigateway.Response;

//Every lambda function will need its own deserializer which will be passed
//into the APIGateway.hitGatetway function
public interface Deserializer {
    public Response deserialize(String rawJson);
}
