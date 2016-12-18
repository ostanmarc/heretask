
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PublicTransportLine implements Serializable {

    @SerializedName("lineName")
    @Expose
    private String lineName;
    @SerializedName("lineForeground")
    @Expose
    private String lineForeground;
    @SerializedName("lineBackground")
    @Expose
    private String lineBackground;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineForeground() {
        return lineForeground;
    }

    public void setLineForeground(String lineForeground) {
        this.lineForeground = lineForeground;
    }

    public String getLineBackground() {
        return lineBackground;
    }

    public void setLineBackground(String lineBackground) {
        this.lineBackground = lineBackground;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
