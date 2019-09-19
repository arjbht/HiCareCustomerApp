package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityComplaintDetailBinding;
import com.arj.hicarehygiene.fragments.ViewComplaintFragment;
import com.arj.hicarehygiene.network.model.complaint.Complaints;

public class ComplaintDetailActivity extends BaseActivity {
    ActivityComplaintDetailBinding mActivityComplaintDetailBinding;
    public static final String ARG_COMPLAINTS = "ARG_COMPLAINTS";
    private Complaints complaints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComplaintDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_complaint_detail);
        setSupportActionBar(mActivityComplaintDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        complaints = getIntent().getParcelableExtra(ARG_COMPLAINTS);
        addFragment(ViewComplaintFragment.newInstance(complaints),"ComplaintDetailActivity-ViewComplaintFragment");
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
