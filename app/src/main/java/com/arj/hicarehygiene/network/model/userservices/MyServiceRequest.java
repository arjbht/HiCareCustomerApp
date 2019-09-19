package com.arj.hicarehygiene.network.model.userservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyServiceRequest {

    @SerializedName("UserId")
    @Expose
    private String UserId;

    @SerializedName("OrderNo")
    @Expose
    private String OrderNo;

    @SerializedName("CaseNo")
    @Expose
    private String CaseNo;

    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;

    @SerializedName("DataType")
    @Expose
    private String DataType;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getCaseNo() {
        return CaseNo;
    }

    public void setCaseNo(String caseNo) {
        CaseNo = caseNo;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }
}
