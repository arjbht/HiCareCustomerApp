package com.arj.hicarehygiene.handler;

import android.view.View;

public interface UserLoginClickHandler {

    void onLoginClicked(View view);

    void onForgotPasswordClicked(View view);

    void onRegisterClicked(View view);

    void onResendOtpClicked(View view);
}
