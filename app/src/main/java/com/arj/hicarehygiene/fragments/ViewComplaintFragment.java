package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.adapter.ComplaintHistoryAdapter;
import com.arj.hicarehygiene.adapter.ViewComplaintsAdapter;
import com.arj.hicarehygiene.databinding.FragmentViewComplaintBinding;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.complaint.ComplaintCaseIdRequest;
import com.arj.hicarehygiene.network.model.complaint.Complaints;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewComplaintFragment extends BaseFragment implements NetworkResponseListner<List<Complaints>> {
    FragmentViewComplaintBinding mFragmentViewComplaintBinding;
    private static final String ARG_CASE = "ARG_CASE";
    private static final String ARG_MODEL = "ARG_MODEL";
    private static final int CASE_REQUEST = 1000;
    private String caseNo = "";
    private Complaints complaints;
    private ViewComplaintsAdapter mAdapter;
    private Integer pageNumber = 1;
    RecyclerView.LayoutManager layoutManager;

    public ViewComplaintFragment() {
        // Required empty public constructor
    }

    public static ViewComplaintFragment newInstance(Complaints complaints) {
        Bundle args = new Bundle();
//        args.putString(ARG_CASE, caseNumber);
        args.putParcelable(ARG_MODEL, complaints);
        ViewComplaintFragment fragment = new ViewComplaintFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            caseNo = getArguments().getString(ARG_CASE);
            complaints = getArguments().getParcelable(ARG_MODEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentViewComplaintBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_view_complaint, container, false);
        getActivity().setTitle("Case #" + complaints.getCaseNumber());

        return mFragmentViewComplaintBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentViewComplaintBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentViewComplaintBinding.recycleView.setLayoutManager(layoutManager);
        mAdapter = new ViewComplaintsAdapter();
        mFragmentViewComplaintBinding.recycleView.setAdapter(mAdapter);
        mFragmentViewComplaintBinding.txtTitle.setText(complaints.getComplaint_Description());
        String upperString = complaints.getComplaint_Description().substring(0, 1).toUpperCase() + complaints.getComplaint_Description().substring(1).toLowerCase();
        mFragmentViewComplaintBinding.txtTitle.setText(upperString);
        mFragmentViewComplaintBinding.txtDescription.setText(complaints.getDescription());
        mFragmentViewComplaintBinding.txtStatus.setText(complaints.getStatus());
        if (complaints.getCreatedDate_Text() != null && complaints.getCreatedDate_Text().length() != 0) {
            mFragmentViewComplaintBinding.txtCreateDate.setText(complaints.getCreatedDate_Text());
        }
        if (complaints.getClosedDate() != null && complaints.getClosedDate().length() != 0) {
            mFragmentViewComplaintBinding.txtClosedDate.setText(complaints.getClosedDate());
            mFragmentViewComplaintBinding.lnrClosedDate.setVisibility(View.VISIBLE);
        }else {
            mFragmentViewComplaintBinding.lnrClosedDate.setVisibility(View.GONE);
        }
        mFragmentViewComplaintBinding.txtOrderNo.setText(complaints.getOrder_No());
        if (complaints.getRefund()) {
            mFragmentViewComplaintBinding.checkRefund.setChecked(true);
        } else {
            mFragmentViewComplaintBinding.checkRefund.setChecked(false);
        }

        if (complaints.getFalse_Complaint()) {
            mFragmentViewComplaintBinding.checkFalseComplaint.setChecked(true);
        } else {
            mFragmentViewComplaintBinding.checkFalseComplaint.setChecked(false);
        }
        getComplaintsByCaseId();
    }

    private void getComplaintsByCaseId() {
        NetworkCallController controller = new NetworkCallController(this);
        ComplaintCaseIdRequest request = new ComplaintCaseIdRequest();
        request.setCaseNo(complaints.getCaseNumber());
        controller.setListner(this);
        controller.getComplaintByCaseId(CASE_REQUEST, request);
    }


    @Override
    public void onResponse(int requestCode, List<Complaints> items) {

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
}
