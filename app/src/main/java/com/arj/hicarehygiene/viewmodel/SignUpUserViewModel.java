package com.arj.hicarehygiene.viewmodel;

/**
 * Created by yogi on 28/04/17.
 */

public class SignUpUserViewModel {

  public String firstName;
  public String lastName;
  public String email;
  public String phoneNumber;


  public SignUpUserViewModel() {
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.phoneNumber = "";

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

}
