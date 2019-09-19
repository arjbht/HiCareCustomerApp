package com.arj.hicarehygiene.adapter;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.OfferAdapterBinding;
import com.arj.hicarehygiene.databinding.ViewServiceDetailAdapterBinding;
import com.arj.hicarehygiene.network.model.offer.Offer;
import com.arj.hicarehygiene.network.model.userservices.ServiceTasks;
import com.arj.hicarehygiene.viewmodel.OffersViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<OffersViewModel> items = null;
    private Activity context;

    public OfferAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }


    @Override
    public OfferAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OfferAdapterBinding mOfferAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.offer_adapter, parent, false);
        return new OfferAdapter.ViewHolder(mOfferAdapterBinding);
    }

    @Override
    public void onBindViewHolder(final OfferAdapter.ViewHolder holder, final int position) {
        holder.mOfferAdapterBinding.txtExpiry.setText("Expires " + items.get(position).getExpiryDate());
//        holder.mOfferAdapterBinding.txtTitle.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD);
        Picasso.with(context).load(items.get(position).getImageUrl()).into(holder.mOfferAdapterBinding.imgOffer);
        holder.mOfferAdapterBinding.lnrGetOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(position).getUrlLink())));
            }
        });
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

        private final OfferAdapterBinding mOfferAdapterBinding;

        public ViewHolder(OfferAdapterBinding mOfferAdapterBinding) {
            super(mOfferAdapterBinding.getRoot());
            this.mOfferAdapterBinding = mOfferAdapterBinding;
        }
    }
}

