package com.arj.hicarehygiene.network.model.complaint;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Complaints implements Parcelable {

    @SerializedName("CaseNumber")
    @Expose
    private Integer CaseNumber;
    @SerializedName("Complaint_No__c")
    @Expose
    private String Complaint_No;
    @SerializedName("Complaint_Description__c")
    @Expose
    private String Complaint_Description;
    @SerializedName("Status")
    @Expose
    private String Status;
    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;
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

    protected Complaints(Parcel in) {
        if (in.readByte() == 0) {
            CaseNumber = null;
        } else {
            CaseNumber = in.readInt();
        }
        Complaint_No = in.readString();
        Complaint_Description = in.readString();
        Status = in.readString();
        CreatedDate = in.readString();
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

    public Integer getCaseNumber() {
        return CaseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        CaseNumber = caseNumber;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (CaseNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(CaseNumber);
        }
        dest.writeString(Complaint_No);
        dest.writeString(Complaint_Description);
        dest.writeString(Status);
        dest.writeString(CreatedDate);
        dest.writeString(ClosedDate);
        if (Case_Age == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Case_Age);
        }
        dest.writeByte((byte) (Refund == null ? 0 : Refund ? 1 : 2));
        dest.writeByte((byte) (False_Complaint == null ? 0 : False_Complaint ? 1 : 2));
        dest.writeString(Cust_Feedback);
        dest.writeString(AccountType);
    }
}
