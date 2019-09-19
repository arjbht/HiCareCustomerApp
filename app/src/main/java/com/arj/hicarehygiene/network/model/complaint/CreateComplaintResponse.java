package com.arj.hicarehygiene.network.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateComplaintResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;
    @SerializedName("Message")
    @Expose
    private String Message;
    @SerializedName("Data")
    @Expose
    private String Data;

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public void setSuccess(Boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
