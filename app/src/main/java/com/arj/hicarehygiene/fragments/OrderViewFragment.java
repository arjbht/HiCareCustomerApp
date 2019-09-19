package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
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
import com.arj.hicarehygiene.activities.OrderViewActivity;
import com.arj.hicarehygiene.adapter.ServicesAdapter;
import com.arj.hicarehygiene.adapter.SlotsAdapter;
import com.arj.hicarehygiene.adapter.WeeksAdapter;
import com.arj.hicarehygiene.databinding.FragmentOrderViewBinding;
import com.arj.hicarehygiene.handler.OnOrderDetailClickHandler;
import com.arj.hicarehygiene.handler.OnRescheduleClickHandler;
import com.arj.hicarehygiene.handler.UserOrderClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.network.model.slots.TimeSlot;
import com.arj.hicarehygiene.network.model.userservices.ServiceRequest;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;
import com.arj.hicarehygiene.network.model.weeks.WeekModel;
import com.arj.hicarehygiene.utils.AppUtils;
import com.arj.hicarehygiene.utils.MyDividerItemDecoration;
import com.arj.hicarehygiene.utils.TimeUtil;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderViewFragment extends BaseFragment implements UserOrderClickHandler, NetworkResponseListner<List<Service_Details>> {
    FragmentOrderViewBinding fragmentOrderViewBinding;
    Orders model;
    public static final String ARGS_ORDERS = "ARGS_ORDERS";
    private static final int SERVICE_REQ = 1000;
    private static final int SLOT_REQUEST = 2000;
    ServicesAdapter mAdapter;
    String OrderNo;
    private Integer pageNumber = 1;
    RecyclerView.LayoutManager layoutManager;
    private WeeksAdapter mWeeksAdapter;
    private SlotsAdapter mSlotAdapter;
    private String taskId = "";
    private String date = "";

    public OrderViewFragment() {
        // Required empty public constructor
    }

    public static OrderViewFragment newInstance(Orders model) {
        Bundle args = new Bundle();
        args.putParcelable(ARGS_ORDERS, model);
        OrderViewFragment fragment = new OrderViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            model = getArguments().getParcelable(ARGS_ORDERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentOrderViewBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_order_view, container, false);
        return fragmentOrderViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = getActivity().getIntent().getParcelableExtra(ARGS_ORDERS);
        OrderNo = model.getOrderNumber();
        getActivity().setTitle("Order #" + model.getOrderNumber());

        getAllServices();
//        fragmentOrderViewBinding.setModel(model);
        fragmentOrderViewBinding.imgType.setImageResource(AppUtils.getImageByCode(model.getServicePlan().getServiceGroupCodeC()));
        fragmentOrderViewBinding.txtService.setText(AppUtils.GetSMSServiceType(model.getServicePlan().getServiceGroupCodeC()));
        fragmentOrderViewBinding.txtAddress.setText(model.getAccountDetail().getFlatNumber() + ", "
                + model.getAccountDetail().getBuildingName() + ", "
                + model.getAccountDetail().getLandmark() + ", "
                + model.getAccountDetail().getLocalitySuburb() + ", "
                + model.getAccountDetail().getBillingStreet() + ", "
                + model.getAccountDetail().getBillingPoastalCode());
        fragmentOrderViewBinding.finalAmount.setText("\u20B9 " + model.getPayment());
        fragmentOrderViewBinding.txtName.setText(model.getAccountDetail().getName() + " | " + model.getAccountDetail().getMobile());
        fragmentOrderViewBinding.txtOrderno.setText("Order No : " + model.getOrderNumber());
        try {
            String start = TimeUtil.reFormatDate(model.getStartDate(), "MMM dd, yyyy");
            String end = TimeUtil.reFormatDate(model.getEndDate(), "MMM dd, yyyy");
            fragmentOrderViewBinding.txtStartDate.setText("Stated On : " + start);
            fragmentOrderViewBinding.txtEndDate.setText("Ended On : " + end);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        fragmentOrderViewBinding.txtActualservice.setText(model.getServicePlanName());
        fragmentOrderViewBinding.txtApartment.setText("Select Apartment Size - " + model.getHouseDetail().getName() + " " + model.getFlatType());
        fragmentOrderViewBinding.txtTotal.setText("\u20B9 " + model.getPayment());
        fragmentOrderViewBinding.txtQuantity.setText("Qty: " + model.getQuantity());


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentOrderViewBinding.recycleServices.setLayoutManager(layoutManager);
        fragmentOrderViewBinding.recycleServices.setHasFixedSize(true);
        mAdapter = new ServicesAdapter();
        fragmentOrderViewBinding.recycleServices.setAdapter(mAdapter);
        fragmentOrderViewBinding.recycleServices.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 10));

        getAllServices();

    }

    @Override
    public void onConstraintClicked(View view) {

    }

    @Override
    public void onRescheduleClicked(View view) {

    }

    @Override
    public void onComplaintClicked(View view) {

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

                NetworkCallController controller = new NetworkCallController(this);
                controller.setListner(this);
                controller.getServiceList(SERVICE_REQ, request);
            }
        }

    }

    @Override
    public void onResponse(int requestCode, final List<Service_Details> items) {
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
        mAdapter.setOnOrderDetailClickHandler(new OnOrderDetailClickHandler() {
            @Override
            public void onViewDetailsClicked(int position) {
                replaceFragment(MyServiceDetailsFragment.newInstance(items.get(position).getTasksList()), "OrderViewActivity-MyServiceDetailsFragment");
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }

            @Override
            public void onRescheduleClicked(int position) {
                taskId =  items.get(position).getTasksList().get(0).getTaskId();
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
                }else {
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

    }
}
