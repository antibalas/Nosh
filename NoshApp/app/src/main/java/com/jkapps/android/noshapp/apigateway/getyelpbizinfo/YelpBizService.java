package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpBizService {
    @GET("getYelpBizInfo")
    Call<Response> getBizInfo(@Query("id") String bizID);
}