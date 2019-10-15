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
import com.intechdev.IpasKala.entity.TransactionsModel;

import java.util.List;

/**
 * Created by HBM on 02/08/2018.
 */

public class TransactionsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TransactionsModel> mList;
    private Context context;

    public TransactionsAdapter(List<TransactionsModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BlogListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transactions, parent, false);
                return new TransactionsAdapter.EventViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TransactionsModel object = mList.get(position);
        object.position = position;
        if (object != null) {
            switch (object.getType()) {
                case OrderModel.VIEW_REQTANGLE:

                    ((EventViewHolder) holder).txtDateSend.setText(object.getDateSend());
                    ((EventViewHolder) holder).txtTrackingCode.setText(object.getTrackingCode());
                    ((EventViewHolder) holder).txtPrice.setText(object.getPrice());
                    ((EventViewHolder) holder).txtPardakhtBabate.setText(object.getPardakhtBabate());
                    ((EventViewHolder) holder).txtPaySerialNo.setText(object.getPaySerialNo());

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
            TransactionsModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public  List<TransactionsModel> getAddressModel()
    {
        return mList;
    }

    public void remove(TransactionsModel city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {


        private TextView txtDateSend;
        private TextView txtTrackingCode;
        private TextView txtPrice;
        private TextView txtPardakhtBabate;
        private TextView txtPaySerialNo;


        public EventViewHolder(View itemView) {
            super(itemView);

            txtDateSend = (TextView) itemView.findViewById(R.id.txtDateSend);
            txtTrackingCode = (TextView) itemView.findViewById(R.id.txtTrackingCode);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtPardakhtBabate = (TextView) itemView.findViewById(R.id.txtPardakhtBabate);
            txtPaySerialNo = (TextView) itemView.findViewById(R.id.txtPaySerialNo);

        }
    }
}
