package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.userservices.Service_Details;

public class ServiceViewModel implements Parcelable {
    private Integer Sequence_No;
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

    public ServiceViewModel() {
        this.Sequence_No = 0;
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
    }


    protected ServiceViewModel(Parcel in) {
        if (in.readByte() == 0) {
            Sequence_No = null;
        } else {
            Sequence_No = in.readInt();
        }
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

    public void clone(Service_Details service) {
        this.Sequence_No = service.getSequence_No();
        this.Scheduled_Date = service.getScheduled_Date();
        this.Completed_Date = service.getCompleted_Date();
        this.Status = service.getStatus();
        this.Service_Step = service.getService_Step();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (Sequence_No == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Sequence_No);
        }
        dest.writeString(Scheduled_Date);
        dest.writeString(Status);
        dest.writeString(Completed_Date);
        dest.writeString(Service_Step);
        dest.writeString(TechnicianStatus);
        dest.writeString(CustomerAppointmentStartDate);
        dest.writeString(CustomerAppointmentStartTime);
        dest.writeString(CustomerAppointmentEndTime);
        dest.writeString(CustomerAppointmentEndDate);
        dest.writeString(CustomerAssignmentStartDate);
        dest.writeString(CustomerAssignmentStartTime);
        dest.writeString(CustomerAssignmentEndTime);
        dest.writeString(CustomerAssignmentEndDate);
        dest.writeString(TechnicianId);
        dest.writeString(TechnicianName);
        dest.writeString(TechnicianMobile);
    }
}
