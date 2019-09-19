package com.arj.hicarehygiene.network.model.access;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arjun on 29/04/17.
 */

public class CreateUserResonse  {
  @SerializedName("Id") @Expose
  private String id;
  @SerializedName("UserName") @Expose
  private String userName;
  @SerializedName("FullName") @Expose
  private String fullName;
  @SerializedName("Email") @Expose
  private String email;
  @SerializedName("EmailConfirmed") @Expose
  private Boolean emailConfirmed;
  @SerializedName("PhoneNumber") @Expose
  private String phoneNumber;
  @SerializedName("PhoneNumberConfirmed") @Expose
  private Boolean phoneNumberConfirmed;
  @SerializedName("Claims") @Expose
  private List<Object> claims = null;
  @SerializedName("Acknowledge") @Expose
  private Integer acknowledge;
  @SerializedName("ResponseCode") @Expose
  private Object responseCode;
  @SerializedName("ResponseMessage") @Expose
  private String responseMessage;
  @SerializedName("Data") @Expose
  private Object data;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getEmailConfirmed() {
    return emailConfirmed;
  }

  public void setEmailConfirmed(Boolean emailConfirmed) {
    this.emailConfirmed = emailConfirmed;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Boolean getPhoneNumberConfirmed() {
    return phoneNumberConfirmed;
  }

  public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
    this.phoneNumberConfirmed = phoneNumberConfirmed;
  }

  public List<Object> getClaims() {
    return claims;
  }

  public void setClaims(List<Object> claims) {
    this.claims = claims;
  }

  public Integer getAcknowledge() {
    return acknowledge;
  }

  public void setAcknowledge(Integer acknowledge) {
    this.acknowledge = acknowledge;
  }

  public Object getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(Object responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
