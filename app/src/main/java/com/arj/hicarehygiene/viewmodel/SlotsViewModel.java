package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.network.model.slots.TimeSlot;

public class SlotsViewModel implements Parcelable {
    public Boolean Selected;
    public String StartTime;
    public String FinishTime;


    public SlotsViewModel() {
        this.Selected = false;
        this.StartTime = "NA";
        this.FinishTime = "NA";
    }


    protected SlotsViewModel(Parcel in) {
        byte tmpSelected = in.readByte();
        Selected = tmpSelected == 0 ? null : tmpSelected == 1;
        StartTime = in.readString();
        FinishTime = in.readString();
    }

    public static final Creator<SlotsViewModel> CREATOR = new Creator<SlotsViewModel>() {
        @Override
        public SlotsViewModel createFromParcel(Parcel in) {
            return new SlotsViewModel(in);
        }

        @Override
        public SlotsViewModel[] newArray(int size) {
            return new SlotsViewModel[size];
        }
    };

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public void clone(TimeSlot slot) {
        this.Selected = slot.getSelected();
        this.StartTime = slot.getStartTime();
        this.FinishTime = slot.getFinishTime();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (Selected == null ? 0 : Selected ? 1 : 2));
        parcel.writeString(StartTime);
        parcel.writeString(FinishTime);
    }
}
