package com.intechdev.IpasKala.Controllers;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.AddressModel;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.utils.AppUtils;

import java.util.List;

/**
 * Created by HBM on 06/05/2018.
 */

public class AddressListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AddressModel> mList;
    private Context context;

    public AddressListAdapter(List<AddressModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BlogListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_address, parent, false);
                return new EventViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final AddressModel object = mList.get(position);
        object.position = position;
        if (object != null) {
            switch (object.getType()) {
                case AddressModel.VIEW_REQTANGLE:

                    ((EventViewHolder) holder).txtArea.setText("استان: " + object.getProvinceTitle());
                    ((EventViewHolder) holder).txtCity.setText("شهرستان: " + object.getCity());
                    ((EventViewHolder) holder).txtPostalcode.setText("کدپستی: " + object.getPostalCode());
                    ((EventViewHolder) holder).txtTell.setText("تلفن: " + object.getTel());
                    ((EventViewHolder) holder).txtAddress.setText("آدرس: " + object.getAddress());

                    ((EventViewHolder) holder).txtDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_DELETE_ITEM, object.getId(), "", object);

                        }
                    });

                    ((EventViewHolder) holder).txtEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_UPDATE_ITEM, object.getId(), "", object);
                        }
                    });

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
            AddressModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public  List<AddressModel> getAddressModel()
    {
        return mList;
    }

    public void remove(AddressModel city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {


        private TextView txtCity;
        private TextView txtArea;
        private TextView txtPostalcode;
        private TextView txtTell;
        private TextView txtAddress;
        private TextView txtEdit;
        private TextView txtDelete;

        public EventViewHolder(View itemView) {
            super(itemView);

            txtCity = (TextView) itemView.findViewById(R.id.txtCity);
            txtArea = (TextView) itemView.findViewById(R.id.txtArea);
            txtPostalcode = (TextView) itemView.findViewById(R.id.txtPostalcode);
            txtTell = (TextView) itemView.findViewById(R.id.txtTell);
            txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
            txtEdit = (TextView) itemView.findViewById(R.id.txtEdit);
            txtDelete = (TextView) itemView.findViewById(R.id.txtDelete);

        }
    }
}
