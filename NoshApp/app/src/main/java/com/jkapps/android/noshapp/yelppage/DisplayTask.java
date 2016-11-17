package com.jkapps.android.noshapp.yelppage;

import android.os.AsyncTask;
import android.webkit.WebView;
import android.util.Pair;

import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;
import com.jkapps.android.noshapp.apigateway.Response;
import com.jkapps.android.noshapp.apigateway.Business;

import java.util.List;
import java.util.Stack;

public class DisplayTask extends
        AsyncTask<DisplayParams, Void, Pair<WebView, Response>> {

    List<Business> businessList;
    int businessListIndex = 0;

    @Override
    public Pair<WebView, Response> doInBackground(final DisplayParams... displayParams_) {
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
    public void onPostExecute(final Pair<WebView, Response> result) {
        businessList = result.second.getBusinesses();
        displayNextYelpPage(result.first);
    }

    //start over if we've gone through the whole list
    public void displayNextYelpPage(final WebView yelpView) {
        if (businessListIndex > businessList.size() - 1)
            businessListIndex = 0;
        yelpView.loadUrl(businessList.get(businessListIndex++).getUrl());
    }
}
