package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityAddComplaintBinding;

public class AddComplaintActivity extends BaseActivity {
    ActivityAddComplaintBinding mActivityAddComplaintBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddComplaintBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_add_complaint);
        setSupportActionBar(mActivityAddComplaintBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
