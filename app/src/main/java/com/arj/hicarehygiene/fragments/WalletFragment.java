package com.arj.hicarehygiene.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.FragmentWalletBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {

    FragmentWalletBinding mfragmentWalletBinding;

    public WalletFragment() {
        // Required empty public constructor
    }

    public static WalletFragment newInstance() {
        Bundle args = new Bundle();
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mfragmentWalletBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);
        getActivity().setTitle("My Wallet");

        // Inflate the layout for this fragment
        return mfragmentWalletBinding.getRoot();
    }

}
