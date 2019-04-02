package com.arj.hicarehygiene.viewmodel;

public class UserLoginViewModel {
    public String username;
    public String otp;
    private String id;

    public UserLoginViewModel() {
        username = "";
        otp = "";
        id = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
