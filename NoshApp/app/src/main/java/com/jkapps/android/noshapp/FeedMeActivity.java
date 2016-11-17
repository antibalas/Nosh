package com.jkapps.android.noshapp;

import android.support.v7.app.AppCompatActivity;

import com.jkapps.android.noshapp.yelppage.DisplayParams;
import com.jkapps.android.noshapp.yelppage.DisplayTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FeedMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initGoBackButton();
        DisplayParams displayParams = retrieveDisplayParams();
        setViewParams(displayParams);
        final DisplayTask displayTask = new DisplayTask();
        displayTask.execute(displayParams);
    }

    public void initGoBackButton() {
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

    private WebView getYelpView() {
        final WebView yelpView = (WebView) findViewById(R.id.YelpView);
        yelpView.setWebViewClient(new WebViewClient());
        yelpView.getSettings().setJavaScriptEnabled(true); //yelp requires
        return yelpView;                                   //this
    }

    private void setViewParams(final DisplayParams displayParams) {
        setViewParam(R.id.RatingView, displayParams.getRating());
        setViewParam(R.id.DollarView, displayParams.getDollars());
        setViewParam(R.id.CategoryView, displayParams.getCategory());
    }

    private void setViewParam(final int id, final String param) {
        ((TextView) findViewById(id)).setText(param);
    }
}
