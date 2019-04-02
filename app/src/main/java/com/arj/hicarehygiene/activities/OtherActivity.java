package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityOtherBinding;
import com.arj.hicarehygiene.fragments.AccountFragment;
import com.arj.hicarehygiene.fragments.ProfileFragment;

public class OtherActivity extends BaseActivity {

    ActivityOtherBinding mactivityOtherBinding;
    String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivityOtherBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_other);

        setSupportActionBar(mactivityOtherBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragment = getIntent().getStringExtra("navigation");
        if (fragment != null) {
            if (fragment.equals("Offer")) {
                getSupportFragmentManager().beginTransaction().replace(mactivityOtherBinding.container.getId(), ProfileFragment.newInstance()).addToBackStack(null).commit();

            } else if (fragment.equals("Account")) {
                getSupportFragmentManager().beginTransaction().replace(mactivityOtherBinding.container.getId(), AccountFragment.newInstance()).addToBackStack(null).commit();

            }
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
