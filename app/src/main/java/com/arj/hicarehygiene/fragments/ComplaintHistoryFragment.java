package com.arj.hicarehygiene.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.ComplaintActivity;
import com.arj.hicarehygiene.activities.ComplaintDetailActivity;
import com.arj.hicarehygiene.activities.DashboardActivity;
import com.arj.hicarehygiene.activities.HomeActivity;
import com.arj.hicarehygiene.activities.OrderViewActivity;
import com.arj.hicarehygiene.adapter.ComplaintHistoryAdapter;
import com.arj.hicarehygiene.databinding.FragmentComplaintHistoryBinding;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.handler.UserAddComplaintHandler;
import com.arj.hicarehygiene.handler.UserComplaintHistoryClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.network.model.complaint.ComplaintHistoryRequest;
import com.arj.hicarehygiene.network.model.complaint.Complaints;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintHistoryFragment extends BaseFragment implements NetworkResponseListner<List<Complaints>>, UserAddComplaintHandler {

    FragmentComplaintHistoryBinding mFragmentComplaintHistoryBinding;
    ComplaintHistoryAdapter mAdapter;
    //    private RealmResults<Orders> OrdersRealmModels;
    private String UserId = "";
    private String OrderNo = "";
    private String MobileNo = "";
    private Integer pageNumber = 1;
    RecyclerView.LayoutManager layoutManager;
    private static final int COMPLAINT_REQ = 1000;

    public ComplaintHistoryFragment() {
        // Required empty public constructor
    }

    public static ComplaintHistoryFragment newInstance() {
        Bundle args = new Bundle();
        ComplaintHistoryFragment fragment = new ComplaintHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentComplaintHistoryBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_complaint_history, container, false);

        getActivity().setTitle("Complaint History");
        mFragmentComplaintHistoryBinding.setHandler(this);
        setHasOptionsMenu(true);
        return mFragmentComplaintHistoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentComplaintHistoryBinding.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getComplaintHistory();
                    }
                });

        mFragmentComplaintHistoryBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentComplaintHistoryBinding.recycleView.setLayoutManager(layoutManager);

        mFragmentComplaintHistoryBinding.swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light,
                android.R.color.holo_green_dark, android.R.color.holo_green_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light);

        mAdapter = new ComplaintHistoryAdapter(getActivity());
        mFragmentComplaintHistoryBinding.recycleView.setAdapter(mAdapter);
        mFragmentComplaintHistoryBinding.recycleView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);


                    }
                });
        mFragmentComplaintHistoryBinding.swipeRefreshLayout.setRefreshing(true);
        mFragmentComplaintHistoryBinding.shimmerComplaint.setVisibility(View.VISIBLE);
        getComplaintHistory();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem cart = menu.findItem(R.id.cart);
        MenuItem search = menu.findItem(R.id.search);
        MenuItem filter = menu.findItem(R.id.filter);

        cart.setVisible(false);
        search.setVisible(false);
        filter.setVisible(false);
    }

    private void getComplaintHistory() {
        if ((ComplaintActivity) getActivity() != null) {
//            RealmResults<Orders> OrdersRealmModels =
//                    BaseApplication.getRealm().where(Orders.class).findAll();
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();

            if (LoginRealmModels != null && LoginRealmModels.size() > 0 /*&& OrdersRealmModels != null && OrdersRealmModels.size() > 0*/) {
                UserId = LoginRealmModels.get(0).getUserID();
                MobileNo = LoginRealmModels.get(0).getPhoneNumber();
//                OrderNo = OrdersRealmModels.get(0).getOrderNumber();
                ComplaintHistoryRequest request = new ComplaintHistoryRequest();

                request.setUserId(UserId);
                request.setMobileNo(MobileNo);
//                request.setOrderNo(OrderNo);
                NetworkCallController controller = new NetworkCallController(this);
                controller.setListner(this);
                controller.getComplaintList(COMPLAINT_REQ, request);
                setHasOptionsMenu(false);
            }
        }
    }

    @Override
    public void onResponse(int requestCode, final List<Complaints> items) {
        mFragmentComplaintHistoryBinding.shimmerComplaint.setVisibility(View.GONE);
        mFragmentComplaintHistoryBinding.swipeRefreshLayout.setRefreshing(false);
        if (items != null) {
            if (pageNumber == 1 && items.size() > 0) {
                mFragmentComplaintHistoryBinding.txtData.setVisibility(View.GONE);
                mAdapter.setData(items);
                mAdapter.notifyDataSetChanged();
            } else if (items.size() > 0) {
                mFragmentComplaintHistoryBinding.txtData.setVisibility(View.GONE);
                mAdapter.addData(items);
                mAdapter.notifyDataSetChanged();
            } else {
                pageNumber--;
            }
        } else {
            mFragmentComplaintHistoryBinding.txtData.setVisibility(View.VISIBLE);
        }

        mAdapter.setOnComplaintViewHandler(new UserComplaintHistoryClickHandler() {
            @Override
            public void onComplaintViewClicked(int Position) {
                    replaceFragment(ViewComplaintFragment.newInstance(items.get(Position)),"ComplaintHistoryFragment - ViewComplaintFragment");
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    @Override
    public void onFailure(int requestCode) {
        mFragmentComplaintHistoryBinding.swipeRefreshLayout.setRefreshing(false);
        mFragmentComplaintHistoryBinding.shimmerComplaint.setVisibility(View.GONE);
    }


    @Override
    public void onAddComplaintClicked(View view) {
        replaceFragment(FragmentComplaint.newInstance(), "ComplaintHistoryFragment-FragmentComplaint");
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

    }
}
