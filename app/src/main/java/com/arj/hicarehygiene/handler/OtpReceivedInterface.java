package com.arj.hicarehygiene.handler;

public interface OtpReceivedInterface {
    void onOtpReceived(String otp);
    void onOtpTimeout();
}
