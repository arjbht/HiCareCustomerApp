package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityDashboardBinding;
import com.arj.hicarehygiene.fragments.AddReferralFragment;
import com.arj.hicarehygiene.fragments.ComplaintHistoryFragment;
import com.arj.hicarehygiene.fragments.MyServicesFragment;
import com.arj.hicarehygiene.fragments.TrackServiceFragment;
import com.arj.hicarehygiene.fragments.VoucherFragment;

public class DashboardActivity extends BaseActivity {
    ActivityDashboardBinding mActivityDashboardBinding;
    public static String ARG_DASHBOARD = "ARG_DASHBOARD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDashboardBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        setSupportActionBar(mActivityDashboardBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String transaction = getIntent().getStringExtra(ARG_DASHBOARD);

        if (transaction.equals("referral")) {
            addFragment(AddReferralFragment.newInstance(), "DashboardActivity-AddReferralFragment");
        } else if (transaction.equals("complaint")) {
            addFragment(ComplaintHistoryFragment.newInstance(), "DashboardActivity-ComplaintHistoryFragment");
        } else if (transaction.equals("voucher")) {
            addFragment(VoucherFragment.newInstance(), "DashboardActivity-VoucherFragment");
        }else if(transaction.equals("service")){
            addFragment(MyServicesFragment.newInstance(), "DashboardActivity-MyServiceFragment");
        }else if(transaction.equals("track")){
            addFragment(TrackServiceFragment.newInstance(), "DashboardActivity-TrackService");
        }
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.stay, R.anim.slide_out_right);  //close animation
        super.onBackPressed();
        int fragment = getSupportFragmentManager().getBackStackEntryCount();
        Log.e("fragments", String.valueOf(fragment));
        if (fragment < 1) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
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
