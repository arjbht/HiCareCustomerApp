package com.arj.hicarehygiene.network.model.todayservice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TodayServiceData implements Parcelable {
    @SerializedName("Order_Number__c")
    @Expose
    private String Order_No;
    @SerializedName("Service_Plan__c")
    @Expose
    private String Service_Plan;
    @SerializedName("ServiceStep__c")
    @Expose
    private String Service_Step;
    @SerializedName("RelatedTasks")
    @Expose
    private List<TodaysDetails> todaysDetails = null;

    protected TodayServiceData(Parcel in) {
        Order_No = in.readString();
        Service_Plan = in.readString();
        Service_Step = in.readString();
    }

    public static final Creator<TodayServiceData> CREATOR = new Creator<TodayServiceData>() {
        @Override
        public TodayServiceData createFromParcel(Parcel in) {
            return new TodayServiceData(in);
        }

        @Override
        public TodayServiceData[] newArray(int size) {
            return new TodayServiceData[size];
        }
    };

    public String getOrder_No() {
        return Order_No;
    }

    public void setOrder_No(String order_No) {
        Order_No = order_No;
    }

    public String getService_Plan() {
        return Service_Plan;
    }

    public void setService_Plan(String service_Plan) {
        Service_Plan = service_Plan;
    }

    public String getService_Step() {
        return Service_Step;
    }

    public void setService_Step(String service_Step) {
        Service_Step = service_Step;
    }

    public List<TodaysDetails> getTodaysDetails() {
        return todaysDetails;
    }

    public void setTodaysDetails(List<TodaysDetails> todaysDetails) {
        this.todaysDetails = todaysDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Order_No);
        dest.writeString(Service_Plan);
        dest.writeString(Service_Step);
    }
}
