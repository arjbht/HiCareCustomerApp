package com.arj.hicarehygiene.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.HomeActivity;
import com.arj.hicarehygiene.activities.OrderViewActivity;
import com.arj.hicarehygiene.adapter.OrderSummaryAdapter;
import com.arj.hicarehygiene.databinding.FragmentOrderBinding;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.GetOrderRequest;
import com.arj.hicarehygiene.network.model.OrdersModel.Orders;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment implements NetworkResponseListner<List<Orders>> {
    FragmentOrderBinding mfragmentOrderBinding;
    OrderSummaryAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private RealmResults<LoginResponse> LoginRealmModels;
    private static final int ORDER_REQ = 1000;
    private Integer pageNumber = 1;
    private String UserId = "";
    private String OrderNo = "";
    private String MobileNo = "";

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mfragmentOrderBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        getActivity().setTitle("Orders");

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
//        CoordinatorLayout coordinate = getActivity().findViewById(R.id.coordinate);
//        CoordinatorLayout coordinate_normal = getActivity().findViewById(R.id.coordinate1);

//        FrameLayout lnr_activity = getActivity().findViewById(R.id.container);
//        FrameLayout lnr_fragment = getActivity().findViewById(R.id.container1);
//
//        coordinate.setVisibility(View.GONE);
//        coordinate_normal.setVisibility(View.VISIBLE);

        setHasOptionsMenu(true);
        return mfragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getAllOrders();

        mfragmentOrderBinding.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        getAllOrders();
                    }
                });

        mfragmentOrderBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mfragmentOrderBinding.recycleView.setLayoutManager(layoutManager);


        mfragmentOrderBinding.swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light,
                android.R.color.holo_green_dark, android.R.color.holo_green_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light);

        // specify an adapter (see also next example)
        mAdapter = new OrderSummaryAdapter(getActivity());
//        mAdapter.setOnItemClickHandler(this);
        mfragmentOrderBinding.recycleView.setAdapter(mAdapter);
        mfragmentOrderBinding.recycleView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        if (dy > 0 && isLastItemDisplaying(mfragmentOrderBinding.recycleView)) {
                            pageNumber++;
                            mfragmentOrderBinding.progressBar.setVisibility(View.VISIBLE);
                            getAllOrders();
                        }
                    }
                });
        getAllOrders();
        mfragmentOrderBinding.swipeRefreshLayout.setRefreshing(true);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem cart = menu.findItem(R.id.cart);
//        MenuItem search = menu.findItem(R.id.search);
//        MenuItem filter = menu.findItem(R.id.filter);
//
//        cart.setVisible(false);
//        search.setVisible(false);
//        filter.setVisible(true);
//    }

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

    private void getAllOrders() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                UserId = LoginRealmModels.get(0).getUserID();
                MobileNo = LoginRealmModels.get(0).getPhoneNumber();

                GetOrderRequest request = new GetOrderRequest();
                request.setUserId(UserId);
                request.setMobileNo(MobileNo);
                NetworkCallController controller = new NetworkCallController(this);
                controller.setListner(this);
                controller.getOrderList(ORDER_REQ, request);
            }
        }

    }


    @Override
    public void onResponse(int requestCode, final List<Orders> items) {
        mfragmentOrderBinding.swipeRefreshLayout.setRefreshing(false);
        mfragmentOrderBinding.progressBar.setVisibility(View.GONE);
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

        mAdapter.setOnItemClickHandler(new OnListItemClickHandler() {
            @Override
            public void onItemClick(int positon) {
                Intent intent = new Intent(getActivity(), OrderViewActivity.class);
                intent.putExtra(OrderViewActivity.ARGS_ORDERS, items.get(positon));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onFailure(int requestCode) {
        mfragmentOrderBinding.swipeRefreshLayout.setRefreshing(false);
        mfragmentOrderBinding.progressBar.setVisibility(View.GONE);
    }


}
