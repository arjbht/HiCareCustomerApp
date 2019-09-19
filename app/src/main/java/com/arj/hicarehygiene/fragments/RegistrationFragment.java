package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.FragmentRegistrationBinding;
import com.arj.hicarehygiene.handler.UserRegisterClickHandler;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.BasicResponse;
import com.arj.hicarehygiene.network.model.access.CreateUserResonse;
import com.arj.hicarehygiene.viewmodel.SignUpUserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends BaseFragment implements UserRegisterClickHandler, NetworkResponseListner<BasicResponse> {
    FragmentRegistrationBinding mfragmentRegistrationBinding;
    private static final int CREATE_NEW_USER = 1000;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance() {
        Bundle args = new Bundle();
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mfragmentRegistrationBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false);
        mfragmentRegistrationBinding.setHandler(this);
        mfragmentRegistrationBinding.setModel(new SignUpUserViewModel());
        String text = "<font color=#000000>Already have an account?</font> <b><font color=#2bb77a> Sign In</font></b>";
        mfragmentRegistrationBinding.btnSignin.setText(Html.fromHtml(text));
        return mfragmentRegistrationBinding.getRoot();
    }

    @Override
    public void onSignInClicked(View view) {
        replaceFragment(LoginFragment.newInstance(), "CreateNewUserFragment-UserLoginFragment");
    }

    @Override
    public void onSendOtpClicked(View view) {
        if (validateSignUp(mfragmentRegistrationBinding.getModel())) {
            SignUpUserViewModel model = mfragmentRegistrationBinding.getModel();
            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(this);
            controller.createUser(CREATE_NEW_USER, model);
        }
    }


    private boolean validateSignUp(SignUpUserViewModel signUpUserViewModel) {
        if (signUpUserViewModel.firstName.trim().equalsIgnoreCase("")) {
            mfragmentRegistrationBinding.editTextFirstName.setError(getString(R.string.invalid_first_name));
            return false;
        } else if (signUpUserViewModel.lastName.trim().equalsIgnoreCase("")) {
            mfragmentRegistrationBinding.editTextLastName.setError(getString(R.string.invalid_last_name));
            return false;
        } else if (signUpUserViewModel.phoneNumber.trim().equalsIgnoreCase("")) {
            mfragmentRegistrationBinding.editTextPhone.setError(getString(R.string.invalid_mobile_number));
            return false;
        } else if (signUpUserViewModel.email.trim().equalsIgnoreCase("")) {
            mfragmentRegistrationBinding.editTextEmail.setError(getString(R.string.invalid_email));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onResponse(int requestCode, BasicResponse response) {
        switch (requestCode) {
            case CREATE_NEW_USER:
                if(response.getSuccess()){
                    Toast.makeText(getActivity(), "Otp send successflly.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onFailure(int requestCode) {

    }
}
