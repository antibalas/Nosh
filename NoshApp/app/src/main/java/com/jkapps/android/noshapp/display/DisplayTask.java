package com.jkapps.android.noshapp.display;

import android.os.AsyncTask;
import android.view.View;
import android.util.Pair;

import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;
import com.jkapps.android.noshapp.apigateway.Response;
import com.jkapps.android.noshapp.apigateway.Business;
import com.jkapps.android.noshapp.uber.Summoner;
import com.jkapps.android.noshapp.uber.SummonerParams;
import com.uber.sdk.android.rides.RideRequestButton;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DisplayTask extends
        AsyncTask<DisplayParams, Void, DisplayParams> {

    private List<Business> businessList;
    private int businessListIndex = 0;

    private final Lock businessListLock = new ReentrantLock();

    public boolean rideParametersSet = false;

    @Override
    public DisplayParams doInBackground
            (final DisplayParams ... displayParams_) {
        final DisplayParams displayParams = displayParams_[0];

        final Stack<Pair<String, String>> paramStack = new Stack<>();

        paramStack.push(new Pair<>("latitude", displayParams.getLatitude()));
        paramStack.push(new Pair<>("longitude", displayParams.getLongitude()));
        //TODO push other displayParams

        final Response response = hitGateway(paramStack);

        synchronized (businessListLock) {
            Collections.shuffle(businessList = response.getBusinesses());
            if (businessList.isEmpty()) //FIXME
                throw new RuntimeException("There are no restaurants in the area!");
            businessListLock.notify();
        }

        return displayParams;
    }

    private Response hitGateway(final Stack<Pair<String, String>> params) {
        try {
            return APIGateway.hitGateway("getFromYelp", params,
                    new GetFromYelpDeserializer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onPostExecute(DisplayParams displayParams) {
        displayNextYelpPage(displayParams);
    }

    public void displayNextYelpPage(final DisplayParams displayParams) {

        try {
            synchronized (businessListLock) {
                while (businessList == null)
                    businessListLock.wait();
            }
        } catch (InterruptedException e) {}

        //start over if we've gone through the whole list
        if (businessListIndex > businessList.size() - 1)
            businessListIndex = 0;

        Summoner.setRideParameters(getSummonerParams
                (displayParams.getRideRequestButton()));
        rideParametersSet = true;

        displayParams.getYelpView()
                .loadUrl(businessList.get(businessListIndex++).getUrl());
        displayParams.getYelpView().loadUrl("javascript:(function() {" +
                "document.getElementById('fullscreen-pitch').style.display=\"none\";" +
                "})()");
        displayParams.getYelpView().setVisibility(View.VISIBLE);
    }

    private SummonerParams getSummonerParams
            (final RideRequestButton rideRequestButton) {
        final Business currentBusiness = businessList.get(businessListIndex);
        return new SummonerParams.Builder()
                .withLocation(currentBusiness.getLocation())
                .withCoordinates(currentBusiness.getCoordinates())
                .withName(currentBusiness.getName())
                .withRideRequestButton(rideRequestButton)
                .build();
    }
}

