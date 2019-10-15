package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.entity.DiscountsModel;
import com.intechdev.IpasKala.entity.OrderModel;

import java.util.List;

/**
 * Created by HBM on 02/08/2018.
 */

public class DiscountsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DiscountsModel> mList;
    private Context context;

    public DiscountsAdapter(List<DiscountsModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BlogListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_discount, parent, false);
                return new DiscountsAdapter.EventViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DiscountsModel object = mList.get(position);
        object.position = position;
        if (object != null) {
            switch (object.getType()) {
                case OrderModel.VIEW_REQTANGLE:

                    ((EventViewHolder) holder).txtTitle.setText(object.getTitle());
                    ((EventViewHolder) holder).txtCreateDate.setText(object.getCreateDate());
                    ((EventViewHolder) holder).txtExpireDate.setText(object.getExpireDate());
                    ((EventViewHolder) holder).txtDiscount.setText(object.getDiscount());
                    ((EventViewHolder) holder).txtHadaghalKharidWithLable.setText(object.getHadaghalKharid());
                    ((EventViewHolder) holder).txtCode.setText(object.getCode());

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
            DiscountsModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public  List<DiscountsModel> getAddressModel()
    {
        return mList;
    }

    public void remove(DiscountsModel city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtCreateDate;
        private TextView txtExpireDate;
        private TextView txtDiscount;
        private TextView txtHadaghalKharidWithLable;
        private TextView txtCode;

        public EventViewHolder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtCreateDate = (TextView) itemView.findViewById(R.id.txtCreateDate);
            txtExpireDate = (TextView) itemView.findViewById(R.id.txtExpireDate);
            txtDiscount = (TextView) itemView.findViewById(R.id.txtDiscount);
            txtHadaghalKharidWithLable = (TextView) itemView.findViewById(R.id.txtHadaghalKharidWithLable);
            txtCode = (TextView) itemView.findViewById(R.id.txtCode);
        }
    }
}
