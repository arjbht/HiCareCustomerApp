package com.arj.hicarehygiene.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arj.hicarehygiene.BaseApplication;
import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.HomeActivity;
import com.arj.hicarehygiene.activities.LoginActivity;
import com.arj.hicarehygiene.activities.UserDetailActivity;
import com.arj.hicarehygiene.databinding.FragmentAccountBinding;
import com.arj.hicarehygiene.handler.UserAccountClickHandler;
import com.arj.hicarehygiene.network.model.LoginResponse;
import com.arj.hicarehygiene.utils.SharedPreferencesUtility;
import com.onesignal.OneSignalDbHelper;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends BaseFragment implements UserAccountClickHandler {
    FragmentAccountBinding mfragmentAccountBinding;
    FrameLayout container;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mfragmentAccountBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        mfragmentAccountBinding.setHandler(this);
        getActivity().setTitle("Account Details");

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);

//        CoordinatorLayout coordinate = getActivity().findViewById(R.id.coordinate);
//        CoordinatorLayout coordinate_normal = getActivity().findViewById(R.id.coordinate1);
//        container = getActivity().findViewById(R.id.container1);
//        coordinate.setVisibility(View.GONE);
//        coordinate_normal.setVisibility(View.VISIBLE);
        setHasOptionsMenu(true);
        return mfragmentAccountBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAccountDetails();
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem cart = menu.findItem(R.id.cart);
//        MenuItem search = menu.findItem(R.id.search);
//        MenuItem filter = menu.findItem(R.id.filter);
//
//        cart.setVisible(false);
//        search.setVisible(false);
//        filter.setVisible(false);
//    }

    private void getAccountDetails() {
        if ((HomeActivity) getActivity() != null) {
            RealmResults<LoginResponse> LoginRealmModels =
                    BaseApplication.getRealm().where(LoginResponse.class).findAll();
            if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                String Username = LoginRealmModels.get(0).getFirstName() + " " + LoginRealmModels.get(0).getLastName();
                String MobileNo = LoginRealmModels.get(0).getPhoneNumber();
                String Email = LoginRealmModels.get(0).getEmail();

                mfragmentAccountBinding.txtUsername.setText(Username);
                mfragmentAccountBinding.txtUserdetails.setText(MobileNo + " | " + Email);
            }
        }
    }

    @Override
    public void onEditProfileClicked(View view) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra("fragment", "Profile");
        getActivity().startActivity(intent);
    }

    @Override
    public void onNotificationClicked(View view) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra("fragment", "Notification");
        getActivity().startActivity(intent);
    }

    @Override
    public void onMyOrdersClicked(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, OrderFragment.newInstance()).addToBackStack(null).commit();
//        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
//        intent.putExtra("fragment", "Orders");
//        getActivity().startActivity(intent);
    }

    @Override
    public void onMyWalletClicked(View view) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra("fragment", "Wallet");
        getActivity().startActivity(intent);
    }

    @Override
    public void onManageAddressClicked(View view) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra("fragment", "Address");
        getActivity().startActivity(intent);
    }

    @Override
    public void onSignoutClicked(View view) {
        SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_USER_LOGIN,
                false);
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void onHelpClicked(View view) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra("fragment", "Help");
        getActivity().startActivity(intent);
    }


}
