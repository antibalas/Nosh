package com.jkapps.android.noshapp;

import com.jkapps.android.noshapp.display.DisplayParams;
import com.jkapps.android.noshapp.display.DisplayTask;
import com.jkapps.android.noshapp.display.yelp.YelpWebView;
import com.jkapps.android.noshapp.display.yelp.YelpWebViewClient;
import com.uber.sdk.android.rides.RideRequestButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.content.Intent;

import android.webkit.WebView;

import static com.jkapps.android.noshapp.uber.Configuration.initialized;
import static com.jkapps.android.noshapp.uber.Configuration.initializedLock;

public class FeedMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final DisplayTask displayTask = new DisplayTask();
        final RideRequestButton rideRequestButton = getRideRequestButton();
        DisplayParams displayParams = retrieveDisplayParams(rideRequestButton);
        displayTask.execute(displayParams);      //order of these two
        initButtons(displayTask, displayParams); //is very important
    }

    /* There are 2 races we're concerned about with the uber button:
     * 1) initializing it before it's been configured
     * 2) allowing clicking before the ride parameters have been set
     */
    private RideRequestButton getRideRequestButton() {
        try {
            synchronized (initializedLock) { //prevent race 1
                while (!initialized)
                    initializedLock.wait();
            }
        } catch (InterruptedException e) { throw new RuntimeException(e); }
        return ((RideRequestButton) findViewById(R.id.UberButton));
    }

    private void initButtons(final DisplayTask displayTask,
                             final DisplayParams displayParams) {
        initGoBackButton();
        initDislikeButton(displayTask, displayParams);
        initOverlapUberButton(displayParams.getRideRequestButton());
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

    private void initOverlapUberButton
            (final RideRequestButton rideRequestButton) {
        final Button overlapUberButton =
                ((Button) findViewById(R.id.OverlapUberButton));
        overlapUberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rideRequestButton.callOnClick();
            }
        });
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

