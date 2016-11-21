package com.jkapps.android.noshapp.uber.summoner;

import android.os.AsyncTask;

public class SummonerTask extends AsyncTask<SummonerParams, Void, Void> {

    @Override
    public Void doInBackground(SummonerParams ... summonerParams) {
        Summoner.setRideParameters(summonerParams[0]);
        synchronized (summonerParams[0].getDisplayTask()
                                       .rideParametersSetLock) {
            summonerParams[0].getDisplayTask().rideParametersSet.set(true);
            summonerParams[0].getDisplayTask().rideParametersSetLock.notify();
        }
        return null;
    }
}
