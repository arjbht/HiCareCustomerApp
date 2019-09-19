package com.arj.hicarehygiene.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.PrimaryOffersAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.network.model.offer.Offer;
import com.arj.hicarehygiene.viewmodel.OffersViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Primary_Offer_Adapter extends RecyclerView.Adapter<Primary_Offer_Adapter.ViewHolder> {

    private List<OffersViewModel> items = null;
    private Activity context;

    public Primary_Offer_Adapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;

    }


    @Override
    public Primary_Offer_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PrimaryOffersAdapterBinding mPrimaryOffersAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.primary_offers_adapter, parent, false);
        return new Primary_Offer_Adapter.ViewHolder(mPrimaryOffersAdapterBinding);
    }

    @Override
    public void onBindViewHolder(Primary_Offer_Adapter.ViewHolder holder, final int position) {
        Picasso.with(context).load(items.get(position).getImageUrl()).into(holder.mPrimaryOffersAdapterBinding.imgPrimaryOffers);
    }

    @Override
    public int getItemCount() {
        return items.size();

    }


    public void setData(List<Offer> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            OffersViewModel offersViewModel = new OffersViewModel();
            offersViewModel.clone(data.get(index));
            items.add(offersViewModel);
        }
    }

    public void addData(List<Offer> data) {
        for (int index = 0; index < data.size(); index++) {
            OffersViewModel offersViewModel = new OffersViewModel();
            offersViewModel.clone(data.get(index));
            items.add(offersViewModel);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final PrimaryOffersAdapterBinding mPrimaryOffersAdapterBinding;

        public ViewHolder(PrimaryOffersAdapterBinding mPrimaryOffersAdapterBinding) {
            super(mPrimaryOffersAdapterBinding.getRoot());
            this.mPrimaryOffersAdapterBinding = mPrimaryOffersAdapterBinding;
        }
    }
}
