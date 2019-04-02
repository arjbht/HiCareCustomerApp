package com.arj.hicarehygiene.network.model.complaint;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ComplaintHistoryResponse implements Parcelable {
    @SerializedName("IsSuccess")
    @Expose
    private Boolean IsSuccess;
    @SerializedName("Message")
    @Expose
    private String message = null;
    @SerializedName("Data")
    @Expose
    private List<Complaints> data = null;

    protected ComplaintHistoryResponse(Parcel in) {
        byte tmpIsSuccess = in.readByte();
        IsSuccess = tmpIsSuccess == 0 ? null : tmpIsSuccess == 1;
        message = in.readString();
        data = in.createTypedArrayList(Complaints.CREATOR);
    }

    public static final Creator<ComplaintHistoryResponse> CREATOR = new Creator<ComplaintHistoryResponse>() {
        @Override
        public ComplaintHistoryResponse createFromParcel(Parcel in) {
            return new ComplaintHistoryResponse(in);
        }

        @Override
        public ComplaintHistoryResponse[] newArray(int size) {
            return new ComplaintHistoryResponse[size];
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

    public List<Complaints> getData() {
        return data;
    }

    public void setData(List<Complaints> data) {
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
