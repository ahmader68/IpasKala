package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.StockListModel;
import com.intechdev.IpasKala.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HBM on 23/04/2018.
 */

public class StocksListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StockListModel> mList;
    private Context context;

    public StocksListAdapter(List<StockListModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case StockListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stocks_list_item_rectangle, parent, false);
                return new StocksListAdapter.EventViewHolder(view);
            case StockListModel.VIEW_SQUARE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stocks_list_item_square, parent, false);
                return new StocksListAdapter.EventViewHolderSquare(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final StockListModel object = mList.get(position);
        if (object != null) {
            switch (object.getType()) {
                case StockListModel.VIEW_REQTANGLE:

                   ((EventViewHolder) holder).txtStockName.setText(object.getStockName());
                    ((EventViewHolder) holder).txtStockDescription.setText(object.getStockDescription());
                    if(!object.getPriceOffer().equals("-1")) {
                        ((EventViewHolder) holder).txtPriceOffer.setText(object.getPriceOffer());
                    }
                    ((EventViewHolder) holder).txtPrice.setText(object.getPrice());

                    Picasso.get().load(object.getStockUrlImageSmall().length() <= 0 ? "file:///android_asset/capture1.jpg" : object.getStockUrlImageSmall())
                            .resize(150, 125)
                            .centerCrop()
                            .into(((EventViewHolder) holder).imgStockSmall);



                    ((EventViewHolder) holder).lStockItems.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CLICK_ITEM, object.getId() + "", object.getInformMe(), object);
                        }
                    });

                    break;
                case StockListModel.VIEW_SQUARE:

                    ((EventViewHolderSquare) holder).txtStockName.setText(object.getStockName());
                    if(!object.getPriceOffer().equals("-1")) {
                        ((EventViewHolderSquare) holder).txtPriceOffer.setText(object.getPriceOffer());
                    }
                    ((EventViewHolderSquare) holder).txtPrice.setText(object.getPrice());

                    Picasso.get().load(object.getStockUrlImageSmall().length() <= 0 ? "file:///android_asset/capture1.jpg" : object.getStockUrlImageSmall())
                            .resize(150, 125)
                            .centerCrop()
                            .into(((EventViewHolderSquare) holder).imgStockSmall);



                    ((EventViewHolderSquare) holder).lStockItems.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CLICK_ITEM, object.getId() + "", object.getInformMe(), object);
                        }
                    });

                    break;
                    default:
                        Toast.makeText(context, "kjhjkh", Toast.LENGTH_LONG).show();
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
            StockListModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public List<StockListModel> getmList() {
        return mList;
    }

    public void setType(int viewType) {
        if (mList != null) {
            StockListModel object = mList.get(0);
            if (object != null) {
                object.setType(viewType);
            }
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lStockItems;
        private TextView txtStockName;
        private TextView txtStockDescription;
        private TextView txtPrice;
        private TextView txtPriceOffer;
        private ImageView imgStockSmall;

        public EventViewHolder(View itemView) {
            super(itemView);

            lStockItems = (LinearLayout) itemView.findViewById(R.id.lStockItems);
            txtStockName = (TextView) itemView.findViewById(R.id.txtStockName);
            txtStockDescription = (TextView) itemView.findViewById(R.id.txtStockDescription);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtPriceOffer = (TextView) itemView.findViewById(R.id.txtPriceOffer);
            imgStockSmall = (ImageView) itemView.findViewById(R.id.imgStockSmall);
        }
    }

    public static class EventViewHolderSquare extends RecyclerView.ViewHolder {

        private LinearLayout lStockItems;
        private TextView txtStockName;
        private TextView txtStockDescription;
        private TextView txtPrice;
        private TextView txtPriceOffer;
        private ImageView imgStockSmall;

        public EventViewHolderSquare(View itemView) {
            super(itemView);

            lStockItems = (LinearLayout) itemView.findViewById(R.id.lStockItems);
            txtStockName = (TextView) itemView.findViewById(R.id.txtStockName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtPriceOffer = (TextView) itemView.findViewById(R.id.txtPriceOffer);
            imgStockSmall = (ImageView) itemView.findViewById(R.id.imgStockSmall);
        }
    }
}