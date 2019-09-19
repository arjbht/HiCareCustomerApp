package com.arj.hicarehygiene.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arj.hicarehygiene.utils.AppUtils;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.OrdersummaryAdapterBinding;
import com.arj.hicarehygiene.handler.OnComplaintClickHandler;
import com.arj.hicarehygiene.handler.OnListItemClickHandler;
import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.utils.TimeUtil;
import com.arj.hicarehygiene.viewmodel.OrderViewModel;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private List<OrderViewModel> items = null;
    private List<Orders> ordersList = null;

    public OrderSummaryAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        if (ordersList == null) {
            ordersList = new ArrayList<>();
        }
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrdersummaryAdapterBinding mordersummaryAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.ordersummary_adapter, parent, false);
        return new ViewHolder(mordersummaryAdapterBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mordersummaryAdapterBinding.executePendingBindings();
        holder.mordersummaryAdapterBinding.txtOrderNo.setText("Order #"+items.get(position).getOrderNumber());
        if (items.get(position).getServiceGroupCodeC() != null) {
            holder.mordersummaryAdapterBinding.txtService.setText(AppUtils.GetSMSServiceType(items.get(position).getServiceGroupCodeC()));
            holder.mordersummaryAdapterBinding.imgType.setImageResource(AppUtils.getImageByCode(items.get(position).getServiceGroupCodeC()));
        }
        holder.mordersummaryAdapterBinding.txtAmount.setText("\u20B9 " +items.get(position).getPaymentValue());

        if (!items.get(position).getCustStatus().equals("Active")) {
            holder.mordersummaryAdapterBinding.lnrActive.setBackgroundResource(R.drawable.gradient_red);
        }

        holder.mordersummaryAdapterBinding.txtStatus.setText(items.get(position).getCustStatus());
        try {
            String date = TimeUtil.reFormatDate(items.get(position).getStartDate(), "MMM dd, yyyy");
            holder.mordersummaryAdapterBinding.txtDate.setText(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickHandler.onItemClick(position);
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

    public void setData(List<Orders> data) {
        ordersList.clear();
        ordersList = data;
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            OrderViewModel orderViewModel = new OrderViewModel();
            orderViewModel.clone(data.get(index));
            items.add(orderViewModel);
        }
    }

    public void addData(List<Orders> data) {
        ordersList = data;
        for (int index = 0; index < data.size(); index++) {
            OrderViewModel orderViewModel = new OrderViewModel();
            orderViewModel.clone(data.get(index));
            items.add(orderViewModel);
        }
    }

    public OrderViewModel getItem(int position) {
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final OrdersummaryAdapterBinding mordersummaryAdapterBinding;

        public ViewHolder(OrdersummaryAdapterBinding mordersummaryAdapterBinding) {
            super(mordersummaryAdapterBinding.getRoot());
            this.mordersummaryAdapterBinding = mordersummaryAdapterBinding;
        }
    }
}
