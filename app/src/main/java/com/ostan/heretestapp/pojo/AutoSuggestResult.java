package com.ostan.heretestapp.pojo;

/**
 * Created by marco on 17/12/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AutoSuggestResult implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("highlightedTitle")
    @Expose
    private String highlightedTitle;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("highlightedVicinity")
    @Expose
    private String highlightedVicinity;
    @SerializedName("position")
    @Expose
    private List<Double> position = null;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("type")
    @Expose
    private String type;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHighlightedTitle() {
        return highlightedTitle;
    }

    public void setHighlightedTitle(String highlightedTitle) {
        this.highlightedTitle = highlightedTitle;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getHighlightedVicinity() {
        return highlightedVicinity;
    }

    public void setHighlightedVicinity(String highlightedVicinity) {
        this.highlightedVicinity = highlightedVicinity;
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}