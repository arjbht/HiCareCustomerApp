package com.arj.hicarehygiene.network.model.userservices;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceTasks implements Parcelable {
    @SerializedName("CKSW__Status__c")
    @Expose
    private String Technician_Status;

    @SerializedName("CKSW__Appointment_Start_Date__c")
    @Expose
    private String CustomerAppointedStartDate;

    @SerializedName("CKSW__Appointment_Start_Time_AMPM__c")
    @Expose
    private String CustomerAppointedStartTime;

    @SerializedName("CKSW__Appointment_Finish_Date__c")
    @Expose
    private String CustomerAppointedFinishDate;
    @SerializedName("CKSW__Appointment_Finish_Time_AMPM__c")
    @Expose
    private String CustomerAppointedFinishTime;
    @SerializedName("CKSW__Assignment_Start_Date__c")
    @Expose
    private String CustomerAssignmentStartDate;
    @SerializedName("CKSW__Assignment_Start_Time_AMPM__c")
    @Expose
    private String CustomerAssignmentStartTime;
    @SerializedName("CKSW__Assignment_Finish_Date__c")
    @Expose
    private String CustomerAssignmentFinishDate;
    @SerializedName("CKSW__Assignment_Finish_Time_AMPM__c")
    @Expose
    private String CustomerAssignmentFinishTime;
    @SerializedName("CKSW__Assigned_Resource__r")
    @Expose
    private ServiceTechnicianDetails TechnicianDetail;

    protected ServiceTasks(Parcel in) {
        Technician_Status = in.readString();
        CustomerAppointedStartDate = in.readString();
        CustomerAppointedStartTime = in.readString();
        CustomerAppointedFinishDate = in.readString();
        CustomerAppointedFinishTime = in.readString();
        CustomerAssignmentStartDate = in.readString();
        CustomerAssignmentStartTime = in.readString();
        CustomerAssignmentFinishDate = in.readString();
        CustomerAssignmentFinishTime = in.readString();
    }

    public static final Creator<ServiceTasks> CREATOR = new Creator<ServiceTasks>() {
        @Override
        public ServiceTasks createFromParcel(Parcel in) {
            return new ServiceTasks(in);
        }

        @Override
        public ServiceTasks[] newArray(int size) {
            return new ServiceTasks[size];
        }
    };

    public String getTechnician_Status() {
        return Technician_Status;
    }

    public void setTechnician_Status(String technician_Status) {
        Technician_Status = technician_Status;
    }

    public String getCustomerAppointedStartDate() {
        return CustomerAppointedStartDate;
    }

    public void setCustomerAppointedStartDate(String customerAppointedStartDate) {
        CustomerAppointedStartDate = customerAppointedStartDate;
    }

    public String getCustomerAppointedStartTime() {
        return CustomerAppointedStartTime;
    }

    public void setCustomerAppointedStartTime(String customerAppointedStartTime) {
        CustomerAppointedStartTime = customerAppointedStartTime;
    }

    public String getCustomerAppointedFinishDate() {
        return CustomerAppointedFinishDate;
    }

    public void setCustomerAppointedFinishDate(String customerAppointedFinishDate) {
        CustomerAppointedFinishDate = customerAppointedFinishDate;
    }

    public String getCustomerAppointedFinishTime() {
        return CustomerAppointedFinishTime;
    }

    public void setCustomerAppointedFinishTime(String customerAppointedFinishTime) {
        CustomerAppointedFinishTime = customerAppointedFinishTime;
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

    public String getCustomerAssignmentFinishDate() {
        return CustomerAssignmentFinishDate;
    }

    public void setCustomerAssignmentFinishDate(String customerAssignmentFinishDate) {
        CustomerAssignmentFinishDate = customerAssignmentFinishDate;
    }

    public String getCustomerAssignmentFinishTime() {
        return CustomerAssignmentFinishTime;
    }

    public void setCustomerAssignmentFinishTime(String customerAssignmentFinishTime) {
        CustomerAssignmentFinishTime = customerAssignmentFinishTime;
    }

    public ServiceTechnicianDetails getTechnicianDetail() {
        return TechnicianDetail;
    }

    public void setTechnicianDetail(ServiceTechnicianDetails technicianDetail) {
        TechnicianDetail = technicianDetail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Technician_Status);
        dest.writeString(CustomerAppointedStartDate);
        dest.writeString(CustomerAppointedStartTime);
        dest.writeString(CustomerAppointedFinishDate);
        dest.writeString(CustomerAppointedFinishTime);
        dest.writeString(CustomerAssignmentStartDate);
        dest.writeString(CustomerAssignmentStartTime);
        dest.writeString(CustomerAssignmentFinishDate);
        dest.writeString(CustomerAssignmentFinishTime);
    }
}
