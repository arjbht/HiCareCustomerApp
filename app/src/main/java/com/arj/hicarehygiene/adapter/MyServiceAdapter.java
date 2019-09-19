package com.arj.hicarehygiene.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.OrderViewActivity;
import com.arj.hicarehygiene.databinding.MyServicesAdapterBinding;
import com.arj.hicarehygiene.databinding.ServiceAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.handler.OnServiceDetailClickHandler;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;
import com.arj.hicarehygiene.utils.AppUtils;
import com.arj.hicarehygiene.utils.TimeUtil;
import com.arj.hicarehygiene.viewmodel.ServiceViewModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MyServiceAdapter extends RecyclerView.Adapter<MyServiceAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private OnServiceDetailClickHandler onServiceDetailClickHandler;
    private List<ServiceViewModel> items = null;
    private Activity mContext;

    public MyServiceAdapter(FragmentActivity context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }


    public void setOnServiceDetailClickHandler(OnServiceDetailClickHandler onServiceDetailClickHandler) {
        this.onServiceDetailClickHandler = onServiceDetailClickHandler;
    }

    @Override
    public MyServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyServicesAdapterBinding mServiceAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.my_services_adapter, parent, false);
        return new MyServiceAdapter.ViewHolder(mServiceAdapterBinding);
    }

    @Override
    public void onBindViewHolder(final MyServiceAdapter.ViewHolder holder, final int position) {
//        holder.mServiceAdapterBinding.lnrTop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (holder.mServiceAdapterBinding.lnrTop.getTag() != null && holder.mServiceAdapterBinding.lnrTop.getTag().toString().equals("180")) {
//                    ObjectAnimator anim = ObjectAnimator.ofFloat(holder.mServiceAdapterBinding.imgArrow, "rotation", 180, 0);
//                    anim.setDuration(500);
//                    anim.start();
//                    holder.mServiceAdapterBinding.lnrTop.setTag("");
//                } else {
//                    ObjectAnimator anim = ObjectAnimator.ofFloat(holder.mServiceAdapterBinding.imgArrow, "rotation", 0, 180);
//                    anim.setDuration(500);
//                    anim.start();
//                    holder.mServiceAdapterBinding.lnrTop.setTag(180 + "");
//                }
//
//                if (holder.mServiceAdapterBinding.lnrDetails.getVisibility() == View.VISIBLE) {
//                    holder.mServiceAdapterBinding.lnrDetails.setVisibility(View.GONE);
//                } else {
//                    Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.splash);
//                    holder.mServiceAdapterBinding.lnrDetails.startAnimation(animation);
//                    holder.mServiceAdapterBinding.lnrDetails.setVisibility(View.VISIBLE);
//                }
//            }
//        });
        holder.mServiceAdapterBinding.txtStatus.setText(items.get(position).getStatus());
        holder.mServiceAdapterBinding.txtOrderNo.setText(items.get(position).getOrder_Number__c());
        holder.mServiceAdapterBinding.txtSchedule.setText(items.get(position).getScheduled_Date());
        holder.mServiceAdapterBinding.txtService.setText(items.get(position).getService_Plan__c());
        holder.mServiceAdapterBinding.txtSequence.setText(String.valueOf(items.get(position).getSequence_No()));
        holder.mServiceAdapterBinding.txtStep.setText(items.get(position).getService_Step());
        holder.mServiceAdapterBinding.imgType.setImageResource(AppUtils.getImageByCode(items.get(position).getService_Group_Code__c()));
        if (items.get(position).getCompleted_Date() != null && items.get(position).getCompleted_Date().length() > 0) {
            holder.mServiceAdapterBinding.lnrCompletion.setVisibility(View.VISIBLE);
            holder.mServiceAdapterBinding.txtCompletion.setText(items.get(position).getCompleted_Date());
        } else {
            holder.mServiceAdapterBinding.lnrCompletion.setVisibility(View.GONE);
        }

        holder.mServiceAdapterBinding.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onServiceDetailClickHandler.onViewDetailClicked(position);
            }
        });

        holder.mServiceAdapterBinding.btnReschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onServiceDetailClickHandler.onRescheduleClicked(position);
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


    public void setData(List<Service_Details> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            ServiceViewModel complaintViewModel = new ServiceViewModel();
            complaintViewModel.clone(data.get(index));
            items.add(complaintViewModel);
        }
    }

    public void addData(List<Service_Details> data) {
        for (int index = 0; index < data.size(); index++) {
            ServiceViewModel complaintViewModel = new ServiceViewModel();
            complaintViewModel.clone(data.get(index));
            items.add(complaintViewModel);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final MyServicesAdapterBinding mServiceAdapterBinding;

        public ViewHolder(MyServicesAdapterBinding mServiceAdapterBinding) {
            super(mServiceAdapterBinding.getRoot());
            this.mServiceAdapterBinding = mServiceAdapterBinding;
        }
    }
}
