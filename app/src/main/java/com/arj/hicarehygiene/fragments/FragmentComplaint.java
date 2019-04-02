package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.FragmentComplaintBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentComplaint extends BaseFragment {
FragmentComplaintBinding mfragmentComplaintBinding;

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

        getActivity().setTitle("Complaint");

        // Inflate the layout for this fragment
        return mfragmentComplaintBinding.getRoot();

    }

}
