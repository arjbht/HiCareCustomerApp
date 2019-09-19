package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.ComplaintActivity;
import com.arj.hicarehygiene.activities.DashboardActivity;
import com.arj.hicarehygiene.databinding.FragmentComplaintBinding;
import com.arj.hicarehygiene.handler.UserComplaintFormHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.OrdersModel.GetOrderRequest;
import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.network.model.complaint.ComplaintType;
import com.arj.hicarehygiene.network.model.complaint.CreateComplaintRequest;
import com.arj.hicarehygiene.network.model.complaint.CreateComplaintResponse;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentComplaint extends BaseFragment implements UserComplaintFormHandler {
    FragmentComplaintBinding mfragmentComplaintBinding;
    private static final int REQUEST_TYPE = 1000;
    private static final int REQUEST_ORDER = 2000;
    private static final int REQUEST_COMPLAINTS = 3000;
    private String[] arrayStatus = null;
    private String Type = "";
    private String orderNo = "";

    public FragmentComplaint() {
        // Required empty public constructor
    }

    public static FragmentComplaint newInstance() {
        Bundle args = new Bundle();
        FragmentComplaint fragment = new FragmentComplaint();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mfragmentComplaintBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_complaint, container, false);
        mfragmentComplaintBinding.setHandler(this);
        return mfragmentComplaintBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Complaint");

        getOrderNumbers();
        getComplaintTypes();
    }

    private void createComplaints() {
        if(validateComplaints(Type,orderNo,mfragmentComplaintBinding.edtComment,mfragmentComplaintBinding.edtTitle)) {
            NetworkCallController controller = new NetworkCallController(this);
            CreateComplaintRequest request = new CreateComplaintRequest();
            request.setComplaintType(Type);
            request.setDataType("");
            request.setOrderNo(orderNo);
            request.setRemarks(mfragmentComplaintBinding.edtComment.getText().toString());
            request.setTitle(mfragmentComplaintBinding.edtTitle.getText().toString());
            controller.setListner(new NetworkResponseListner() {
                @Override
                public void onResponse(int requestCode, Object data) {
                    CreateComplaintResponse response = (CreateComplaintResponse) data;
                    if(response.getSuccess()){
                        Toast.makeText(getActivity(), response.getData(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int requestCode) {

                }
            });

            controller.getCreateComplaints(REQUEST_COMPLAINTS,request);
        }

    }

    private void getOrderNumbers() {
        if ((ComplaintActivity) getActivity() != null) {
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                String userId = LoginRealmModels.get(0).getUserID();
                String mobile = LoginRealmModels.get(0).getPhoneNumber();
                NetworkCallController controller = new NetworkCallController(this);
                GetOrderRequest request = new GetOrderRequest();
                request.setMobileNo(mobile);
                request.setUserId(userId);
                controller.setListner(new NetworkResponseListner<List<Orders>>() {
                    @Override
                    public void onResponse(int requestCode, List<Orders> items) {
                        List<String> list = new ArrayList<>();
                        for (Orders order : items) {
                            list.add(order.getOrderNumber());
                        }
                        arrayStatus = new String[list.size()];
                        arrayStatus = list.toArray(arrayStatus);
                        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getActivity(),
                                R.layout.spinner_layout, arrayStatus);
                        statusAdapter.setDropDownViewResource(R.layout.spinner_popup);
                        mfragmentComplaintBinding.spnOrder.setAdapter(statusAdapter);
                        mfragmentComplaintBinding.spnOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                orderNo = mfragmentComplaintBinding.spnOrder.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(int requestCode) {

                    }
                });
                controller.getAllActiveOrders(REQUEST_ORDER,request);
            }
        }
    }

    private void getComplaintTypes() {
        NetworkCallController controller = new NetworkCallController(this);
        controller.setListner(new NetworkResponseListner<List<ComplaintType>>() {

            @Override
            public void onResponse(int requestCode, List<ComplaintType> items) {
                List<String> list = new ArrayList<>();
                for (ComplaintType type : items) {
                    list.add(type.getType());
                }
                arrayStatus = new String[list.size()];
                arrayStatus = list.toArray(arrayStatus);
                ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_layout, arrayStatus);
                statusAdapter.setDropDownViewResource(R.layout.spinner_popup);
                mfragmentComplaintBinding.spnType.setAdapter(statusAdapter);
                mfragmentComplaintBinding.spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        Type = mfragmentComplaintBinding.spnType.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(int requestCode) {

            }
        });
        controller.getComplaintTypes(REQUEST_TYPE);
    }

    @Override
    public void onComplaintSubmitClicked(View view) {
        createComplaints();
    }

    private boolean validateComplaints(String type, String order, EditText comments, EditText title) {
        if (comments.getText().toString().length() == 0) {
            comments.setError("Remark field is required");
            comments.requestFocus();
            return false;
        } else if (title.getText().toString().trim().length() == 0) {
            title.setError("Title field is required");
            title.requestFocus();
            return false;
        }else {
            return true;
        }
    }

}
