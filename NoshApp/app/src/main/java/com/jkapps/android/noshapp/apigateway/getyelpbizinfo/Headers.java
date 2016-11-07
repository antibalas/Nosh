package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headers {

    @SerializedName("Content-Type")
    @Expose
    private String contentType;

    /**
     *
     * @return
     * The contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     *
     * @param contentType
     * The Content-Type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
