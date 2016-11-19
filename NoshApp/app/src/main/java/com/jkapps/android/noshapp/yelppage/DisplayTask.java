package com.jkapps.android.noshapp.yelppage;

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

public class DisplayTask extends
        AsyncTask<DisplayParams, Void, Pair<DisplayParams, Response>> {

    private List<Business> businessList;
    private int businessListIndex = 0;

    public boolean rideParametersSet = false;

    @Override
    public Pair<DisplayParams, Response> doInBackground
            (final DisplayParams... displayParams_) {
        DisplayParams displayParams = displayParams_[0];

        Stack<Pair<String, String>> paramStack = new Stack<>();

        paramStack.push(new Pair<>("latitude", displayParams.getLatitude()));
        paramStack.push(new Pair<>("longitude", displayParams.getLongitude()));
        //TODO push other displayParams

        return new Pair<>(displayParams, hitGateway(paramStack));
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
    public void onPostExecute(final Pair<DisplayParams, Response> result) {
        Collections.shuffle(businessList = result.second.getBusinesses());
        displayNextYelpPage(result.first);
    }

    //start over if we've gone through the whole list
    public void displayNextYelpPage(final DisplayParams displayParams) {
        /*this fixes the race on the dislike button
         *if a location had no restaurants, this would loop forever
         *but since handling this would require an extra dialog box
         *let's just assume there are restaurants in the area ;P
         */
        while (businessListIndex > businessList.size() - 1)
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

