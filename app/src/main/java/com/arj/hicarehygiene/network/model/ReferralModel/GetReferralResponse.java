package com.arj.hicarehygiene.network.model.ReferralModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetReferralResponse {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Data") @Expose
    private List<ReferralList> data = null;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public List<ReferralList> getData() {
        return data;
    }

    public void setData(List<ReferralList> data) {
        this.data = data;
    }
}
