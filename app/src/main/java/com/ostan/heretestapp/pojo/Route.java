
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Route extends AbstractResponseItem implements Serializable{

    @SerializedName("waypoint")
    @Expose
    private List<Waypoint> waypoint = null;
    @SerializedName("mode")
    @Expose
    private Mode mode;
    @SerializedName("leg")
    @Expose
    private List<Leg> leg = null;
    @SerializedName("publicTransportLine")
    @Expose
    private List<PublicTransportLine> publicTransportLine = null;
    @SerializedName("summary")
    @Expose
    private Summary summary;

    public List<Waypoint> getWaypoint() {
        return waypoint;
    }

    public void setWaypoint(List<Waypoint> waypoint) {
        this.waypoint = waypoint;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<Leg> getLeg() {
        return leg;
    }

    public void setLeg(List<Leg> leg) {
        this.leg = leg;
    }

    public List<PublicTransportLine> getPublicTransportLine() {
        return publicTransportLine;
    }

    public void setPublicTransportLine(List<PublicTransportLine> publicTransportLine) {
        this.publicTransportLine = publicTransportLine;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @Override
    public String getTitle() {
        String title = "";
        for (Leg iterator: leg) {
            title += iterator.getStart().getLabel();
        }
        return title;
    }

    @Override
    public String getDescription() {
        return getSummary().getText();
    }
}
