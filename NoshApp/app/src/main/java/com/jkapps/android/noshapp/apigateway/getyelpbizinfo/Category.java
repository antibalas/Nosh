package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     *
     * @return
     * The alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     *
     * @param alias
     * The alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}