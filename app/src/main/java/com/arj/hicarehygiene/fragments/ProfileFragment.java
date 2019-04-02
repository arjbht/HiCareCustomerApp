package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.UserDetailActivity;
import com.arj.hicarehygiene.databinding.FragmentProfileBinding;
import com.arj.hicarehygiene.network.model.LoginResponse;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {
    FragmentProfileBinding mfragmentprofilebinding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mfragmentprofilebinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        getActivity().setTitle("My Profile");

        return mfragmentprofilebinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAccountDetails();
    }

    private void getAccountDetails() {
        if ((UserDetailActivity) getActivity() != null) {
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                String Username = LoginRealmModels.get(0).getFirstName() + " " + LoginRealmModels.get(0).getLastName();
                String MobileNo = LoginRealmModels.get(0).getPhoneNumber();
                String Email = LoginRealmModels.get(0).getEmail();
                mfragmentprofilebinding.edtUsername.setText(Username);
                mfragmentprofilebinding.edtEmail.setText(Email);
                mfragmentprofilebinding.edtMobile.setText(MobileNo);

            }
        }
    }
}
