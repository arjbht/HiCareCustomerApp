package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityComplaintBinding;
import com.arj.hicarehygiene.fragments.ComplaintHistoryFragment;
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
        addFragment(ComplaintHistoryFragment.newInstance(), "DashboardActivity-ComplaintHistoryFragment");
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.stay, R.anim.slide_out_right);  //close animation
        super.onBackPressed();
        int fragment = getSupportFragmentManager().getBackStackEntryCount();
        Log.e("fragments", String.valueOf(fragment));
//        if (fragment < 1) {
//            finish();
//        } else {
//            getFragmentManager().popBackStack();
//        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


