package com.arj.hicarehygiene.network.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplaintType {
    @SerializedName("Name")
    @Expose
    private String Type = null;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
