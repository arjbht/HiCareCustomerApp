package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.userservices.Service_Details;

public class ServiceViewModel implements Parcelable {
    private Integer Sequence_No;
    private String Order_Number__c;
    private String Service_Plan__c;
    private String Scheduled_Date;
    private String Status;
    private String Completed_Date;
    private String Service_Step;
    private String TechnicianStatus;
    private String CustomerAppointmentStartDate;
    private String CustomerAppointmentStartTime;
    private String CustomerAppointmentEndTime;
    private String CustomerAppointmentEndDate;
    private String CustomerAssignmentStartDate;
    private String CustomerAssignmentStartTime;
    private String CustomerAssignmentEndTime;
    private String CustomerAssignmentEndDate;
    private String TechnicianId;
    private String TechnicianName;
    private String TechnicianMobile;
    private String Service_Group_Code__c;

    public ServiceViewModel() {
        this.Sequence_No = 0;
        this.Order_Number__c = "NA";
        this.Scheduled_Date = "NA";
        this.Status = "NA";
        this.Completed_Date = "NA";
        this.Service_Step = "NA";
        this.CustomerAppointmentStartDate = "NA";
        this.CustomerAppointmentStartTime = "NA";
        this.CustomerAppointmentEndTime = "NA";
        this.CustomerAppointmentEndDate = "NA";
        this.CustomerAssignmentStartDate = "NA";
        this.CustomerAssignmentStartTime = "NA";
        this.CustomerAssignmentEndTime = "NA";
        this.CustomerAssignmentEndDate = "NA";
        this.TechnicianId = "NA";
        this.TechnicianName = "NA";
        this.TechnicianMobile = "NA";
        this.TechnicianStatus = "NA";
        this.Service_Plan__c = "NA";
        this.Service_Group_Code__c = "NA";
    }


    protected ServiceViewModel(Parcel in) {
        if (in.readByte() == 0) {
            Sequence_No = null;
        } else {
            Sequence_No = in.readInt();
        }
        Order_Number__c = in.readString();
        Service_Plan__c = in.readString();
        Scheduled_Date = in.readString();
        Status = in.readString();
        Completed_Date = in.readString();
        Service_Step = in.readString();
        TechnicianStatus = in.readString();
        CustomerAppointmentStartDate = in.readString();
        CustomerAppointmentStartTime = in.readString();
        CustomerAppointmentEndTime = in.readString();
        CustomerAppointmentEndDate = in.readString();
        CustomerAssignmentStartDate = in.readString();
        CustomerAssignmentStartTime = in.readString();
        CustomerAssignmentEndTime = in.readString();
        CustomerAssignmentEndDate = in.readString();
        TechnicianId = in.readString();
        TechnicianName = in.readString();
        TechnicianMobile = in.readString();
        Service_Group_Code__c = in.readString();
    }

    public static final Creator<ServiceViewModel> CREATOR = new Creator<ServiceViewModel>() {
        @Override
        public ServiceViewModel createFromParcel(Parcel in) {
            return new ServiceViewModel(in);
        }

        @Override
        public ServiceViewModel[] newArray(int size) {
            return new ServiceViewModel[size];
        }
    };

    public Integer getSequence_No() {
        return Sequence_No;
    }

    public void setSequence_No(Integer sequence_No) {
        Sequence_No = sequence_No;
    }

    public String getScheduled_Date() {
        return Scheduled_Date;
    }

    public void setScheduled_Date(String scheduled_Date) {
        Scheduled_Date = scheduled_Date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCompleted_Date() {
        return Completed_Date;
    }

    public void setCompleted_Date(String completed_Date) {
        Completed_Date = completed_Date;
    }

    public String getService_Step() {
        return Service_Step;
    }

    public void setService_Step(String service_Step) {
        Service_Step = service_Step;
    }

    public String getTechnicianStatus() {
        return TechnicianStatus;
    }

    public void setTechnicianStatus(String technicianStatus) {
        TechnicianStatus = technicianStatus;
    }

    public String getCustomerAppointmentStartDate() {
        return CustomerAppointmentStartDate;
    }

    public void setCustomerAppointmentStartDate(String customerAppointmentStartDate) {
        CustomerAppointmentStartDate = customerAppointmentStartDate;
    }

    public String getCustomerAppointmentStartTime() {
        return CustomerAppointmentStartTime;
    }

    public void setCustomerAppointmentStartTime(String customerAppointmentStartTime) {
        CustomerAppointmentStartTime = customerAppointmentStartTime;
    }

    public String getCustomerAppointmentEndTime() {
        return CustomerAppointmentEndTime;
    }

    public void setCustomerAppointmentEndTime(String customerAppointmentEndTime) {
        CustomerAppointmentEndTime = customerAppointmentEndTime;
    }

    public String getCustomerAppointmentEndDate() {
        return CustomerAppointmentEndDate;
    }

    public void setCustomerAppointmentEndDate(String customerAppointmentEndDate) {
        CustomerAppointmentEndDate = customerAppointmentEndDate;
    }

    public String getCustomerAssignmentStartDate() {
        return CustomerAssignmentStartDate;
    }

    public void setCustomerAssignmentStartDate(String customerAssignmentStartDate) {
        CustomerAssignmentStartDate = customerAssignmentStartDate;
    }

    public String getCustomerAssignmentStartTime() {
        return CustomerAssignmentStartTime;
    }

    public void setCustomerAssignmentStartTime(String customerAssignmentStartTime) {
        CustomerAssignmentStartTime = customerAssignmentStartTime;
    }

    public String getCustomerAssignmentEndTime() {
        return CustomerAssignmentEndTime;
    }

    public void setCustomerAssignmentEndTime(String customerAssignmentEndTime) {
        CustomerAssignmentEndTime = customerAssignmentEndTime;
    }

    public String getCustomerAssignmentEndDate() {
        return CustomerAssignmentEndDate;
    }

    public void setCustomerAssignmentEndDate(String customerAssignmentEndDate) {
        CustomerAssignmentEndDate = customerAssignmentEndDate;
    }

    public String getTechnicianId() {
        return TechnicianId;
    }

    public void setTechnicianId(String technicianId) {
        TechnicianId = technicianId;
    }

    public String getTechnicianName() {
        return TechnicianName;
    }

    public void setTechnicianName(String technicianName) {
        TechnicianName = technicianName;
    }

    public String getTechnicianMobile() {
        return TechnicianMobile;
    }

    public void setTechnicianMobile(String technicianMobile) {
        TechnicianMobile = technicianMobile;
    }

    public String getOrder_Number__c() {
        return Order_Number__c;
    }

    public void setOrder_Number__c(String order_Number__c) {
        Order_Number__c = order_Number__c;
    }

    public String getService_Plan__c() {
        return Service_Plan__c;
    }

    public void setService_Plan__c(String service_Plan__c) {
        Service_Plan__c = service_Plan__c;
    }

    public String getService_Group_Code__c() {
        return Service_Group_Code__c;
    }

    public void setService_Group_Code__c(String service_Group_Code__c) {
        Service_Group_Code__c = service_Group_Code__c;
    }

    public void clone(Service_Details service) {
        this.Sequence_No = service.getSequence_No();
        this.Scheduled_Date = service.getScheduled_Date();
        this.Completed_Date = service.getCompleted_Date();
        this.Status = service.getStatus();
        this.Service_Step = service.getService_Step();
        this.Order_Number__c = service.getOrder_Number__c();
        this.Service_Plan__c = service.getService_Plan__c();
        this.Service_Group_Code__c = service.getService_Group_Code__c();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (Sequence_No == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(Sequence_No);
        }
        parcel.writeString(Order_Number__c);
        parcel.writeString(Service_Plan__c);
        parcel.writeString(Scheduled_Date);
        parcel.writeString(Status);
        parcel.writeString(Completed_Date);
        parcel.writeString(Service_Step);
        parcel.writeString(TechnicianStatus);
        parcel.writeString(CustomerAppointmentStartDate);
        parcel.writeString(CustomerAppointmentStartTime);
        parcel.writeString(CustomerAppointmentEndTime);
        parcel.writeString(CustomerAppointmentEndDate);
        parcel.writeString(CustomerAssignmentStartDate);
        parcel.writeString(CustomerAssignmentStartTime);
        parcel.writeString(CustomerAssignmentEndTime);
        parcel.writeString(CustomerAssignmentEndDate);
        parcel.writeString(TechnicianId);
        parcel.writeString(TechnicianName);
        parcel.writeString(TechnicianMobile);
        parcel.writeString(Service_Group_Code__c);
    }
}
