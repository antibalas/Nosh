package com.jkapps.android.noshapp;

import android.support.v7.app.AppCompatActivity;

import com.jkapps.android.noshapp.yelppage.DisplayParams;
import com.jkapps.android.noshapp.yelppage.DisplayTask;
import com.jkapps.android.noshapp.yelppage.YelpWebView;
import com.jkapps.android.noshapp.yelppage.YelpWebViewClient;

import android.os.Bundle;
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
        DisplayParams displayParams = retrieveDisplayParams();
        initButtons(displayTask);
        displayTask.execute(displayParams);
    }

    private void initButtons(final DisplayTask displayTask) {
        initGoBackButton();
        initLikeButton();
        initDislikeButton(displayTask);
    }

    private void initDislikeButton(final DisplayTask displayTask) {
        (findViewById(R.id.Dislike)).setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displayTask.displayNextYelpPage(getYelpView());
                    }
                });
    }

    private void initLikeButton() {
        //TODO
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

    private DisplayParams retrieveDisplayParams() {
        return (new DisplayParams.Builder())
                .withCategory(getParam("CategoryParam"))
                .withRating(getParam("RatingParam"))
                .withDollars(getParam("DollarsParam"))
                .withLatitude(getParam("Latitude"))
                .withLongitude(getParam("Longitude"))
                .withYelpView(getYelpView())
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
