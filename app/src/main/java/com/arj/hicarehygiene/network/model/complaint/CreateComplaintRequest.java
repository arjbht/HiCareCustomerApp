package com.arj.hicarehygiene.network.model.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateComplaintRequest {
    @SerializedName("OrderNo")
    @Expose
    private String OrderNo;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("ComplaintType")
    @Expose
    private String ComplaintType;
    @SerializedName("Remarks")
    @Expose
    private String Remarks;
    @SerializedName("DataType")
    @Expose
    private String DataType;

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getComplaintType() {
        return ComplaintType;
    }

    public void setComplaintType(String complaintType) {
        ComplaintType = complaintType;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }
}
