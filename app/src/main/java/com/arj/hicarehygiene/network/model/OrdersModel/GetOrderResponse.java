package com.arj.hicarehygiene.network.model.OrdersModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class GetOrderResponse implements Parcelable {
    @SerializedName("IsSuccess") @Expose private Boolean IsSuccess;
    @SerializedName("Message") @Expose private String message = null;
    @SerializedName("Data") @Expose private List<Orders> data = null;

    protected GetOrderResponse(Parcel in) {
        byte tmpIsSuccess = in.readByte();
        IsSuccess = tmpIsSuccess == 0 ? null : tmpIsSuccess == 1;
        message = in.readString();
        data = in.createTypedArrayList(Orders.CREATOR);
        in.readList(data, this.getClass().getClassLoader());

    }

    public static final Creator<GetOrderResponse> CREATOR = new Creator<GetOrderResponse>() {
        @Override
        public GetOrderResponse createFromParcel(Parcel in) {
            return new GetOrderResponse(in);
        }


        @Override
        public GetOrderResponse[] newArray(int size) {
            return new GetOrderResponse[size];
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

    public List<Orders> getData() {
        return data;
    }

    public void setData(List<Orders> data) {
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
