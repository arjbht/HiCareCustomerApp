package com.arj.hicarehygiene.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by arjun on 25/03/19.
 */

public class LoginResponse extends RealmObject {
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @PrimaryKey
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("IsPhoneNumberConfirmed")
    @Expose
    private String isPhoneNumberConfirmed;
    @SerializedName("IsEmailConfirmed")
    @Expose
    private String isEmailConfirmed;
    @SerializedName(".issued")
    @Expose
    private String issued;
    @SerializedName(".expires")
    @Expose
    private String expires;
    @SerializedName("OTP")
    @Expose
    private String OTP;
    @SerializedName("SFDCID")
    @Expose
    private String SFDCID;

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIsPhoneNumberConfirmed() {
        return isPhoneNumberConfirmed;
    }

    public void setIsPhoneNumberConfirmed(String isPhoneNumberConfirmed) {
        this.isPhoneNumberConfirmed = isPhoneNumberConfirmed;
    }

    public String getIsEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setIsEmailConfirmed(String isEmailConfirmed) {
        this.isEmailConfirmed = isEmailConfirmed;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getSFDCID() {
        return SFDCID;
    }

    public void setSFDCID(String SFDCID) {
        this.SFDCID = SFDCID;
    }
}
