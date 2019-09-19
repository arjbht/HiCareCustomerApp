package com.arj.hicarehygiene.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arjun on 11/05/19.
 */

public class BasicResponse {
  @SerializedName("IsSuccess") @Expose private Boolean IsSuccess;
  @SerializedName("Message") @Expose private String message = null;
  @SerializedName("Data") @Expose private Object data = null;

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

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
