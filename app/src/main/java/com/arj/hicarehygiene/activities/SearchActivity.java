package com.arj.hicarehygiene.activities;

import android.os.Bundle;
import android.view.View;


import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.handler.UserSearchClickHandler;

public class SearchActivity extends BaseActivity implements UserSearchClickHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    @Override
    public void onBackClicked(View view) {
        finish();
    }
}
