package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class StockListMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<StockListModel> mList;
    private Context context;
    public StockListMainAdapter(List<StockListModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case StockListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item_main, parent, false);
                return new StockListMainAdapter.EventViewHolder(view);
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
                    if(object.getPriceOffer().equals("-1") || object.getPriceOffer().equals("0")){
                        ((EventViewHolder) holder).txtPriceOffer.setVisibility(View.GONE);
                        ((EventViewHolder) holder).txtPriceOfferMainTitle.setVisibility(View.GONE);

                        ((EventViewHolder) holder).txtPrice.setText(object.getPrice());
                    }else{
                        ((EventViewHolder) holder).txtPriceOffer.setVisibility(View.VISIBLE);
                        ((EventViewHolder) holder).txtPriceOfferMainTitle.setVisibility(View.VISIBLE);

                        ((EventViewHolder) holder).txtPriceOffer.setPaintFlags(((EventViewHolder) holder).txtPriceOffer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        ((EventViewHolder) holder).txtPriceOfferMainTitle.setPaintFlags(((EventViewHolder) holder).txtPriceOfferMainTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        ((EventViewHolder) holder).txtPrice.setText(object.getPriceOffer());
                        ((EventViewHolder) holder).txtPriceOffer.setText(object.getPrice());
                    }

                    Picasso.get().load(object.getStockUrlImageSmall().length() < 4 ? "file:///android_asset/capture1.jpg" : object.getStockUrlImageSmall())
                            .resize(126, 84)
                            .centerCrop()
                            .into(((EventViewHolder) holder).imgStockSmall);

                    ((EventViewHolder) holder).lItemMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CLICK_ITEM, object.getId() + "", "", object);
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
            StockListModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lItemMain;
        private TextView txtStockName;
        private TextView txtPrice;
        private TextView txtPriceOfferMainTitle;
        private TextView txtPriceOffer;
        private ImageView imgStockSmall;


        public EventViewHolder(View itemView) {
            super(itemView);

            lItemMain = (LinearLayout) itemView.findViewById(R.id.lItemMain);
            txtStockName = (TextView) itemView.findViewById(R.id.txtStockNameMain);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPriceMain);
            txtPriceOfferMainTitle = (TextView) itemView.findViewById(R.id.txtPriceOfferMainTitle);
            txtPriceOffer = (TextView) itemView.findViewById(R.id.txtPriceOfferMain);
            imgStockSmall = (ImageView) itemView.findViewById(R.id.imgStockSmallMain);

        }
    }
}