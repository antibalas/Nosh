package com.jkapps.android.noshapp;

import com.jkapps.android.noshapp.uber.Configuration;
import com.jkapps.android.noshapp.yelppage.DisplayParams;
import com.jkapps.android.noshapp.yelppage.DisplayTask;
import com.jkapps.android.noshapp.yelppage.YelpWebView;
import com.jkapps.android.noshapp.yelppage.YelpWebViewClient;
import com.uber.sdk.android.rides.RideRequestButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.content.Intent;

import android.webkit.WebView;

public class FeedMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final DisplayTask displayTask = new DisplayTask();
        final RideRequestButton rideRequestButton = getRideRequestButton();
        DisplayParams displayParams = retrieveDisplayParams(rideRequestButton);
        initButtons(displayTask, displayParams);
        displayTask.execute(displayParams);
    }

    private RideRequestButton getRideRequestButton() {
        return ((RideRequestButton) findViewById(R.id.UberButton));
    }

    private void initButtons(final DisplayTask displayTask,
                             final DisplayParams displayParams) {
        initGoBackButton();
        initDislikeButton(displayTask, displayParams);
        initUberButton(displayTask, displayParams.getRideRequestButton());
    }

    private void initDislikeButton(final DisplayTask displayTask,
                                   final DisplayParams displayParams) {
        (findViewById(R.id.Dislike)).setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayTask.displayNextYelpPage(displayParams);
                    }
                });
    }

    /* There are 2 races we're concerned about with the uber button:
     * 1) initializing it before it's been configured
     * 2) allowing clicking before the ride parameters have been set
     */
    private void initUberButton(final DisplayTask displayTask,
                                final RideRequestButton rideRequestButton) {
        while (!Configuration.isInitialized()); //prevent race 1
        /*rideRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                while (!displayTask.rideParametersSet); //prevent race 2
                displayTask.rideParametersSet = false; //race
            }
        });*/
    }

    private void initGoBackButton() {
        Button GoBack = (Button) findViewById(R.id.GoBack);
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedMeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private DisplayParams retrieveDisplayParams
            (final RideRequestButton rideRequestButton) {
        return (new DisplayParams.Builder())
                .withCategory(getParam("CategoryParam"))
                .withRating(getParam("RatingParam"))
                .withDollars(getParam("DollarsParam"))
                .withLatitude(getParam("Latitude"))
                .withLongitude(getParam("Longitude"))
                .withYelpView(getYelpView())
                .withRideRequestButton(rideRequestButton)
                .build();
    }

    private String getParam(String param) {
        return getIntent().getExtras().getString(param);
    }

    private YelpWebView getYelpView() {
        final YelpWebView yelpView = (YelpWebView) findViewById(R.id.YelpView);
        yelpView.getSettings().setJavaScriptEnabled(true);

        final YelpWebViewClient yelpWebViewClient = new YelpWebViewClient();
        yelpView.setWebViewClient(yelpWebViewClient);
        yelpView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(final WebView webView,
                                          final int progress) {
                if (progress == 100)
                    yelpWebViewClient.setDone();
            }
        });

        return yelpView;
    }
}

