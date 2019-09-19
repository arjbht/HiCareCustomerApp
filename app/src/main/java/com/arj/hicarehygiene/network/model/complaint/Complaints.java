package com.arj.hicarehygiene.network.model.complaint;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Complaints implements Parcelable {

    @SerializedName("CaseNumber")
    @Expose
    private String CaseNumber;
    @SerializedName("Complaint_No__c")
    @Expose
    private String Complaint_No;
    @SerializedName("Complaint_Description__c")
    @Expose
    private String Complaint_Description;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Status")
    @Expose
    private String Status;
    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;
    @SerializedName("CreatedDate_Text")
    @Expose
    private String CreatedDate_Text;
    @SerializedName("ClosedDate")
    @Expose
    private String ClosedDate;
    @SerializedName("Case_Age_in_hours__c")
    @Expose
    private Integer Case_Age;
    @SerializedName("Refund_Requested__c")
    @Expose
    private Boolean Refund;
    @SerializedName("False_Complaint__c")
    @Expose
    private Boolean False_Complaint;
    @SerializedName("Customer_Feedback__c")
    @Expose
    private String Cust_Feedback;
    @SerializedName("Account_Type__c")
    @Expose
    private String AccountType;
    @SerializedName("Order_No__c")
    @Expose
    private String Order_No;

    @SerializedName("InteractionLogs")
    @Expose
    private List<InteractionLogs> InteractionLogs = null;


    protected Complaints(Parcel in) {
        CaseNumber = in.readString();
        Complaint_No = in.readString();
        Complaint_Description = in.readString();
        Description = in.readString();
        Status = in.readString();
        CreatedDate = in.readString();
        CreatedDate_Text = in.readString();
        ClosedDate = in.readString();
        if (in.readByte() == 0) {
            Case_Age = null;
        } else {
            Case_Age = in.readInt();
        }
        byte tmpRefund = in.readByte();
        Refund = tmpRefund == 0 ? null : tmpRefund == 1;
        byte tmpFalse_Complaint = in.readByte();
        False_Complaint = tmpFalse_Complaint == 0 ? null : tmpFalse_Complaint == 1;
        Cust_Feedback = in.readString();
        AccountType = in.readString();
        Order_No = in.readString();
    }

    public static final Creator<Complaints> CREATOR = new Creator<Complaints>() {
        @Override
        public Complaints createFromParcel(Parcel in) {
            return new Complaints(in);
        }

        @Override
        public Complaints[] newArray(int size) {
            return new Complaints[size];
        }
    };

    public String getComplaint_No() {
        return Complaint_No;
    }

    public void setComplaint_No(String complaint_No) {
        Complaint_No = complaint_No;
    }

    public String getComplaint_Description() {
        return Complaint_Description;
    }

    public void setComplaint_Description(String complaint_Description) {
        Complaint_Description = complaint_Description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getClosedDate() {
        return ClosedDate;
    }

    public void setClosedDate(String closedDate) {
        ClosedDate = closedDate;
    }

    public Integer getCase_Age() {
        return Case_Age;
    }

    public void setCase_Age(Integer case_Age) {
        Case_Age = case_Age;
    }

    public Boolean getRefund() {
        return Refund;
    }

    public void setRefund(Boolean refund) {
        Refund = refund;
    }

    public Boolean getFalse_Complaint() {
        return False_Complaint;
    }

    public void setFalse_Complaint(Boolean false_Complaint) {
        False_Complaint = false_Complaint;
    }

    public String getCust_Feedback() {
        return Cust_Feedback;
    }

    public void setCust_Feedback(String cust_Feedback) {
        Cust_Feedback = cust_Feedback;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getCreatedDate_Text() {
        return CreatedDate_Text;
    }

    public void setCreatedDate_Text(String createdDate_Text) {
        CreatedDate_Text = createdDate_Text;
    }

    public String getOrder_No() {
        return Order_No;
    }

    public void setOrder_No(String order_No) {
        Order_No = order_No;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<InteractionLogs> getInteractionLogs() {
        return InteractionLogs;
    }

    public void setInteractionLogs(List<InteractionLogs> interactionLogs) {
        InteractionLogs = interactionLogs;
    }

    public String getCaseNumber() {
        return CaseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        CaseNumber = caseNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(CaseNumber);
        parcel.writeString(Complaint_No);
        parcel.writeString(Complaint_Description);
        parcel.writeString(Description);
        parcel.writeString(Status);
        parcel.writeString(CreatedDate);
        parcel.writeString(CreatedDate_Text);
        parcel.writeString(ClosedDate);
        if (Case_Age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(Case_Age);
        }
        parcel.writeByte((byte) (Refund == null ? 0 : Refund ? 1 : 2));
        parcel.writeByte((byte) (False_Complaint == null ? 0 : False_Complaint ? 1 : 2));
        parcel.writeString(Cust_Feedback);
        parcel.writeString(AccountType);
        parcel.writeString(Order_No);
    }
}
