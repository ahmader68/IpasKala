package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.entity.FavoriteModel;
import com.intechdev.IpasKala.utils.AppUtils;

import java.util.List;

/**
 * Created by HBM on 01/08/2018.
 */

public class FavoritesAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FavoriteModel> mList;
    private Context context;

    public FavoritesAdapter(List<FavoriteModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BlogListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_favorites, parent, false);
                return new FavoritesAdapter.EventViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FavoriteModel object = mList.get(position);
        object.position = position;
        if (object != null) {
            switch (object.getType()) {
                case FavoriteModel.VIEW_REQTANGLE:

                    ((EventViewHolder) holder).txtStockName.setText(object.getProductName());
                    ((EventViewHolder) holder).txtPrice.setText(object.getPrice());

                    ((EventViewHolder) holder).lFavoriteItems.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CLICK_ITEM, object.getProductId(), "", object);
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
            FavoriteModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public  List<FavoriteModel> getAddressModel()
    {
        return mList;
    }

    public void remove(FavoriteModel city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lFavoriteItems;
        private TextView txtStockName;
        private TextView txtPrice;

        public EventViewHolder(View itemView) {
            super(itemView);

            lFavoriteItems = (LinearLayout) itemView.findViewById(R.id.lFavoriteItems);
            txtStockName = (TextView) itemView.findViewById(R.id.txtStockName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);

        }
    }
}
