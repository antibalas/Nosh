package com.jkapps.android.noshapp.apigateway;

import java.net.URL;

import java.util.List;
import java.util.Map;

public class APIGateway {

    private static final String API_GATEWAY_BASE_URL =
            "https://zg5hawbfuh.execute-api.us-west-2.amazonaws.com/prod/";

    public static Map<String, String> hitGateway (final String resource,
                                                  final List<String> params) {
        return null;
    }

    public static String test (final String resource) throws Exception {
        return Communicator.performGetRequest
                (new URL(API_GATEWAY_BASE_URL + resource));
    }
}