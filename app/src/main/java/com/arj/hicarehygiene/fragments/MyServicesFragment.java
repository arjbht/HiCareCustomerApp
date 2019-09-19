package com.arj.hicarehygiene.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.MyServiceDetailActivity;
import com.arj.hicarehygiene.adapter.ComplaintHistoryAdapter;
import com.arj.hicarehygiene.adapter.MyServiceAdapter;
import com.arj.hicarehygiene.adapter.OfferAdapter;
import com.arj.hicarehygiene.adapter.SlotsAdapter;
import com.arj.hicarehygiene.adapter.WeeksAdapter;
import com.arj.hicarehygiene.databinding.FragmentMyServicesBinding;
import com.arj.hicarehygiene.handler.OnRescheduleClickHandler;
import com.arj.hicarehygiene.handler.OnServiceDetailClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.slots.TimeSlot;
import com.arj.hicarehygiene.network.model.userservices.MyServiceRequest;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;
import com.arj.hicarehygiene.network.model.weeks.WeekModel;
import com.arj.hicarehygiene.utils.AppUtils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyServicesFragment extends BaseFragment implements NetworkResponseListner<List<Service_Details>> {
    FragmentMyServicesBinding mFragmentMyServiceBinding;
    private static final int SERVICE_REQUEST = 1000;
    private static final int SLOT_REQUEST = 2000;
    private MyServiceAdapter mAdapter;
    private Integer pageNumber = 1;
    RecyclerView.LayoutManager layoutManager;
    private WeeksAdapter mWeeksAdapter;
    private SlotsAdapter mSlotAdapter;
    private String taskId = "";
    private String date = "";

    public static MyServicesFragment newInstance() {
        Bundle args = new Bundle();
        MyServicesFragment fragment = new MyServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MyServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentMyServiceBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_my_services, container, false);
        getActivity().setTitle("My Services");
        return mFragmentMyServiceBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentMyServiceBinding.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getAllServices();
                    }
                });
        mFragmentMyServiceBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentMyServiceBinding.recycleView.setLayoutManager(layoutManager);
        mFragmentMyServiceBinding.swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light,
                android.R.color.holo_green_dark, android.R.color.holo_green_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light);

        mAdapter = new MyServiceAdapter(getActivity());
        mFragmentMyServiceBinding.recycleView.setAdapter(mAdapter);
        mFragmentMyServiceBinding.swipeRefreshLayout.setRefreshing(true);
        mFragmentMyServiceBinding.shimmerServices.setVisibility(View.VISIBLE);
        getAllServices();
    }

    private void getAllServices() {
        if (getActivity() != null) {
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                String mobile = LoginRealmModels.get(0).getPhoneNumber();
                NetworkCallController controller = new NetworkCallController(this);
                MyServiceRequest request = new MyServiceRequest();
                request.setMobileNo(mobile);
                controller.setListner(this);
                controller.getAllServices(SERVICE_REQUEST, request);
            }
        }
    }

    @Override
    public void onResponse(final int requestCode, final List<Service_Details> items) {
        mFragmentMyServiceBinding.shimmerServices.setVisibility(View.GONE);
        mFragmentMyServiceBinding.swipeRefreshLayout.setRefreshing(false);
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

        mAdapter.setOnServiceDetailClickHandler(new OnServiceDetailClickHandler() {
            @Override
            public void onViewDetailClicked(int position) {
                replaceFragment(MyServiceDetailsFragment.newInstance(items.get(position).getTasksList()), "MyServiceDetailsFragment - ViewComplaintFragment");
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

            @Override
            public void onRescheduleClicked(int position) {
                taskId = items.get(position).getTasksList().get(0).getTaskId();
                showRescheduleDialog();
            }
        });
    }

    private void showRescheduleDialog() {
        LayoutInflater li = LayoutInflater.from(getActivity());

        View promptsView = li.inflate(R.layout.reschedule_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(promptsView);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        final ArrayList<WeekModel> weekList = new ArrayList();
        DateTime today = new DateTime().withTimeAtStartOfDay();
        TextView txtMonth = (TextView) promptsView.findViewById(R.id.txtMonth);
        final TextView txtSlots = (TextView) promptsView.findViewById(R.id.txtNoSlots);
        final Button btnSubmit = (Button) promptsView.findViewById(R.id.btnSubmit);
        RecyclerView recycleWeeks = (RecyclerView) promptsView.findViewById(R.id.recycleView);
        RecyclerView recycleSlots = (RecyclerView) promptsView.findViewById(R.id.recycleSlots);
        recycleWeeks.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycleWeeks.setLayoutManager(layoutManager);
        recycleSlots.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycleSlots.setLayoutManager(layoutManager);
        btnSubmit.setVisibility(View.GONE);
        for (int i = 2; i < 9; i++) {
            WeekModel weekModel = new WeekModel();
            weekModel.setDateTime(today.plusDays(i).withTimeAtStartOfDay());
            weekModel.setDate(String.valueOf(today.plusDays(i).getDayOfMonth()));
            weekModel.setDays(AppUtils.getDays(today.plusDays(i).getDayOfWeek()));
            weekList.add(weekModel);
        }

        mWeeksAdapter = new WeeksAdapter(weekList, today, txtMonth);
        recycleWeeks.setAdapter(mWeeksAdapter);

        mWeeksAdapter.setOnRescheduleClickHandler(new OnRescheduleClickHandler() {
            @Override
            public void onDateSelected(int position) {
                weekList.get(position).getDateTime();
                String strDateTime = String.valueOf(weekList.get(position).getDateTime());
                DateTime dateTime = DateTime.parse(strDateTime);
                DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                date = fmt.print(dateTime);
                getAvailableSlots(btnSubmit, txtSlots);
            }

            @Override
            public void onSlotSelected(int position) {

            }
        });

        mSlotAdapter = new SlotsAdapter(getActivity());
        recycleSlots.setAdapter(mSlotAdapter);
        alertDialog.show();
    }

    private void getAvailableSlots(final Button btnSubmit, final TextView txtSlots) {
        NetworkCallController controller = new NetworkCallController(this);
        controller.setListner(new NetworkResponseListner<List<TimeSlot>>() {

            @Override
            public void onResponse(int requestCode, List<TimeSlot> items) {
                if (items != null) {
                    if (pageNumber == 1 && items.size() > 0) {
                        mSlotAdapter.setData(items);
                        mSlotAdapter.notifyDataSetChanged();
                        btnSubmit.setVisibility(View.VISIBLE);
                        txtSlots.setVisibility(View.GONE);
                    } else if (items.size() > 0) {
                        mSlotAdapter.addData(items);
                        mSlotAdapter.notifyDataSetChanged();
                        btnSubmit.setVisibility(View.VISIBLE);
                        txtSlots.setVisibility(View.GONE);
                    } else {
                        pageNumber--;
                    }
                } else {
                    txtSlots.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(int requestCode) {

            }
        });

        controller.getAvailableSlots(SLOT_REQUEST, taskId, date, date);
    }


    @Override
    public void onFailure(int requestCode) {
        mFragmentMyServiceBinding.shimmerServices.setVisibility(View.GONE);
    }
}
