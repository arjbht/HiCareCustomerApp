package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.complaint.Complaints;
import com.arj.hicarehygiene.network.model.complaint.InteractionLogs;

import java.util.List;

public class ComplaintHistoryViewModel implements Parcelable {

    private String CaseNumber;

    private String Complaint_No;

    private String Complaint_Description;

    private String Description;

    private String Status;

    private String CreatedDate;

    private String CreatedDate_Text;

    private String ClosedDate;

    private Integer Case_Age;

    private Boolean Refund;

    private Boolean False_Complaint;

    private String Cust_Feedback;

    private String AccountType;

    private String Order_No__c;

    private List<InteractionLogs> interactionList = null;


    public ComplaintHistoryViewModel() {
        this.CaseNumber = "NA";
        this.Complaint_No = "NA";
        this.Complaint_Description = "NA";
        this.Description = "NA";
        this.Status = "NA";
        this.CreatedDate = "NA";
        this.CreatedDate_Text = "NA";
        this.ClosedDate = "NA";
        this.Case_Age = 0;
        this.Refund = false;
        this.False_Complaint = false;
        this.Cust_Feedback = "NA";
        this.AccountType = "NA";
        this.Order_No__c = "NA";
    }


    protected ComplaintHistoryViewModel(Parcel in) {
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
        Order_No__c = in.readString();
        interactionList = in.createTypedArrayList(InteractionLogs.CREATOR);
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

    public String getCaseNumber() {
        return CaseNumber;
    }

    public void setCaseNumber(String caseNumber) {
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public String getOrder_No__c() {
        return Order_No__c;
    }

    public void setOrder_No__c(String order_No__c) {
        Order_No__c = order_No__c;
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

    public List<InteractionLogs> getInteractionList() {
        return interactionList;
    }

    public void setInteractionList(List<InteractionLogs> interactionList) {
        this.interactionList = interactionList;
    }

    public void clone(Complaints complaints) {
        this.CaseNumber = complaints.getCaseNumber();
        this.Complaint_No = complaints.getComplaint_No();
        this.Complaint_Description = complaints.getComplaint_Description();
        this.Description = complaints.getDescription();
        this.Status = complaints.getStatus();
        this.Order_No__c = complaints.getOrder_No();
        this.CreatedDate = complaints.getCreatedDate();
        this.CreatedDate_Text = complaints.getCreatedDate_Text();
        this.ClosedDate = complaints.getClosedDate();
        this.Case_Age = complaints.getCase_Age();
        this.Refund = complaints.getRefund();
        this.False_Complaint = complaints.getFalse_Complaint();
        this.Cust_Feedback = complaints.getCust_Feedback();
        this.AccountType = complaints.getAccountType();
        this.interactionList = complaints.getInteractionLogs();

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
        parcel.writeString(Order_No__c);
        parcel.writeTypedList(interactionList);
    }
}
