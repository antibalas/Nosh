package com.jkapps.android.noshapp.feedme;

import com.jkapps.android.noshapp.R;
import com.jkapps.android.noshapp.feedme.display.DisplayParams;
import com.jkapps.android.noshapp.feedme.display.DisplayTask;
import com.jkapps.android.noshapp.feedme.yelp.YelpWebView;
import com.jkapps.android.noshapp.feedme.yelp.YelpWebViewClient;
import com.uber.sdk.android.rides.RideRequestButton;
import com.jkapps.android.noshapp.feedme.buttons.InitializeAll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;

import android.webkit.WebView;

import static com.jkapps.android.noshapp.uber.Configuration.initialized;
import static com.jkapps.android.noshapp.uber.Configuration.initializedLock;

public class FeedMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final DisplayTask displayTask = new DisplayTask();
        final RideRequestButton uberButton = getRideRequestButton();
        DisplayParams displayParams = retrieveDisplayParams(uberButton);
        displayTask.execute(displayParams);
        (new InitializeAll()).initializeButtons
                (displayTask, uberButton, displayParams, FeedMeActivity.this);
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

    private DisplayParams retrieveDisplayParams
            (final RideRequestButton uberButton) {
        return (new DisplayParams.Builder())
                .withCategory(getParam("CategoryParam"))
                .withRating(getParam("RatingParam"))
                .withDollars(getParam("DollarsParam"))
                .withLatitude(getParam("Latitude"))
                .withLongitude(getParam("Longitude"))
                .withYelpView(getYelpView())
                .withRideRequestButton(uberButton)
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

