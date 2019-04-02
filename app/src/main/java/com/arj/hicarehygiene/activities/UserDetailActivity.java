package com.arj.hicarehygiene.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityUserdetailBinding;
import com.arj.hicarehygiene.fragments.ManageAddressFragment;
import com.arj.hicarehygiene.fragments.NotificationFragment;
import com.arj.hicarehygiene.fragments.ProfileFragment;
import com.arj.hicarehygiene.fragments.WalletFragment;

public class UserDetailActivity extends BaseActivity {
    ActivityUserdetailBinding mactivityUserDetailBinding;
    String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivityUserDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_userdetail);

        setSupportActionBar(mactivityUserDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragment = getIntent().getStringExtra("fragment");
        if (fragment != null) {
            if (fragment.equals("Profile")) {
                getSupportFragmentManager().beginTransaction().replace(mactivityUserDetailBinding.container.getId(), ProfileFragment.newInstance()).addToBackStack(null).commit();

            }  else if (fragment.equals("Wallet")) {
                getSupportFragmentManager().beginTransaction().replace(mactivityUserDetailBinding.container.getId(), WalletFragment.newInstance()).addToBackStack(null).commit();
            } else if (fragment.equals("Notification")) {
                getSupportFragmentManager().beginTransaction().replace(mactivityUserDetailBinding.container.getId(), NotificationFragment.newInstance()).addToBackStack(null).commit();
            } else if (fragment.equals("Address")) {
                getSupportFragmentManager().beginTransaction().replace(mactivityUserDetailBinding.container.getId(), ManageAddressFragment.newInstance()).addToBackStack(null).commit();

            } else if (fragment.equals("Help")) {

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
