package com.arj.hicarehygiene.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ComplaintHistoryAdapterBinding;
import com.arj.hicarehygiene.fragments.ComplaintHistoryFragment;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.handler.UserComplaintHistoryClickHandler;
import com.arj.hicarehygiene.network.model.complaint.Complaints;

import com.arj.hicarehygiene.viewmodel.ComplaintHistoryViewModel;

public class ComplaintHistoryAdapter extends RecyclerView.Adapter<ComplaintHistoryAdapter.ViewHolder> {
    private int rotationAngle = 0;
    private OnListItemClickHandler onItemClickHandler;
    private UserComplaintHistoryClickHandler onComplaintViewHandler;
    private List<ComplaintHistoryViewModel> items = null;
    private Activity mContext;

    public ComplaintHistoryAdapter(Activity context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }


    @Override
    public ComplaintHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ComplaintHistoryAdapterBinding mComplaintHistoryAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.complaint_history_adapter, parent, false);
        return new ComplaintHistoryAdapter.ViewHolder(mComplaintHistoryAdapterBinding);
    }

    @Override
    public void onBindViewHolder(final ComplaintHistoryAdapter.ViewHolder holder, final int position) {
        holder.mComplaintHistoryAdapterBinding.txtDate.setText(items.get(position).getCreatedDate_Text());
        holder.mComplaintHistoryAdapterBinding.txtCaseNo.setText(items.get(position).getCaseNumber());
        holder.mComplaintHistoryAdapterBinding.txtDescription.setText(items.get(position).getDescription());
        holder.mComplaintHistoryAdapterBinding.txtStatus.setText(items.get(position).getStatus());
        holder.mComplaintHistoryAdapterBinding.txtOrder.setText(items.get(position).getOrder_No__c());
        String upperString = items.get(position).getComplaint_Description().substring(0,1).toUpperCase() + items.get(position).getComplaint_Description().substring(1).toLowerCase();
        holder.mComplaintHistoryAdapterBinding.txtService.setText(upperString);

        holder.mComplaintHistoryAdapterBinding.lnrView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onComplaintViewHandler.onComplaintViewClicked(position);
            }
        });



//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickHandler.onItemClick(position);
//            }
//        });
        holder.mComplaintHistoryAdapterBinding.lnrTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.mComplaintHistoryAdapterBinding.lnrTop.getTag()!=null &&  holder.mComplaintHistoryAdapterBinding.lnrTop.getTag().toString().equals("180")){
                    ObjectAnimator anim = ObjectAnimator.ofFloat(holder.mComplaintHistoryAdapterBinding.imgArrow, "rotation",180, 0);
                    anim.setDuration(500);
                    anim.start();
                    holder.mComplaintHistoryAdapterBinding.lnrTop.setTag("");


                }  else {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(holder.mComplaintHistoryAdapterBinding.imgArrow, "rotation",0,  180);
                    anim.setDuration(500);
                    anim.start();
                    holder.mComplaintHistoryAdapterBinding.lnrTop.setTag(180+"");

                }

                if (holder.mComplaintHistoryAdapterBinding.lnrDetails.getVisibility() == View.VISIBLE) {
                    holder.mComplaintHistoryAdapterBinding.lnrDetails.setVisibility(View.GONE);
                } else {
                    Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.splash);
                    holder.mComplaintHistoryAdapterBinding.lnrDetails.startAnimation(animation);
                    holder.mComplaintHistoryAdapterBinding.lnrDetails.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void setOnComplaintViewHandler(UserComplaintHistoryClickHandler onComplaintViewHandler) {
        this.onComplaintViewHandler = onComplaintViewHandler;
    }

    public void setData(List<Complaints> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            ComplaintHistoryViewModel complaintViewModel = new ComplaintHistoryViewModel();
            complaintViewModel.clone(data.get(index));
            items.add(complaintViewModel);
        }
    }

    public void addData(List<Complaints> data) {
        for (int index = 0; index < data.size(); index++) {
            ComplaintHistoryViewModel complaintViewModel = new ComplaintHistoryViewModel();
            complaintViewModel.clone(data.get(index));
            items.add(complaintViewModel);
        }
    }

    public ComplaintHistoryViewModel getItem(int position) {
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ComplaintHistoryAdapterBinding mComplaintHistoryAdapterBinding;

        public ViewHolder(ComplaintHistoryAdapterBinding mComplaintHistoryAdapterBinding) {
            super(mComplaintHistoryAdapterBinding.getRoot());
            this.mComplaintHistoryAdapterBinding = mComplaintHistoryAdapterBinding;
        }
    }
}
