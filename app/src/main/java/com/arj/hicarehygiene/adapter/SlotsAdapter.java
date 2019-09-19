package com.arj.hicarehygiene.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.HomePestAdapterBinding;
import com.arj.hicarehygiene.databinding.SlotsAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.network.model.slots.TimeSlot;
import com.arj.hicarehygiene.viewmodel.SlotsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private List<SlotsViewModel> items = null;
    private Activity context;
    private int lastSelectedPosition = 0;

    public SlotsAdapter(FragmentActivity activity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.context = activity;
    }

    @Override
    public SlotsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlotsAdapterBinding mSlotsAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.slots_adapter, parent, false);
        return new SlotsAdapter.ViewHolder(mSlotsAdapterBinding);
    }

    @Override
    public void onBindViewHolder(final SlotsAdapter.ViewHolder holder,  int position) {
        String slots = items.get(position).getStartTime() + " to " + items.get(position).getFinishTime();
        holder.mSlotsAdapterBinding.txtSlots.setText(slots);
        if(position == lastSelectedPosition){
            holder.mSlotsAdapterBinding.radioSlots.setChecked(true);
            holder.mSlotsAdapterBinding.relSlot.setBackgroundResource(R.drawable.bg_slot);
        }else {
            holder.mSlotsAdapterBinding.radioSlots.setChecked(true);
            holder.mSlotsAdapterBinding.relSlot.setBackgroundResource(R.drawable.bg_greyslot);
        }
        holder.mSlotsAdapterBinding.radioSlots.setChecked(position == lastSelectedPosition);
    }

    @Override
    public int getItemCount() {
        return items.size();

    }


    public void setData(List<TimeSlot> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            SlotsViewModel slotsViewModel = new SlotsViewModel();
            slotsViewModel.clone(data.get(index));
            items.add(slotsViewModel);
        }
    }

    public void addData(List<TimeSlot> data) {
        for (int index = 0; index < data.size(); index++) {
            SlotsViewModel slotsViewModel = new SlotsViewModel();
            slotsViewModel.clone(data.get(index));
            items.add(slotsViewModel);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SlotsAdapterBinding mSlotsAdapterBinding;

        public ViewHolder(SlotsAdapterBinding mSlotsAdapterBinding) {
            super(mSlotsAdapterBinding.getRoot());
            mSlotsAdapterBinding.relSlot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

            mSlotsAdapterBinding.radioSlots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
            this.mSlotsAdapterBinding = mSlotsAdapterBinding;
        }
    }
}
