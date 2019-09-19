package com.arj.hicarehygiene.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupVerificationFragment extends BaseFragment {


    public SignupVerificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_verification, container, false);
    }

}
