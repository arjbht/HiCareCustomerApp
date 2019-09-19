package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.adapter.AddReferralAdapter;
import com.arj.hicarehygiene.databinding.FragmentAddReferralBinding;
import com.arj.hicarehygiene.handler.UserReferralClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.network.model.ReferralModel.AddReferralRequest;
import com.arj.hicarehygiene.network.model.ReferralModel.AddReferralResponse;
import com.arj.hicarehygiene.network.model.ReferralModel.GetReferralRequest;
import com.arj.hicarehygiene.network.model.ReferralModel.ReferralList;

import java.util.List;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddReferralFragment extends BaseFragment implements UserReferralClickHandler, NetworkResponseListner {
    FragmentAddReferralBinding mFragmentAddReferralBinding;
    AddReferralAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private static final int POST_REFERRAL_REQUEST = 1000;
    private static final int GET_REFERRAL_REQUEST = 2000;
    private Integer pageNumber = 1;

    public AddReferralFragment() {
        // Required empty public constructor
    }

    public static AddReferralFragment newInstance() {
        Bundle args = new Bundle();
        AddReferralFragment fragment = new AddReferralFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentAddReferralBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_add_referral, container, false);
        getActivity().setTitle("Add Referral");
        mFragmentAddReferralBinding.setHandler(this);
        // Inflate the layout for this fragment
        return mFragmentAddReferralBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAddReferralBinding.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getReferralList();
                    }
                });
        mFragmentAddReferralBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentAddReferralBinding.recycleView.setLayoutManager(layoutManager);
        mFragmentAddReferralBinding.swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light,
                android.R.color.holo_green_dark, android.R.color.holo_green_light,
                android.R.color.holo_red_dark, android.R.color.holo_red_light);
        mAdapter = new AddReferralAdapter(getActivity());
//        mAdapter.setOnItemClickHandler(this);
        mFragmentAddReferralBinding.recycleView.setAdapter(mAdapter);
        getReferralList();
        mFragmentAddReferralBinding.swipeRefreshLayout.setRefreshing(true);
    }

    private void getReferralList() {
        RealmResults<LoginResponse> LoginRealmModels =
                BaseApplication.getRealm().where(LoginResponse.class).findAll();
        if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
            String mobile = LoginRealmModels.get(0).getUserName();
            String userId = LoginRealmModels.get(0).getUserID();

            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(this);
            GetReferralRequest request = new GetReferralRequest();
            request.setMobileNo(mobile);
            request.setOrderNo("");
            request.setUserId(userId);
            controller.getReferrals(GET_REFERRAL_REQUEST, request);
        }
    }

    @Override
    public void onAddReferralClicked(View view) {
        try {
            LayoutInflater li = LayoutInflater.from(getActivity());

            View promptsView = li.inflate(R.layout.add_referral_dialog, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

            alertDialogBuilder.setView(promptsView);

            alertDialogBuilder.setTitle("Add Referral");
            final AlertDialog alertDialog = alertDialogBuilder.create();
            final EditText edt_fname =
                    (EditText) promptsView.findViewById(R.id.edt_firstname);
            final EditText edt_lname =
                    (EditText) promptsView.findViewById(R.id.edt_lastname);
            final EditText edt_contact =
                    (EditText) promptsView.findViewById(R.id.edtmobile);
            final EditText edt_alt_contact =
                    (EditText) promptsView.findViewById(R.id.edt_alt_mobile);
            final EditText edt_interested =
                    (EditText) promptsView.findViewById(R.id.edt_interested);
            final EditText edt_email =
                    (EditText) promptsView.findViewById(R.id.edtemail);
            final Button btn_send =
                    (Button) promptsView.findViewById(R.id.btn_send);
            final Button btn_cancel =
                    (Button) promptsView.findViewById(R.id.btn_cancel);

            btn_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validateSaveReferral(edt_fname, edt_lname, edt_contact, edt_email)) {
                        RealmResults<LoginResponse> LoginRealmModels =
                                BaseApplication.getRealm().where(LoginResponse.class).findAll();
                        if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                            String sfdcId = LoginRealmModels.get(0).getSFDCID();
                            NetworkCallController controller = new NetworkCallController(AddReferralFragment.this);
                            AddReferralRequest request = new AddReferralRequest();

                            request.setTaskId("");
                            request.setAccountId(sfdcId);
                            request.setReferralName(edt_fname.getText().toString());
                            request.setMobileNo(edt_contact.getText().toString());
                            request.setAlternateMobileNo(edt_alt_contact.getText().toString());
                            request.setEmail(edt_email.getText().toString());
                            request.setInterestedService(edt_interested.getText().toString());

                            controller.setListner(new NetworkResponseListner() {
                                @Override
                                public void onResponse(int requestCode, Object response) {
                                    AddReferralResponse refResponse = (AddReferralResponse) response;
                                    if (refResponse.getSuccess()) {
                                        mAdapter.notifyDataSetChanged();
//                                    Toasty.success(getActivity(),"Referral added successfully.", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getActivity(), "Referral added successfully.", Toast.LENGTH_SHORT).show();
                                        getReferralList();
                                    }
                                }

                                @Override
                                public void onFailure(int requestCode) {

                                }
                            });
                            controller.postReferrals(POST_REFERRAL_REQUEST, request);


                            alertDialog.dismiss();
                            mAdapter.notifyDataSetChanged();

                        }
                    }
                }
            });


            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.setIcon(R.mipmap.logo);

            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateSaveReferral(EditText edt_fname, EditText edt_lname, EditText edt_contact, EditText edtEmail) {
        if (edt_fname.getText().toString().trim().length() == 0) {
            edt_fname.setError("Name is required!");
            edt_fname.requestFocus();
            return false;
        } else if (edt_lname.getText().toString().trim().length() == 0) {
            edt_lname.setError("Last name is required!");
            edt_lname.requestFocus();
            return false;
        } else if (edt_contact.getText().toString().trim().length() == 0) {
            edt_contact.setError("Mobile number is required!");
            edt_contact.requestFocus();
            return false;
        } else if (edt_contact.getText().toString().trim().length() < 10) {
            edt_contact.setError("Mobile number is invalid!");
            edt_contact.requestFocus();
            return false;
        } /*else if (edtEmail.getText().length() > 0) {
            if (!edtEmail.getText().toString().trim().matches(emailPattern)) {
                edtEmail.setError("Email id is invalid!");
                edtEmail.requestFocus();
                return false;
            } else {
                return true;
            }
        }*/ else {
            return true;
        }
    }

    @Override
    public void onResponse(int requestCode, Object data) {
        switch (requestCode) {
            case GET_REFERRAL_REQUEST:
                List<ReferralList> items = (List<ReferralList>) data;
                mFragmentAddReferralBinding.swipeRefreshLayout.setRefreshing(false);
                if (items != null) {
                    if (pageNumber == 1 && items.size() > 0) {
                        mFragmentAddReferralBinding.txtData.setVisibility(View.GONE);
                        mAdapter.setData(items);
                        mAdapter.notifyDataSetChanged();
                    } else if (items.size() > 0) {
                        mFragmentAddReferralBinding.txtData.setVisibility(View.GONE);
                        mAdapter.addData(items);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        pageNumber--;
                    }
                } else {
                    mFragmentAddReferralBinding.txtData.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onFailure(int requestCode) {

    }
}
