package com.arj.hicarehygiene.network.model.userservices;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Service_Details implements Parcelable {
    @SerializedName("Sequence_No__c")
    @Expose
    private Integer Sequence_No;
    @SerializedName("Scheduled_Date_Time__c")
    @Expose
    private String Scheduled_Date;
    @SerializedName("Status__c")
    @Expose
    private String Status;
    @SerializedName("Completed_Date_Time__c")
    @Expose
    private String Completed_Date;
    @SerializedName("ServiceStep__c")
    @Expose
    private String Service_Step;
    @SerializedName("RelatedTasks")
    @Expose
    private List<ServiceTasks> task = null;

    protected Service_Details(Parcel in) {
        if (in.readByte() == 0) {
            Sequence_No = null;
        } else {
            Sequence_No = in.readInt();
        }
        Scheduled_Date = in.readString();
        Status = in.readString();
        Completed_Date = in.readString();
        Service_Step = in.readString();
    }

    public static final Creator<Service_Details> CREATOR = new Creator<Service_Details>() {
        @Override
        public Service_Details createFromParcel(Parcel in) {
            return new Service_Details(in);
        }

        @Override
        public Service_Details[] newArray(int size) {
            return new Service_Details[size];
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

    public List<ServiceTasks> getTask() {
        return task;
    }

    public void setTask(List<ServiceTasks> task) {
        this.task = task;
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
    }
}
