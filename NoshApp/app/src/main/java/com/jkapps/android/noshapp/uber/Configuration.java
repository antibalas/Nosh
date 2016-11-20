package com.jkapps.android.noshapp.uber;

import android.os.AsyncTask;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Configuration extends AsyncTask<Void, Void, Void> {

    private static final String CLIENT_ID = "DvbZBHi2j0TSA-odnpjU0Nw9aCKTh9q4";

    private static final String SERVER_TOKEN = "oVEqLqICz2VzQq5P9gskcosw--0f2eAndFEPm6hr";

    public static boolean initialized = false;
    public static final Lock initializedLock = new ReentrantLock();

    private static SessionConfiguration getSessionConfiguration() {
        return (new SessionConfiguration.Builder())
                .setClientId(CLIENT_ID)
                .setServerToken(SERVER_TOKEN)
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();
    }

    @Override
    public Void doInBackground (Void... args) {
        UberSdk.initialize(getSessionConfiguration());
        synchronized (initializedLock) {
            initialized = true;
            initializedLock.notify();
        }
        return null;
    }
}
