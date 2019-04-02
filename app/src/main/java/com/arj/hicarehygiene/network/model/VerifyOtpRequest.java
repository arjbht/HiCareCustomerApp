package com.arj.hicarehygiene.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yogi on 29/04/17.
 */

public class VerifyOtpRequest {
  @SerializedName("UserId") @Expose
  private String userId;
  @SerializedName("Code") @Expose
  private String code;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
