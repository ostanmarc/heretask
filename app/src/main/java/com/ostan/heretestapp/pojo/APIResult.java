package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marco on 17/12/2016.
 */

public class APIResult<T>  implements Serializable {
    @SerializedName("results")
    @Expose
    public List<T> results;
}
