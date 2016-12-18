
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Start implements Serializable {

    @SerializedName("linkId")
    @Expose
    private String linkId;
    @SerializedName("mappedPosition")
    @Expose
    private MappedPosition mappedPosition;
    @SerializedName("originalPosition")
    @Expose
    private OriginalPosition originalPosition;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("spot")
    @Expose
    private Double spot;
    @SerializedName("sideOfStreet")
    @Expose
    private String sideOfStreet;
    @SerializedName("mappedRoadName")
    @Expose
    private String mappedRoadName;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("shapeIndex")
    @Expose
    private Integer shapeIndex;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public MappedPosition getMappedPosition() {
        return mappedPosition;
    }

    public void setMappedPosition(MappedPosition mappedPosition) {
        this.mappedPosition = mappedPosition;
    }

    public OriginalPosition getOriginalPosition() {
        return originalPosition;
    }

    public void setOriginalPosition(OriginalPosition originalPosition) {
        this.originalPosition = originalPosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSpot() {
        return spot;
    }

    public void setSpot(Double spot) {
        this.spot = spot;
    }

    public String getSideOfStreet() {
        return sideOfStreet;
    }

    public void setSideOfStreet(String sideOfStreet) {
        this.sideOfStreet = sideOfStreet;
    }

    public String getMappedRoadName() {
        return mappedRoadName;
    }

    public void setMappedRoadName(String mappedRoadName) {
        this.mappedRoadName = mappedRoadName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getShapeIndex() {
        return shapeIndex;
    }

    public void setShapeIndex(Integer shapeIndex) {
        this.shapeIndex = shapeIndex;
    }

}
