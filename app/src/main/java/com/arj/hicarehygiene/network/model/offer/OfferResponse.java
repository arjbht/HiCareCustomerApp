package com.arj.hicarehygiene.network.model.offer;

import com.arj.hicarehygiene.network.model.complaint.Complaints;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferResponse {

    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;
    @SerializedName("Message")
    @Expose
    private String message = null;
    @SerializedName("Data")
    @Expose
    private List<Offer> data = null;

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public void setSuccess(Boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Offer> getData() {
        return data;
    }

    public void setData(List<Offer> data) {
        this.data = data;
    }
}
