package com.arj.hicarehygiene.network.model.todayservice;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TodayResponse implements Parcelable {
    @SerializedName("IsSuccess") @Expose
    private Boolean IsSuccess;
    @SerializedName("Message") @Expose private String message = null;
    @SerializedName("Data") @Expose private List<TodayServiceData> data = null;

    protected TodayResponse(Parcel in) {
        byte tmpIsSuccess = in.readByte();
        IsSuccess = tmpIsSuccess == 0 ? null : tmpIsSuccess == 1;
        message = in.readString();
        data = in.createTypedArrayList(TodayServiceData.CREATOR);
    }

    public static final Creator<TodayResponse> CREATOR = new Creator<TodayResponse>() {
        @Override
        public TodayResponse createFromParcel(Parcel in) {
            return new TodayResponse(in);
        }

        @Override
        public TodayResponse[] newArray(int size) {
            return new TodayResponse[size];
        }
    };

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public void setSuccess(Boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TodayServiceData> getData() {
        return data;
    }

    public void setData(List<TodayServiceData> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (IsSuccess == null ? 0 : IsSuccess ? 1 : 2));
        dest.writeString(message);
        dest.writeTypedList(data);
    }
}
