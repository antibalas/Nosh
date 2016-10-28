package com.jkapps.android.noshapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import com.jkapps.android.noshapp.apigateway.APIGateway;
import com.jkapps.android.noshapp.apigateway.deserializer.GetFromYelpDeserializer;

import java.util.Stack;

import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testAPIGateway();

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

}
