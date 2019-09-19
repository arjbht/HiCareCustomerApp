package com.arj.hicarehygiene.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ReferralListAdapterBinding;
import com.arj.hicarehygiene.network.model.ReferralModel.ReferralList;
import com.arj.hicarehygiene.viewmodel.ReferralViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddReferralAdapter extends RecyclerView.Adapter<AddReferralAdapter.ViewHolder> {


    //    private OnDeleteListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private List<ReferralViewModel> items = null;

    public AddReferralAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }


    @Override
    public AddReferralAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReferralListAdapterBinding mReferralListAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.referral_list_adapter, parent, false);
        return new AddReferralAdapter.ViewHolder(mReferralListAdapterBinding);
    }


    @Override
    public void onBindViewHolder(AddReferralAdapter.ViewHolder holder, final int position) {
        holder.mReferralListAdapterBinding.txtName.setText(items.get(position).getName());
        holder.mReferralListAdapterBinding.txtMobile.setText(items.get(position).getMobileNo());
        holder.mReferralListAdapterBinding.txtAltMobile.setText(items.get(position).getAlternateMobileNo());
        holder.mReferralListAdapterBinding.txtInterested.setText(items.get(position).getInterestedService());
        holder.mReferralListAdapterBinding.txtEmail.setText(items.get(position).getEmail());


//        holder.mReferralListAdapterBinding.imgDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickHandler.onDeleteItemClicked(position);
//            }
//        });
    }

//    public void setOnItemClickHandler(OnDeleteListItemClickHandler onItemClickHandler) {
//        this.onItemClickHandler = onItemClickHandler;
//    }


    @Override
    public int getItemCount() {
        return items.size();

    }

    public void setData(List<ReferralList> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            ReferralViewModel referralListViewModel = new ReferralViewModel();
            referralListViewModel.clone(data.get(index));
            items.add(referralListViewModel);
        }
    }

    public void addData(List<ReferralList> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            ReferralViewModel referralListViewModel = new ReferralViewModel();
            referralListViewModel.clone(data.get(index));
            items.add(referralListViewModel);
        }
    }

    public void removeAll() {
        items.removeAll(items);
        notifyDataSetChanged();
    }

    public ReferralViewModel getItem(int position) {
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ReferralListAdapterBinding mReferralListAdapterBinding;

        public ViewHolder(ReferralListAdapterBinding mReferralListAdapterBinding) {
            super(mReferralListAdapterBinding.getRoot());
            this.mReferralListAdapterBinding = mReferralListAdapterBinding;
        }
    }
}

