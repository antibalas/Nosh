package com.jkapps.android.noshapp.yelppage;

import android.os.AsyncTask;
import android.view.View;
import android.util.Pair;

import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;
import com.jkapps.android.noshapp.apigateway.Response;
import com.jkapps.android.noshapp.apigateway.Business;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DisplayTask extends
        AsyncTask<DisplayParams, Void, Pair<YelpWebView, Response>> {

    private List<Business> businessList;
    private int businessListIndex = 0;

    @Override
    public Pair<YelpWebView, Response> doInBackground(final DisplayParams... displayParams_) {
        DisplayParams displayParams = displayParams_[0];

        Stack<Pair<String, String>> paramStack = new Stack<>();

        paramStack.push(new Pair<>("latitude", displayParams.getLatitude()));
        paramStack.push(new Pair<>("longitude", displayParams.getLongitude()));
        //TODO push other displayParams

        return new Pair<>(displayParams.getYelpView(), hitGateway(paramStack));
    }

    private Response hitGateway(final Stack<Pair<String, String>> params) {
        try {
            return APIGateway.hitGateway("getFromYelp", params,
                    new GetFromYelpDeserializer());
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    @Override
    public void onPostExecute(final Pair<YelpWebView, Response> result) {
        Collections.shuffle(businessList = result.second.getBusinesses());
        displayNextYelpPage(result.first);
    }

    //start over if we've gone through the whole list
    public void displayNextYelpPage(final YelpWebView yelpView) {
        if (businessListIndex > businessList.size() - 1)
            businessListIndex = 0;
        yelpView.loadUrl(businessList.get(businessListIndex++).getUrl());
        yelpView.loadUrl("javascript:(function() {" +
                "document.getElementById('fullscreen-pitch').style.display=\"none\";" +
                "})()");
        yelpView.setVisibility(View.VISIBLE);
    }
}
