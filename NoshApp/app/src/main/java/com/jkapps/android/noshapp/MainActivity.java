package com.jkapps.android.noshapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.Business;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;
import com.jkapps.android.noshapp.apigateway.getyelpbizinfo.YelpBizClient;
import com.jkapps.android.noshapp.apigateway.getyelpbizinfo.YelpBizListener;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_LOGCAT_STR_SIZE = 1000;

    //logcat caps strings at 1000 chars which may not be big enough, so we
    //can just break it down into smaller strings and print those
    private static void logBigString(final String tag, final String str) {
        for (int i = 0; i <= str.length() / MAX_LOGCAT_STR_SIZE; ++i) {
            final int start = i * MAX_LOGCAT_STR_SIZE;
            final int end = (i + 1) * MAX_LOGCAT_STR_SIZE;
            Log.d(tag, str.substring(start, end > str.length() ?
                                            str.length() : end));
        }
    }

    private static void logBusiness(String tag, Business business) {
        Log.d(tag, "URL: " + business.getUrl());
        Log.d(tag, "Name: " + business.getName());
        Log.d(tag, "Price: " + business.getPrice());
        Log.d(tag, "Rating: " + business.getRating());
        Log.d(tag, "Categories: " + business.getCategories());
        Log.d(tag, "Coordinates: " + business.getCoordinates());
    }

    private static void testAPIGateway() {
        // we should do this with an AsyncTask
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Stack<Pair<String, String>> params = new Stack<>();
                    params.push(new Pair<>("location", "Santa Cruz CA"));
                    params.push(new Pair<>("limit", "3"));
                    for (Business business :
                         APIGateway.hitGateway("getFromYelp", params,
                                               new GetFromYelpDeserializer())
                                   .getBusinesses())
                        logBusiness("testAPIGateway", business);
                } catch (Exception e) {
                    Log.d("onCreate", Log.getStackTraceString(e));
                }
            }
        }).start();
    }

    private static void testYelpBiz(){
        String id = "gary-danko-san-francisco";
        YelpBizClient yelpBizClient = new YelpBizClient();
        yelpBizClient.getYelpBizInfo(id, new YelpBizListener() {
            @Override
            public void success(com.jkapps.android.noshapp.apigateway.getyelpbizinfo.Business business) {
                for (String photo : business.getPhotos()) {
                    String message = "photo = " + photo;
                    Log.d("Yelp Biz", message);
                }
            }

            @Override
            public void failure() {
                Log.d("Yelp Biz", "FAILURE -- Could not get business");
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testAPIGateway();
        testYelpBiz();
    }
}
