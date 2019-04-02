package com.arj.hicarehygiene.viewmodel;

/**
 * Created by yogi on 30/04/17.
 */

public class VerifyOtpViewModel {

  private String otp;
  private String id;

  public VerifyOtpViewModel() {
    this.id = "";
    this.otp = "";
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
