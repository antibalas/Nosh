package com.jkapps.android.noshapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.Business;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;
import com.jkapps.android.noshapp.apigateway.getyelpbizinfo.YelpBizClient;
import com.jkapps.android.noshapp.apigateway.getyelpbizinfo.YelpBizListener;

import android.location.Location;


import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;


import java.util.Stack;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    public static double latitude, longitude;

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
        //Create instance of GoogleApiClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testAPIGateway();
        testYelpBiz();

        listenerForRatingBar();
        listenerForDollarSpinner();
        listenerForCategorySpinner();
    }


    // Stuff for MainActivity UI
    RatingBar rating;
    TextView rating_param;
    public void listenerForRatingBar(){
        rating = (RatingBar) findViewById(R.id.ratingBar);
        rating_param = (TextView) findViewById(R.id.textView);

        rating.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener(){
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        rating_param.setText(String.valueOf(rating));
                    }
                }
        );
    }

    Spinner dollars;
    TextView dollar_param;
    public void listenerForDollarSpinner(){
        dollars = (Spinner) findViewById(R.id.spinner);
        dollar_param = (TextView) findViewById(R.id.textView2);

        dollars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dollar_param.setText(dollars.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    Spinner categories;
    TextView category_param;
    public void listenerForCategorySpinner(){
        categories = (Spinner) findViewById(R.id.spinner2);
        category_param = (TextView) findViewById(R.id.textView3);

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category_param.setText(categories.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {


    }

    public String latalong(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation!=null) {
            longitude = mLastLocation.getLongitude();
            latitude = mLastLocation.getLatitude();
        }
        String lat = Double.toString(latitude);
        String log = Double.toString(longitude);

        String coord = String.format("Lat: %s, long: %s", lat, log);

        return coord;
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
