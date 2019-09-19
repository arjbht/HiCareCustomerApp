package com.arj.hicarehygiene.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.activities.OrderViewActivity;
import com.arj.hicarehygiene.databinding.ServiceAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.handler.OnOrderDetailClickHandler;
import com.arj.hicarehygiene.network.model.userservices.Service_Details;
import com.arj.hicarehygiene.utils.TimeUtil;
import com.arj.hicarehygiene.viewmodel.ServiceViewModel;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private static int currentPosition = 0;
    private List<ServiceViewModel> items = null;
    private OnOrderDetailClickHandler onOrderDetailClickHandler;


    public ServicesAdapter() {
        if (items == null) {
            items = new ArrayList<>();
        }

    }

    public void setOnItemClickHandler(OnComplaintClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void setOnOrderDetailClickHandler(OnOrderDetailClickHandler onOrderDetailClickHandler) {
        this.onOrderDetailClickHandler = onOrderDetailClickHandler;
    }



    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ServiceAdapterBinding mServiceAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.service_adapter, parent, false);
        return new ServicesAdapter.ViewHolder(mServiceAdapterBinding);
    }

    @Override
    public void onBindViewHolder(final ServicesAdapter.ViewHolder holder, final int position) {
        holder.mServiceAdapterBinding.txtSequence.setText(String.valueOf(items.get(position).getSequence_No()));
        holder.mServiceAdapterBinding.txtStep.setText( items.get(position).getService_Step());
//        holder.mServiceAdapterBinding.txtStatus.setText("Service Status : " + items.get(position).getStatus());
//        holder.mServiceAdapterBinding.txt.setText(items.get(position).getTechnicianName());
//        holder.mServiceAdapterBinding.txtTechMobile.setText(items.get(position).getTechnicianMobile());
//        holder.mServiceAdapterBinding.txtTechnicianStatus.setText("Status : "+items.get(position).getTechnicianStatus());
        holder.mServiceAdapterBinding.txtStatus.setText(items.get(position).getStatus());
        try {
//            String date = TimeUtil.reFormatDateTime(items.get(position).getScheduled_Date(), "MMM dd, yyyy");
            holder.mServiceAdapterBinding.txtSchedule.setText(items.get(position).getScheduled_Date());
            if (items.get(position).getCompleted_Date() != null && items.get(position).getCompleted_Date().length()>0) {
//                String close_date = TimeUtil.reFormatDateTime(items.get(position).getCompleted_Date(), "MMM dd, yyyy");
                holder.mServiceAdapterBinding.lnrCompletion.setVisibility(View.VISIBLE);
                holder.mServiceAdapterBinding.txtCompletion.setText(items.get(position).getCompleted_Date());
            }else {
                holder.mServiceAdapterBinding.lnrCompletion.setVisibility(View.GONE);
            }

//            if (TimeUtil.isOnDate(items.get(position).getScheduled_Date(), "MMM dd, yyyy")) {
//                holder.mServiceAdapterBinding.lnrReschedule.setVisibility(View.VISIBLE);
//            } else {
//                holder.mServiceAdapterBinding.lnrReschedule.setVisibility(View.VISIBLE);
//            }

            holder.mServiceAdapterBinding.btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onOrderDetailClickHandler.onViewDetailsClicked(position);
                }
            });

            holder.mServiceAdapterBinding.btnReschedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onOrderDetailClickHandler.onRescheduleClicked(position);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
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

        private final ServiceAdapterBinding mServiceAdapterBinding;

        public ViewHolder(ServiceAdapterBinding mServiceAdapterBinding) {
            super(mServiceAdapterBinding.getRoot());
            this.mServiceAdapterBinding = mServiceAdapterBinding;
        }
    }
}
