package com.jkapps.android.noshapp;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;

import android.location.Location;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected TextView mLatitudeText;
    protected TextView mLongitudeText;

    private static void testAPIGateway() {
        // we should do this with an AsyncTask
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Stack<Pair<String, String>> params = new Stack<>();
                    params.push(new Pair<>("location", "Santa Cruz CA"));
                    params.push(new Pair<>("limit", "3"));
                    Log.d("onCreate", APIGateway.hitGateway
                            ("annie/getFromYelp", params,
                                    new GetFromYelpDeserializer()).getBody());
                } catch (Exception e) {
                    Log.d("onCreate", Log.getStackTraceString(e));
                }
            }
        }).start();
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
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
