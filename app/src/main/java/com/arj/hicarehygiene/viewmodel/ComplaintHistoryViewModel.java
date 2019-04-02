package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.complaint.Complaints;

public class ComplaintHistoryViewModel implements Parcelable {

    private Integer CaseNumber;

    private String Complaint_No;

    private String Complaint_Description;

    private String Status;

    private String CreatedDate;

    private String ClosedDate;

    private Integer Case_Age;

    private Boolean Refund;

    private Boolean False_Complaint;

    private String Cust_Feedback;

    private String AccountType;


    public ComplaintHistoryViewModel(){
        this.CaseNumber = 0;
        this.Complaint_No = "NA";
        this.Complaint_Description = "NA";
        this.Status = "NA";
        this.CreatedDate = "NA";
        this.ClosedDate = "NA";
        this.Case_Age = 0;
        this.Refund = false;
        this.False_Complaint = false;
        this.Cust_Feedback = "NA";
        this.AccountType = "NA";
    }

    protected ComplaintHistoryViewModel(Parcel in) {
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

    public static final Creator<ComplaintHistoryViewModel> CREATOR = new Creator<ComplaintHistoryViewModel>() {
        @Override
        public ComplaintHistoryViewModel createFromParcel(Parcel in) {
            return new ComplaintHistoryViewModel(in);
        }

        @Override
        public ComplaintHistoryViewModel[] newArray(int size) {
            return new ComplaintHistoryViewModel[size];
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

    public void clone(Complaints complaints) {
        this.CaseNumber = complaints.getCaseNumber();
        this.Complaint_No = complaints.getComplaint_No();
        this.Complaint_Description = complaints.getComplaint_Description();
        this.Status = complaints.getStatus();
        this.CreatedDate = complaints.getCreatedDate();
        this.ClosedDate = complaints.getClosedDate();
        this.Case_Age = complaints.getCase_Age();
        this.Refund = complaints.getRefund();
        this.False_Complaint = complaints.getFalse_Complaint();
        this.Cust_Feedback = complaints.getCust_Feedback();
        this.AccountType = complaints.getAccountType();

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
