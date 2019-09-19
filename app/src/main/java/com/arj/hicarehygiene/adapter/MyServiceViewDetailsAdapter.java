package com.arj.hicarehygiene.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.MyServicesAdapterBinding;
import com.arj.hicarehygiene.databinding.ViewServiceDetailAdapterBinding;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.handler.OnServiceDetailClickHandler;
import com.arj.hicarehygiene.network.model.userservices.ServiceTasks;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;
import com.arj.hicarehygiene.utils.AppUtils;
import com.arj.hicarehygiene.viewmodel.ServiceViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyServiceViewDetailsAdapter extends RecyclerView.Adapter<MyServiceViewDetailsAdapter.ViewHolder> {

    private List<ServiceTasks> items = null;

    public MyServiceViewDetailsAdapter(List<ServiceTasks> tasksList) {
       items = tasksList;
    }


    @Override
    public MyServiceViewDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewServiceDetailAdapterBinding mServiceAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.view_service_detail_adapter, parent, false);
        return new MyServiceViewDetailsAdapter.ViewHolder(mServiceAdapterBinding);
    }

    @Override
    public void onBindViewHolder(final MyServiceViewDetailsAdapter.ViewHolder holder, final int position) {
        holder.mViewServiceDetailAdapterBinding.txtDateTime.setText(items.get(position).getAppointmentDateTime());
        holder.mViewServiceDetailAdapterBinding.txtOrder.setText(items.get(position).getOrderNumber__c());
        holder.mViewServiceDetailAdapterBinding.txtReason.setText(items.get(position).getIncomplete_reason__c());
        holder.mViewServiceDetailAdapterBinding.txtSequence.setText(String.valueOf(items.get(position).getService_Sequence_Number__c()));
        holder.mViewServiceDetailAdapterBinding.txtStatus.setText(items.get(position).getTechnician_Status());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


//
//    public void setData(List<Service_Details> data) {
//        items.clear();
//        for (int index = 0; index < data.size(); index++) {
//            ServiceViewModel complaintViewModel = new ServiceViewModel();
//            complaintViewModel.clone(data.get(index));
//            items.add(complaintViewModel);
//        }
//    }
//
//    public void addData(List<Service_Details> data) {
//        for (int index = 0; index < data.size(); index++) {
//            ServiceViewModel complaintViewModel = new ServiceViewModel();
//            complaintViewModel.clone(data.get(index));
//            items.add(complaintViewModel);
//        }
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewServiceDetailAdapterBinding mViewServiceDetailAdapterBinding;

        public ViewHolder(ViewServiceDetailAdapterBinding mViewServiceDetailAdapterBinding) {
            super(mViewServiceDetailAdapterBinding.getRoot());
            this.mViewServiceDetailAdapterBinding = mViewServiceDetailAdapterBinding;
        }
    }
}
