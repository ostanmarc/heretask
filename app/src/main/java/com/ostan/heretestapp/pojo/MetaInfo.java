
package com.ostan.heretestapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MetaInfo implements Serializable {

    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("mapVersion")
    @Expose
    private String mapVersion;
    @SerializedName("moduleVersion")
    @Expose
    private String moduleVersion;
    @SerializedName("interfaceVersion")
    @Expose
    private String interfaceVersion;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMapVersion() {
        return mapVersion;
    }

    public void setMapVersion(String mapVersion) {
        this.mapVersion = mapVersion;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

}
