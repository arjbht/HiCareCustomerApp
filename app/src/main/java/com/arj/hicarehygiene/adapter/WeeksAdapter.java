package com.arj.hicarehygiene.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.HomePestAdapterBinding;
import com.arj.hicarehygiene.databinding.RescheduleAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.handler.OnRescheduleClickHandler;
import com.arj.hicarehygiene.network.model.weeks.WeekModel;
import com.arj.hicarehygiene.utils.AppUtils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class WeeksAdapter extends RecyclerView.Adapter<WeeksAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private OnRescheduleClickHandler onRescheduleClickHandler;
    private List<WeekModel> items = null;
    private DateTime dateTime = null;
    private TextView txtMonth;
    private String month = "";
    private String year = "";
    private int lastSelectedPosition = 0;

    public WeeksAdapter(ArrayList<WeekModel> weekList, DateTime today, TextView txtMonth) {
        if (items == null) {
            this.items = weekList;
        }
        this.dateTime = today;
        this.txtMonth = txtMonth;
    }

    public void setOnItemClickHandler(OnRescheduleClickHandler onRescheduleClickHandler) {
        this.onRescheduleClickHandler = onRescheduleClickHandler;
    }


    @Override
    public WeeksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RescheduleAdapterBinding mRescheduleAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.reschedule_adapter, parent, false);
        return new WeeksAdapter.ViewHolder(mRescheduleAdapterBinding);
    }

    @Override
    public void onBindViewHolder(final WeeksAdapter.ViewHolder holder, final int position) {
        holder.mRescheduleAdapterBinding.txtDays.setText(items.get(position).getDays());
        holder.mRescheduleAdapterBinding.txtDate.setText(items.get(position).getDate());

//        holder.mRescheduleAdapterBinding.txtDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onRescheduleClickHandler.onDateSelected(position);
//                holder.mRescheduleAdapterBinding.txtDate.setBackgroundResource(R.drawable.circle);
//                holder.mRescheduleAdapterBinding.txtDate.setTextColor(Color.WHITE);
//                month = items.get(position).getDateTime().toString("MMMM");
//                year = items.get(position).getDateTime().toString("yy");
//                txtMonth.setText(month + " " + year);
//            }
//        });

        if (lastSelectedPosition == position) {
            onRescheduleClickHandler.onDateSelected(position);
            holder.mRescheduleAdapterBinding.txtDate.setBackgroundResource(R.drawable.circle);
            holder.mRescheduleAdapterBinding.txtDate.setTextColor(Color.WHITE);
            month = items.get(position).getDateTime().toString("MMMM");
            year = items.get(position).getDateTime().toString("yyyy");
            txtMonth.setText(month + " " + year);
        } else {
            holder.mRescheduleAdapterBinding.txtDate.setBackgroundResource(R.drawable.grey_circle);
            holder.mRescheduleAdapterBinding.txtDate.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void setOnRescheduleClickHandler(OnRescheduleClickHandler onRescheduleClickHandler) {
        this.onRescheduleClickHandler = onRescheduleClickHandler;
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

        private final RescheduleAdapterBinding mRescheduleAdapterBinding;

        public ViewHolder(RescheduleAdapterBinding mRescheduleAdapterBinding) {
            super(mRescheduleAdapterBinding.getRoot());
            mRescheduleAdapterBinding.txtDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
            this.mRescheduleAdapterBinding = mRescheduleAdapterBinding;
        }
    }
}
