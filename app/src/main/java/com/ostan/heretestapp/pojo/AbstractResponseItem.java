package com.ostan.heretestapp.pojo;

import java.io.Serializable;

/**
 * Created by marco on 18/12/2016.
 */

public abstract class AbstractResponseItem implements Serializable {
    public abstract String getTitle();
    public abstract String getDescription();

}
