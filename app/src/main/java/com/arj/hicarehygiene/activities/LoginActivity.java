package com.arj.hicarehygiene.activities;

import android.content.Intent;
import android.os.Bundle;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.fragments.LoginFragment;
import com.arj.hicarehygiene.utils.SharedPreferencesUtility;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedPreferencesUtility.getPrefBoolean(this, SharedPreferencesUtility.IS_USER_LOGIN)) {
            startActivity(new Intent(this, HomeActivity.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            finish();
        } else {
            addFragment(LoginFragment.newInstance(), "LoginTrealActivity-CreateRealFragment");
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        }
    }


}
