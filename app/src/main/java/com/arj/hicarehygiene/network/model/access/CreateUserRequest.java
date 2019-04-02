package com.arj.hicarehygiene.network.model.access;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yogi on 29/04/17.
 */

public class CreateUserRequest {
  @SerializedName("Email") @Expose
  private String email;
  @SerializedName("PhoneNumber") @Expose
  private String phoneNumber;
  @SerializedName("FirstName") @Expose
  private String firstName;
  @SerializedName("LastName") @Expose
  private String lastName;
  @SerializedName("Password") @Expose
  private String password;
  @SerializedName("ConfirmPassword") @Expose
  private String confirmPassword;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
