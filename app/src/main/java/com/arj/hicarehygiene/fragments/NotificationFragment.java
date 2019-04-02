package com.arj.hicarehygiene.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.HomeActivity;
import com.arj.hicarehygiene.databinding.FragmentNotificationBinding;
import com.arj.hicarehygiene.handler.UserNotificationClickHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment implements UserNotificationClickHandler {
    FragmentNotificationBinding mfragmentNotificationBinding;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mfragmentNotificationBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        getActivity().setTitle("Notification");
        mfragmentNotificationBinding.setHandler(this);
        return mfragmentNotificationBinding.getRoot();
    }

    @Override
    public void onGoToHomeClicked(View view) {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
