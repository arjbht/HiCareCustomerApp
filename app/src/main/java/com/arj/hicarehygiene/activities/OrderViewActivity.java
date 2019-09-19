package com.arj.hicarehygiene.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.text.ParseException;
import java.util.List;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.adapter.ServicesAdapter;
import com.arj.hicarehygiene.databinding.ActivityOrderViewBinding;
import com.arj.hicarehygiene.fragments.MyServiceDetailsFragment;
import com.arj.hicarehygiene.fragments.OrderViewFragment;
import com.arj.hicarehygiene.handler.OnOrderDetailClickHandler;
import com.arj.hicarehygiene.handler.UserOrderClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.network.model.userservices.ServiceRequest;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;
import com.arj.hicarehygiene.utils.AppUtils;
import com.arj.hicarehygiene.utils.MyDividerItemDecoration;
import com.arj.hicarehygiene.utils.TimeUtil;

import io.realm.RealmResults;

public class OrderViewActivity extends BaseActivity {
    ActivityOrderViewBinding mActivityOrderViewBinding;
    Orders model;
    public static final String ARGS_ORDERS = "ARGS_ORDERS";
    int DROP = 0;
    private static final int SERVICE_REQ = 1000;
    ServicesAdapter mAdapter;
    String OrderNo;
    private Integer pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityOrderViewBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_order_view);
        setSupportActionBar(mActivityOrderViewBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        model = getIntent().getParcelableExtra(ARGS_ORDERS);
        addFragment(OrderViewFragment.newInstance(model),"OrderViewActivity-OrderViewFragment");
    }



    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.stay, R.anim.slide_out_right);  //close animation
        super.onBackPressed();
        int fragment = getSupportFragmentManager().getBackStackEntryCount();
        Log.e("fragments", String.valueOf(fragment));
        getSupportFragmentManager().popBackStack();
//        if (fragment < 1) {
//            finish();
//        } else {
//            getFragmentManager().popBackStack();
//        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Intent parentIntent = NavUtils.getParentActivityIntent(this);
//                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(parentIntent);
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
