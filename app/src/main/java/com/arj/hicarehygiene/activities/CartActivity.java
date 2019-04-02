package com.arj.hicarehygiene.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityCartBinding;
import com.arj.hicarehygiene.handler.UserCartClickHandler;

public class CartActivity extends AppCompatActivity implements UserCartClickHandler {
    ActivityCartBinding mactivityCartBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivityCartBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_cart);

        setSupportActionBar(mactivityCartBinding.toolbar);
        mactivityCartBinding.toolbar.setTitle("Cart");
        mactivityCartBinding.setHandler(CartActivity.this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onBookServiceClicked(View view) {
        Intent intent = new Intent(CartActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
