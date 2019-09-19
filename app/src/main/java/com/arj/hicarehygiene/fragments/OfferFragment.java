package com.arj.hicarehygiene.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.BaseFragment;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.HomeActivity;
import com.arj.hicarehygiene.adapter.MyServiceViewDetailsAdapter;
import com.arj.hicarehygiene.adapter.OfferAdapter;
import com.arj.hicarehygiene.adapter.Primary_Offer_Adapter;
import com.arj.hicarehygiene.databinding.FragmentOfferBinding;
import com.arj.hicarehygiene.network.NetworkCallController;
import com.arj.hicarehygiene.network.NetworkResponseListner;
import com.arj.hicarehygiene.network.model.offer.Offer;
import com.arj.hicarehygiene.network.model.userservices.ServiceTasks;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends BaseFragment {
    FragmentOfferBinding mFragmentOfferBinding;
    RecyclerView.LayoutManager layoutManager;

    private OfferAdapter mAdapter;
    private Primary_Offer_Adapter mPrimaryOfferAdapter;
    private static final int OFFER_REQ = 1000;
    private Integer pageNumber = 1;

    public OfferFragment() {
        // Required empty public constructor
    }

    public static OfferFragment newInstance() {
        Bundle args = new Bundle();
        OfferFragment fragment = new OfferFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentOfferBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_offer, container, false);
        getActivity().setTitle("Offers");
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        return mFragmentOfferBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentOfferBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentOfferBinding.recycleView.setLayoutManager(layoutManager);
        mAdapter = new OfferAdapter(getActivity());

        mFragmentOfferBinding.primaryRecycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mFragmentOfferBinding.primaryRecycle.setLayoutManager(layoutManager);
        mPrimaryOfferAdapter = new Primary_Offer_Adapter(getActivity());
        mFragmentOfferBinding.recycleView.setAdapter(mAdapter);
        mFragmentOfferBinding.primaryRecycle.setAdapter(mPrimaryOfferAdapter);
        mFragmentOfferBinding.navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        mFragmentOfferBinding.shimmerOffer.setVisibility(View.VISIBLE);
        getOffers();

    }

    private void getOffers() {
        NetworkCallController controller = new NetworkCallController(this);
        controller.setListner(new NetworkResponseListner<List<Offer>>() {

            @Override
            public void onResponse(int requestCode, List<Offer> items) {
                mFragmentOfferBinding.shimmerOffer.setVisibility(View.GONE);
                if (items != null) {
                    if (pageNumber == 1 && items.size() > 0) {
                        mAdapter.setData(items);
                        mPrimaryOfferAdapter.setData(items);
                        mAdapter.notifyDataSetChanged();
                        mPrimaryOfferAdapter.notifyDataSetChanged();
                    } else if (items.size() > 0) {
                        mAdapter.addData(items);
                        mPrimaryOfferAdapter.addData(items);
                        mAdapter.notifyDataSetChanged();
                        mPrimaryOfferAdapter.notifyDataSetChanged();
                    } else {
                        pageNumber--;
                    }
                }
            }

            @Override
            public void onFailure(int requestCode) {
                mFragmentOfferBinding.shimmerOffer.setVisibility(View.GONE);
            }
        });
        controller.getOffers(OFFER_REQ);
    }
}
