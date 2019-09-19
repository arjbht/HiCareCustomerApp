package com.arj.hicarehygiene.network.model.complaint;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InteractionLogs implements Parcelable {
    @SerializedName("Main_Disposition_Logs__c")
    @Expose
    private String Main_Disposition_Logs__c;

    @SerializedName("Sub_Disposition_Logs__c")
    @Expose
    private String Sub_Disposition_Logs__c;

    @SerializedName("Remarks__c")
    @Expose
    private String Remarks__c;

    @SerializedName("Action_taken__c")
    @Expose
    private String Action_taken__c;

    @SerializedName("Created_Date__c")
    @Expose
    private String Created_Date__c;

    @SerializedName("SC_Status__c")
    @Expose
    private String SC_Status__c;



    protected InteractionLogs(Parcel in) {
        Main_Disposition_Logs__c = in.readString();
        Sub_Disposition_Logs__c = in.readString();
        Remarks__c = in.readString();
        Action_taken__c = in.readString();
        Created_Date__c = in.readString();
        SC_Status__c = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Main_Disposition_Logs__c);
        dest.writeString(Sub_Disposition_Logs__c);
        dest.writeString(Remarks__c);
        dest.writeString(Action_taken__c);
        dest.writeString(Created_Date__c);
        dest.writeString(SC_Status__c);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InteractionLogs> CREATOR = new Creator<InteractionLogs>() {
        @Override
        public InteractionLogs createFromParcel(Parcel in) {
            return new InteractionLogs(in);
        }

        @Override
        public InteractionLogs[] newArray(int size) {
            return new InteractionLogs[size];
        }
    };

    public String getMain_Disposition_Logs__c() {
        return Main_Disposition_Logs__c;
    }

    public void setMain_Disposition_Logs__c(String main_Disposition_Logs__c) {
        Main_Disposition_Logs__c = main_Disposition_Logs__c;
    }

    public String getSub_Disposition_Logs__c() {
        return Sub_Disposition_Logs__c;
    }

    public void setSub_Disposition_Logs__c(String sub_Disposition_Logs__c) {
        Sub_Disposition_Logs__c = sub_Disposition_Logs__c;
    }

    public String getRemarks__c() {
        return Remarks__c;
    }

    public void setRemarks__c(String remarks__c) {
        Remarks__c = remarks__c;
    }

    public String getAction_taken__c() {
        return Action_taken__c;
    }

    public void setAction_taken__c(String action_taken__c) {
        Action_taken__c = action_taken__c;
    }

    public String getCreated_Date__c() {
        return Created_Date__c;
    }

    public void setCreated_Date__c(String created_Date__c) {
        Created_Date__c = created_Date__c;
    }

    public String getSC_Status__c() {
        return SC_Status__c;
    }

    public void setSC_Status__c(String SC_Status__c) {
        this.SC_Status__c = SC_Status__c;
    }
}
