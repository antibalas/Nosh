package com.jkapps.android.noshapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        GoToMain();
        ViewParam();
        WebView();
    }

    public void GoToMain(){
        Button GoBack = (Button) findViewById(R.id.GoBack);
        GoBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ViewParam(){
        String RatingParam = getIntent().getExtras().getString("RatingParam");
        TextView rating_param = (TextView) findViewById(R.id.RatingView);
        rating_param.setText(RatingParam);

        String DollarParam = getIntent().getExtras().getString("DollarParam");
        TextView dollar_param = (TextView) findViewById(R.id.DollarView);
        dollar_param.setText(DollarParam);

        String CategoryParam = getIntent().getExtras().getString("CategoryParam");
        TextView category_param = (TextView) findViewById(R.id.CategoryView);
        category_param.setText(CategoryParam);
    }

    public void WebView(){
        WebView YelpView = (WebView) findViewById(R.id.YelpView);
        YelpView.setWebViewClient(new WebViewClient());
        YelpView.loadUrl("https://www.google.com/");
        
    }
}
