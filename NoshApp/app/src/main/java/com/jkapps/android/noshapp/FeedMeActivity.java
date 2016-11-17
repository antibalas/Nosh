package com.jkapps.android.noshapp;

import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.Response;
import com.jkapps.android.noshapp.apigateway.Business;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;
import com.jkapps.android.noshapp.yelppageparams.YelpPageParams;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.util.Pair;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;
import java.util.Stack;

public class FeedMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initGoBackButton();
        YelpPageParams yelpPageParams = retrieveParams();
        setViewParams(yelpPageParams);
        final YelpPageDisplayerTask yelpPageDisplayerTask =
                new YelpPageDisplayerTask();
        yelpPageDisplayerTask.execute(yelpPageParams);
    }

    public void initGoBackButton(){
        Button GoBack = (Button) findViewById(R.id.GoBack);
        GoBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(FeedMeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private YelpPageParams retrieveParams() {
        return (new YelpPageParams.Builder())
                .withCategory(getParam("CategoryParam"))
                .withRating(getParam("RatingParam"))
                .withDollars(getParam("DollarsParam"))
                .withLatitude(getParam("Latitude"))
                .withLongitude(getParam("Longitude"))
                .build();
    }

    private String getParam(String param) {
        return getIntent().getExtras().getString(param);
    }

    private void setViewParam(final int id, final String param) {
        ((TextView) findViewById(id)).setText(param);
    }

    private void setViewParams(final YelpPageParams yelpPageParams){
        setViewParam(R.id.RatingView, yelpPageParams.getRating());
        setViewParam(R.id.DollarView, yelpPageParams.getDollars());
        setViewParam(R.id.CategoryView, yelpPageParams.getCategory());
    }

    private class YelpPageDisplayerTask extends
            AsyncTask<YelpPageParams, Void, Response> {

        List<Business> businessList;
        int businessListIndex = 0;

        //start over if we've gone through the whole list
        void displayNextYelpPage() {
            if (businessListIndex > businessList.size() - 1)
                businessListIndex = 0;
            getYelpView().loadUrl(businessList.get(businessListIndex++)
                                              .getUrl());
        }

        private WebView getYelpView() {
            final WebView yelpView = (WebView) findViewById(R.id.YelpView);
            yelpView.setWebViewClient(new WebViewClient());
            yelpView.getSettings().setJavaScriptEnabled(true); //yelp requires
            return yelpView;
        }

        @Override
        public Response doInBackground(YelpPageParams... yelpPageParams_) {
            YelpPageParams yelpPageParams = yelpPageParams_[0];

            Stack<Pair<String, String>> params = new Stack<>();

            params.push(new Pair<>("latitude", yelpPageParams.getLatitude()));
            params.push(new Pair<>("longitude", yelpPageParams.getLongitude()));
            //TODO push other params

            return hitGateway(params);
        }

        private Response hitGateway(Stack<Pair<String, String>> params) {
            try {
                return APIGateway.hitGateway("getFromYelp", params,
                        new GetFromYelpDeserializer());
            } catch (Exception e) { throw new RuntimeException(e); }
        }

        @Override
        public void onPostExecute(Response response) {
            businessList = response.getBusinesses();
            displayNextYelpPage();
        }
    }
}
