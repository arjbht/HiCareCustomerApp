package com.arj.hicarehygiene.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.HomeCleaningAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;

public class Home_Cleaning_Adapter extends RecyclerView.Adapter<Home_Cleaning_Adapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;

//    private List<ComplaintHistoryViewModel> items = null;

    public Home_Cleaning_Adapter() {
//        if (items == null) {
//            items = new ArrayList<>();
//        }
    }

    public void setOnItemClickHandler(OnComplaintClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }


    @Override
    public Home_Cleaning_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeCleaningAdapterBinding mHomeCleaningAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.home_cleaning_adapter, parent, false);
        return new Home_Cleaning_Adapter.ViewHolder(mHomeCleaningAdapterBinding);
    }

    @Override
    public void onBindViewHolder(Home_Cleaning_Adapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 5;

    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

//    public void setData(List<Complaints> data) {
//        items.clear();
//        for (int index = 0; index < data.size(); index++) {
//            ComplaintHistoryViewModel complaintViewModel = new ComplaintHistoryViewModel();
//            complaintViewModel.clone(data.get(index));
//            items.add(complaintViewModel);
//        }
//    }
//
//    public void addData(List<Complaints> data) {
//        for (int index = 0; index < data.size(); index++) {
//            ComplaintHistoryViewModel complaintViewModel = new ComplaintHistoryViewModel();
//            complaintViewModel.clone(data.get(index));
//            items.add(complaintViewModel);
//        }
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final HomeCleaningAdapterBinding mHomeCleaningAdapterBinding;

        public ViewHolder(HomeCleaningAdapterBinding mHomeCleaningAdapterBinding) {
            super(mHomeCleaningAdapterBinding.getRoot());
            this.mHomeCleaningAdapterBinding = mHomeCleaningAdapterBinding;
        }
    }
}
