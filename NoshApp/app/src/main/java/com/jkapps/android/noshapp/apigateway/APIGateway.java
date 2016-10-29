package com.jkapps.android.noshapp.apigateway;

import android.util.Pair;

import com.jkapps.android.noshapp.apigateway.deserializer.Deserializer;

import java.net.URL;

import java.net.URLEncoder;
import java.util.List;

public class APIGateway {

    private static final String API_GATEWAY_BASE_URL =
            "https://zg5hawbfuh.execute-api.us-west-2.amazonaws.com/prod/";

    private static final String ENCODING = "utf-8";

    private static String encode(final String str) throws Exception {
        return URLEncoder.encode(str, ENCODING);
    }

    private static URL getURL
            (final String resource, final List<Pair<String, String>> params)
            throws Exception {

        String url = API_GATEWAY_BASE_URL + resource;

        if (!params.isEmpty()) url += "?";
        for (int i = 0; i < params.size(); ++i)
            url += (encode(params.get(i).first) + "=" +
                    encode(params.get(i).second) +
                    (i != params.size() - 1 ? "&" : ""));

        return new URL(url);
    }

    public static Response hitGateway
            (final String resource, final List<Pair<String, String>> params,
             final Deserializer deserializer) throws Exception {
        return deserializer.deserialize
                (Communicator.performGetRequest(getURL(resource, params)));
    }

}
