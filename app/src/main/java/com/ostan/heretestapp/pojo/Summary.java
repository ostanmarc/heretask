
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Summary implements Serializable {

    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("baseTime")
    @Expose
    private Integer baseTime;
    @SerializedName("flags")
    @Expose
    private List<String> flags = null;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("travelTime")
    @Expose
    private Integer travelTime;
    @SerializedName("departure")
    @Expose
    private String departure;
    @SerializedName("_type")
    @Expose
    private String type;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(Integer baseTime) {
        this.baseTime = baseTime;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
