package com.intechdev.IpasKala.Controllers;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.StockPeropertiesModel;

import java.util.List;

import static com.intechdev.IpasKala.entity.StockPeropertiesModel.DETILE_ITEM;
import static com.intechdev.IpasKala.entity.StockPeropertiesModel.HEARDER_GROUP;

/**
 * Created by HBM on 20/04/2018.
 */

public class StockPeropertiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StockPeropertiesModel> mList;
    public StockPeropertiesAdapter(List<StockPeropertiesModel> list) {
        this.mList = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HEARDER_GROUP:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_peroperties_header, parent, false);
                return new CityViewHolder(view);
            case DETILE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_peroperties_item, parent, false);
                return new EventViewHolder(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StockPeropertiesModel object = mList.get(position);
        if (object != null) {
            switch (object.getType()) {
                case HEARDER_GROUP:
                    ((CityViewHolder) holder).mTitle.setText(object.getName());
                    break;
                case DETILE_ITEM:
                    ((EventViewHolder) holder).mTitle.setText(object.getName());

                    if(object.getDescription().equals("دارد")){
                        Typeface face=Typeface.createFromAsset(holder.itemView.getContext().getAssets(),  "font/fontawesome.ttf");
                        ((EventViewHolder) holder).mDescription.setTypeface(face);
                        ((EventViewHolder) holder).mDescription.setTextColor(holder.itemView.getResources().getColor(R.color.Red));
                        ((EventViewHolder) holder).mDescription.setText(holder.itemView.getResources().getString(R.string.fa_check));
                    }else if(object.getDescription().equals("ندارد")){

                        Typeface face=Typeface.createFromAsset(holder.itemView.getContext().getAssets(),  "font/fontawesome.ttf");
                        ((EventViewHolder) holder).mDescription.setTypeface(face);
                        ((EventViewHolder) holder).mDescription.setTextColor(holder.itemView.getResources().getColor(R.color.Green));
                        ((EventViewHolder) holder).mDescription.setText(holder.itemView.getResources().getString(R.string.fa_times));
                    }
                    else {
                        ((EventViewHolder) holder).mDescription.setTextColor(holder.itemView.getResources().getColor(R.color.colorAccent));
                        ((EventViewHolder) holder).mDescription.setText(object.getDescription());
                    }
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
            StockPeropertiesModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }
    public static class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        public CityViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.titleTextView);
        }
    }
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        public EventViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.titleTextView);
            mDescription = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
