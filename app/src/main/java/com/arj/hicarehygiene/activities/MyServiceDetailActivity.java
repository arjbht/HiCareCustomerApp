package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityMyServiceDetailBinding;
import com.arj.hicarehygiene.fragments.MyServiceDetailsFragment;
import com.arj.hicarehygiene.fragments.MyServicesFragment;
import com.arj.hicarehygiene.network.model.userservices.ServiceTasks;

import java.util.List;

public class MyServiceDetailActivity extends BaseActivity {
ActivityMyServiceDetailBinding mActivityMyServiceDetailBinding;
    public static final String ARG_SERVICES = "ARG_SERVICES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMyServiceDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_my_service_detail);
        setSupportActionBar(mActivityMyServiceDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addFragment(MyServicesFragment.newInstance(), "DashboardActivity-MyServiceFragment");    }

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
