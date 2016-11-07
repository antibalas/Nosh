package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Hour {

    @SerializedName("hours_type")
    @Expose
    private String hoursType;
    @SerializedName("open")
    @Expose
    private List<Open> open = new ArrayList<Open>();
    @SerializedName("is_open_now")
    @Expose
    private Boolean isOpenNow;

    /**
     *
     * @return
     * The hoursType
     */
    public String getHoursType() {
        return hoursType;
    }

    /**
     *
     * @param hoursType
     * The hours_type
     */
    public void setHoursType(String hoursType) {
        this.hoursType = hoursType;
    }

    /**
     *
     * @return
     * The open
     */
    public List<Open> getOpen() {
        return open;
    }

    /**
     *
     * @param open
     * The open
     */
    public void setOpen(List<Open> open) {
        this.open = open;
    }

    /**
     *
     * @return
     * The isOpenNow
     */
    public Boolean getIsOpenNow() {
        return isOpenNow;
    }

    /**
     *
     * @param isOpenNow
     * The is_open_now
     */
    public void setIsOpenNow(Boolean isOpenNow) {
        this.isOpenNow = isOpenNow;
    }

}
