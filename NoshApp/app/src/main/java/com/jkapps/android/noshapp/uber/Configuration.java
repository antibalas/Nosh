package com.jkapps.android.noshapp.uber;

import android.os.AsyncTask;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

public class Configuration extends AsyncTask<Void, Void, SessionConfiguration> {

    private static final String CLIENT_ID = "DvbZBHi2j0TSA-odnpjU0Nw9aCKTh9q4";

    private static final String SERVER_TOKEN = "oVEqLqICz2VzQq5P9gskcosw--0f2eAndFEPm6hr";

    public static boolean initialized = false;

    //prevent race on uber button
    public static boolean isInitialized() { return initialized; }

    @Override
    public SessionConfiguration doInBackground (Void... args) {
        return (new SessionConfiguration.Builder())
                .setClientId(CLIENT_ID)
                .setServerToken(SERVER_TOKEN)
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();
    }

    @Override
    public void onPostExecute(SessionConfiguration config) {
        UberSdk.initialize(config);
        initialized = true;
    }
}
