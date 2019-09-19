package com.arj.hicarehygiene.network.model.ReferralModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddReferralRequest {
    @SerializedName("AccountId")
    @Expose
    private String accountId;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("ResourceId")
    @Expose
    private String resourceId;
    @SerializedName("ReferralName")
    @Expose
    private String referralName;
    @SerializedName("TaskId")
    @Expose
    private String taskId;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("InterestedService")
    @Expose
    private String InterestedService;
    @SerializedName("AlternateMobileNo")
    @Expose
    private String AlternateMobileNo;
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getInterestedService() {
        return InterestedService;
    }

    public void setInterestedService(String interestedService) {
        InterestedService = interestedService;
    }

    public String getAlternateMobileNo() {
        return AlternateMobileNo;
    }

    public void setAlternateMobileNo(String alternateMobileNo) {
        AlternateMobileNo = alternateMobileNo;
    }
}
