package com.arj.hicarehygiene.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.adapter.ServicesAdapter;
import com.arj.hicarehygiene.databinding.ActivityOrderViewBinding;
import com.arj.hicarehygiene.handler.UserOrderClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.network.model.userservices.ServiceRequest;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;
import io.realm.RealmResults;

public class OrderViewActivity extends BaseActivity implements UserOrderClickHandler, NetworkResponseListner<List<Service_Details>> {
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

        mActivityOrderViewBinding.setHandler(this);
        setSupportActionBar(mActivityOrderViewBinding.toolbar);
        getSupportActionBar().setTitle("Order Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mActivityOrderViewBinding.second.setActive(true);

        model = getIntent().getParcelableExtra(ARGS_ORDERS);
        OrderNo = model.getOrderNumber();
        getAllServices();
//        mActivityOrderViewBinding.setModel(model);
        mActivityOrderViewBinding.txtAddress.setText(model.getAccountDetail().getFlatNumber() + ", "
                + model.getAccountDetail().getBuildingName() + ", "
                + model.getAccountDetail().getLandmark() + ", "
                + model.getAccountDetail().getLocalitySuburb() + ", "
                + model.getAccountDetail().getBillingStreet() + ", "
                + model.getAccountDetail().getBillingPoastalCode());
        mActivityOrderViewBinding.finalAmount.setText("\u20B9 " + model.getPayment());
        mActivityOrderViewBinding.txtName.setText(model.getAccountDetail().getName() + " | " + model.getAccountDetail().getMobile());
        mActivityOrderViewBinding.txtOrderno.setText("Order No : " + model.getOrderNumber());
        mActivityOrderViewBinding.txtDate.setText(model.getStartDate());
        mActivityOrderViewBinding.txtActualservice.setText(model.getServicePlanName());
        mActivityOrderViewBinding.txtApartment.setText("Select Apartment Size - " + model.getHouseDetail().getName() +" "+ model.getFlatType());
        mActivityOrderViewBinding.txtTotal.setText("\u20B9 " + model.getPayment());
        mActivityOrderViewBinding.txtQuantity.setText("Qty: " + model.getQuantity());


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mActivityOrderViewBinding.recycleServices.setLayoutManager(layoutManager);
        mActivityOrderViewBinding.recycleServices.setHasFixedSize(true);
        mAdapter = new ServicesAdapter(OrderViewActivity.this);
        mActivityOrderViewBinding.recycleServices.setAdapter(mAdapter);
        mActivityOrderViewBinding.recycleServices.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        if (dy > 0 && isLastItemDisplaying(mActivityOrderViewBinding.recycleServices)) {
                            pageNumber++;
//                            mFragmentBuyerProductListBinding.progressBar.setVisibility(View.VISIBLE);
                            getAllServices();
                        }
                    }
                });
        getAllServices();
    }

    @Override
    public void onConstraintClicked(View view) {
        if (mActivityOrderViewBinding.constraint2.getVisibility() == View.VISIBLE) {
            mActivityOrderViewBinding.imgUp.setVisibility(View.VISIBLE);
            mActivityOrderViewBinding.imgDown.setVisibility(View.GONE);
            mActivityOrderViewBinding.constraint2.setVisibility(View.GONE);
        } else {
            mActivityOrderViewBinding.imgUp.setVisibility(View.GONE);
            mActivityOrderViewBinding.imgDown.setVisibility(View.VISIBLE);
            mActivityOrderViewBinding.constraint2.setVisibility(View.VISIBLE);
        }
    }
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION
                    && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRescheduleClicked(View view) {

    }

    @Override
    public void onComplaintClicked(View view) {
        Intent intent = new Intent(this, ComplaintActivity.class);
        startActivity(intent);
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


    private void getAllServices() {
        if (this != null) {
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                String UserId = LoginRealmModels.get(0).getUserID();
                String MobileNo = LoginRealmModels.get(0).getPhoneNumber();

                ServiceRequest request = new ServiceRequest();

                request.setUserId(UserId);
                request.setMobileNo(MobileNo);
                request.setOrderNo(OrderNo);

                NetworkCallController controller = new NetworkCallController();
                controller.setListner(this);
                controller.getServiceList(SERVICE_REQ, request);
            }
        }

    }

    @Override
    public void onResponse(int requestCode, List<Service_Details> items) {
        if (items != null) {
            if (pageNumber == 1 && items.size() > 0) {
                mAdapter.setData(items);
                mAdapter.notifyDataSetChanged();
            } else if (items.size() > 0) {
                mAdapter.addData(items);
                mAdapter.notifyDataSetChanged();
            } else {
                pageNumber--;
            }
        }


    }

    @Override
    public void onFailure(int requestCode) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
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
