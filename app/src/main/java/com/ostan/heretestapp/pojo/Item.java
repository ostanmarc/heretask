
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Item extends AbstractResponseItem implements Serializable{

    @SerializedName("position")
    @Expose
    private List<Double> position = null;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("averageRating")
    @Expose
    private Integer averageRating;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("having")
    @Expose
    private List<Object> having = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public List<Object> getHaving() {
        return having;
    }

    public void setHaving(List<Object> having) {
        this.having = having;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
