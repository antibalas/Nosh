package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;


import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class YelpBizClient {

    private static final String BASE_URL = "https://zg5hawbfuh.execute-api.us-west-2.amazonaws.com/prod/";
    private static final String LOG_TAG = "Yelp Biz Client";

    public void getYelpBizInfo(String bizID, final YelpBizListener listener){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())	//parse Gson string
                .client(httpClient)	//add logging
                .build();

        YelpBizService service = retrofit.create(YelpBizService.class);

        Call<Response> queryResponseCall = service.getBizInfo(bizID);

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> retrofitResponse) {
                Log.i(LOG_TAG, "Inside Yelp Biz Client - onResponse function");
                Response response = retrofitResponse.body();
                listener.success(response.getBody());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                // Log error here since request failed
                // Make error message visible
                Log.i(LOG_TAG, "Inside Yelp Biz Client - onFailure function");
                listener.failure();
            }

        });

    }
}
