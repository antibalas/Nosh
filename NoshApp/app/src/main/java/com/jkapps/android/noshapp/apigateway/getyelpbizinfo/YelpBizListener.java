package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;


public interface YelpBizListener {

    void success(Business business);
    void failure();

}
