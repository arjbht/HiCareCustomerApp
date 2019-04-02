package com.arj.hicarehygiene.network.model.todayservice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodaysDetails implements Parcelable {
    @SerializedName("AppointmentTime")
    @Expose
    private String Slot_Time;

    protected TodaysDetails(Parcel in) {
        Slot_Time = in.readString();
    }

    public static final Creator<TodaysDetails> CREATOR = new Creator<TodaysDetails>() {
        @Override
        public TodaysDetails createFromParcel(Parcel in) {
            return new TodaysDetails(in);
        }

        @Override
        public TodaysDetails[] newArray(int size) {
            return new TodaysDetails[size];
        }
    };

    public String getSlot_Time() {
        return Slot_Time;
    }

    public void setSlot_Time(String slot_Time) {
        Slot_Time = slot_Time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Slot_Time);
    }
}
