package com.jkapps.android.noshapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.jkapps.android.noshapp.feedme.FeedMeActivity;
import com.jkapps.android.noshapp.uber.Configuration;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Spinner;

import android.content.Intent;
import android.widget.Button;

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

    /*private static void testYelpBiz(){
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

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //initialize uber confiuration in a separate asyncTask
        (new Configuration()).execute();

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
       // testYelpBiz();
        OnFeedMe();
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

    public Pair<String,String> latalong(){
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


        //TODO: don't hard-code these
        //String lat = "36.9741";
        //String log = "-122.0308";
        Log.d("lat", lat);
        Log.d("log", log);
        return new Pair<>(lat,log);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void OnFeedMe(){
        Button FeedMe = (Button) findViewById(R.id.FeedMeButton);
        FeedMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, FeedMeActivity.class);
                Bundle params = new Bundle();

                RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
                String RatingParam = String.valueOf(rating.getRating());

                Spinner dollars = (Spinner) findViewById(R.id.spinner);
                String DollarParam = dollars.getSelectedItem().toString();

                Spinner categories = (Spinner) findViewById(R.id.spinner2);
                String CategoryParam = categories.getSelectedItem().toString();

                final Pair<String, String> latalong = latalong();

                params.putString("DollarParam", DollarParam);
                params.putString("RatingParam", RatingParam);
                params.putString("CategoryParam", CategoryParam);
                params.putString("Latitude", latalong.first);
                params.putString("Longitude", latalong.second);

                intent.putExtras(params);
                startActivity(intent);
            }
        });

    }


}
