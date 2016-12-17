package com.ostan.heretestapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marco on 17/12/2016.
 */

public class APIResult<T> {
    @SerializedName("results")
    @Expose
    public List<T> results;
}
