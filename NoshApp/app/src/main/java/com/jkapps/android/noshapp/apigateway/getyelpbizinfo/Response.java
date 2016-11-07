package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("body")
    @Expose
    private Business body;
    @SerializedName("headers")
    @Expose
    private Headers headers;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    /**
     *
     * @return
     * The body
     */
    public Business getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(Business body) {
        this.body = body;
    }

    /**
     *
     * @return
     * The headers
     */
    public Headers getHeaders() {
        return headers;
    }

    /**
     *
     * @param headers
     * The headers
     */
    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    /**
     *
     * @return
     * The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     * The statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
