package com.arj.hicarehygiene.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ComplaintHistoryAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.network.model.complaint.Complaints;

import com.arj.hicarehygiene.viewmodel.ComplaintHistoryViewModel;

public class ComplaintHistoryAdapter extends RecyclerView.Adapter<ComplaintHistoryAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;

    private List<ComplaintHistoryViewModel> items = null;

    public ComplaintHistoryAdapter() {
        if (items == null) {
            items = new ArrayList<>();
        }
    }

    public void setOnItemClickHandler(OnComplaintClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }


    @Override
    public ComplaintHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ComplaintHistoryAdapterBinding mComplaintHistoryAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.complaint_history_adapter, parent, false);
        return new ComplaintHistoryAdapter.ViewHolder(mComplaintHistoryAdapterBinding);
    }

    @Override
    public void onBindViewHolder(ComplaintHistoryAdapter.ViewHolder holder, final int position) {
        holder.mComplaintHistoryAdapterBinding.txtDate.setText("Complaint On" + items.get(position).getCreatedDate());
        holder.mComplaintHistoryAdapterBinding.txtStatus.setText(items.get(position).getStatus());
        holder.mComplaintHistoryAdapterBinding.txtService.setText(items.get(position).getComplaint_Description());
    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
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
