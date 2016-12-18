
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Leg implements Serializable {

    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("end")
    @Expose
    private End end;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("travelTime")
    @Expose
    private Integer travelTime;
    @SerializedName("maneuver")
    @Expose
    private List<Maneuver> maneuver = null;

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    public List<Maneuver> getManeuver() {
        return maneuver;
    }

    public void setManeuver(List<Maneuver> maneuver) {
        this.maneuver = maneuver;
    }

}
