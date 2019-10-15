package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.entity.OrderModel;

import java.util.List;

/**
 * Created by HBM on 01/08/2018.
 */

public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderModel> mList;
    private Context context;

    public OrderListAdapter(List<OrderModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BlogListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orders, parent, false);
                return new OrderListAdapter.EventViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderModel object = mList.get(position);
        object.position = position;
        if (object != null) {
            switch (object.getType()) {
                case OrderModel.VIEW_REQTANGLE:

                    ((EventViewHolder) holder).txtBuyerFullName.setText(object.getBuyerFullName());
                    ((EventViewHolder) holder).txtTotalToPay.setText(object.getTotalToPay());
                    ((EventViewHolder) holder).txtPostAmount.setText(object.getPostAmount());
                    ((EventViewHolder) holder).txtTrackingCode.setText(object.getTrackingCode());
                    ((EventViewHolder) holder).txtCreateDate.setText(object.getCreateDate());
                    ((EventViewHolder) holder).txtOrderDate.setText(object.getOrderDate());
                    ((EventViewHolder) holder).txtTotalItemCount.setText(object.getTotalItemCount());

                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            OrderModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public  List<OrderModel> getAddressModel()
    {
        return mList;
    }

    public void remove(OrderModel city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {


        private TextView txtBuyerFullName;
        private TextView txtTotalToPay;
        private TextView txtPostAmount;
        private TextView txtTrackingCode;
        private TextView txtCreateDate;
        private TextView txtOrderDate;
        private TextView txtTotalItemCount;

        public EventViewHolder(View itemView) {
            super(itemView);

            txtBuyerFullName = (TextView) itemView.findViewById(R.id.txtBuyerFullName);
            txtTotalToPay = (TextView) itemView.findViewById(R.id.txtTotalToPay);
            txtPostAmount = (TextView) itemView.findViewById(R.id.txtPostAmount);
            txtTrackingCode = (TextView) itemView.findViewById(R.id.txtTrackingCode);
            txtCreateDate = (TextView) itemView.findViewById(R.id.txtCreateDate);
            txtOrderDate = (TextView) itemView.findViewById(R.id.txtOrderDate);
            txtTotalItemCount = (TextView) itemView.findViewById(R.id.txtTotalItemCount);

        }
    }
}
