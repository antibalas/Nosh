package com.jkapps.android.noshapp.apigateway.getyelpbizinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Open {

    @SerializedName("is_overnight")
    @Expose
    private Boolean isOvernight;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("start")
    @Expose
    private String start;

    /**
     *
     * @return
     * The isOvernight
     */
    public Boolean getIsOvernight() {
        return isOvernight;
    }

    /**
     *
     * @param isOvernight
     * The is_overnight
     */
    public void setIsOvernight(Boolean isOvernight) {
        this.isOvernight = isOvernight;
    }

    /**
     *
     * @return
     * The end
     */
    public String getEnd() {
        return end;
    }

    /**
     *
     * @param end
     * The end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     *
     * @return
     * The day
     */
    public Integer getDay() {
        return day;
    }

    /**
     *
     * @param day
     * The day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     *
     * @return
     * The start
     */
    public String getStart() {
        return start;
    }

    /**
     *
     * @param start
     * The start
     */
    public void setStart(String start) {
        this.start = start;
    }

}