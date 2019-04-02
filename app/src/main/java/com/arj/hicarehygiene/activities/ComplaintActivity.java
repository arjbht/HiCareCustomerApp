package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityComplaintBinding;
import com.arj.hicarehygiene.fragments.FragmentComplaint;

public class ComplaintActivity extends BaseActivity {
    ActivityComplaintBinding activityComplaintBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComplaintBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_complaint);
        setSupportActionBar(activityComplaintBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addFragment(FragmentComplaint.newInstance(), "ComplaintActivity-CompplaintFragment");
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

    }

        @Override
        public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}


