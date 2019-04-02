package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.FragmentManageAddressBinding;
import com.arj.hicarehygiene.handler.UserAddAddressClickHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageAddressFragment extends BaseFragment implements UserAddAddressClickHandler {
    FragmentManageAddressBinding mfragmentManageAddressBinding;

    public ManageAddressFragment() {
        // Required empty public constructor
    }


    public static ManageAddressFragment newInstance() {
        Bundle args = new Bundle();
        ManageAddressFragment fragment = new ManageAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mfragmentManageAddressBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_manage_address, container, false);
        // Inflate the layout for this fragment
        getActivity().setTitle("Manage Address");
        mfragmentManageAddressBinding.setHandler(this);
        return mfragmentManageAddressBinding.getRoot();
    }

    @Override
    public void onAddAddressClicked(View view) {
        replaceFragment(AddAddressDemoFragment.newInstance(), "ManageAddressFragment-AddAddressFragment");
    }
}
