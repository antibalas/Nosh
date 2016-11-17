package com.jkapps.android.noshapp.apigateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Communicator {

    private static String getResponse(final HttpURLConnection httpConnection)
            throws Exception {

        final StringBuilder result = new StringBuilder();
        final BufferedReader reader = new BufferedReader(new InputStreamReader
                (httpConnection.getInputStream()));
        try {
            for (String line; (line = reader.readLine()) != null;
                 result.append(line));
        } finally { reader.close(); }

        return result.toString();
    }

    static String performGetRequest(final URL url) throws Exception {

        final String response;
        final HttpURLConnection httpConnection =
                (HttpURLConnection) url.openConnection();
        try {
            httpConnection.setRequestMethod("GET");
            response = getResponse(httpConnection);
        } finally { httpConnection.disconnect(); }

        return response;
    }
}
