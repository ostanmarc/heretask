
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Maneuver implements Serializable {

    @SerializedName("position")
    @Expose
    private Position position;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("travelTime")
    @Expose
    private Integer travelTime;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("_type")
    @Expose
    private String type;
    @SerializedName("stopName")
    @Expose
    private String stopName;
    @SerializedName("nextRoadName")
    @Expose
    private String nextRoadName;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getNextRoadName() {
        return nextRoadName;
    }

    public void setNextRoadName(String nextRoadName) {
        this.nextRoadName = nextRoadName;
    }

}
