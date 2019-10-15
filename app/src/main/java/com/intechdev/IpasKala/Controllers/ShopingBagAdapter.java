package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.StockDetailsActivity;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.webservicecall.ItemOrdersBasket;
import com.intechdev.IpasKala.webservicecall.ItemProdAttribs;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.component.SpinnerAdapterAppWork;
import com.intechdev.IpasKala.component.TextViewAppWork;
import com.intechdev.IpasKala.entity.ShopingBagModel;
import com.intechdev.IpasKala.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HBM on 23/04/2018.
 */

public class ShopingBagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemOrdersBasket> mList;
    private Context context;
    public ShopingBagAdapter(List<ItemOrdersBasket> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ShopingBagModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoping_bag_item, parent, false);
                return new ShopingBagAdapter.EventViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemOrdersBasket object = mList.get(position);
        if (object != null) {

            ((EventViewHolder) holder).txtStockName.setText(object.getProductTitle());
            ((EventViewHolder) holder).txtStockDescription.setText(object.getProductBrief());
            ((EventViewHolder) holder).txtPriceOffer.setText(object.getProductDiscount());
            ((EventViewHolder) holder).txtPrice.setText(object.getUnitPrice());

            ((EventViewHolder) holder).txtSumPriceItem.setText(object.getTotalPrice());

            Picasso.get().load(object.getProductimage().length() < 4 ? "file:///android_asset/capture1.jpg" : object.getProductimage())
                    .resize(150, 125)
                    .centerCrop()
                    .into(((EventViewHolder) holder).imgStockSmall);

            ((EventViewHolder) holder).imgStockSmall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent arcive4 = new Intent(context, StockDetailsActivity.class);
                    context.startActivity(arcive4);
                }
            });

            List<String> lstCount = new ArrayList<>();

            for (int i = 1; i <= Integer.parseInt(object.getProductMojoody()); i++) {
                lstCount.add(i + "");
            }

            ((EventViewHolder) holder).spStockCount.setAdapter(new SpinnerAdapterAppWork(context, R.layout.list_item_spinner, lstCount));
            ((EventViewHolder) holder).spStockCount.setSelection(Integer.parseInt(object.getOrderCount()) - 1);

            ((EventViewHolder) holder).spStockCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    if((i+1) != Integer.parseInt(object.getOrderCount())) {
                        ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CHANGE, object.getID(), (i+1)+"", object);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            ((EventViewHolder) holder).btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ItemClicked) context).onItemClicked(AppUtils.ACTION_DELETE_ITEM, object.getID(), "", object);
                }
            });

            try {

                for (ItemProdAttribs item : object.getProdAttr()) {
                    TextViewAppWork view1 = new TextViewAppWork(context);
                    ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    view1.setLayoutParams(lparams);
                    view1.setText(item.getMeghdar_Attribute());
                    view1.setTextSize(10f);
                    try {
                        ((EventViewHolder) holder).llAttribs.addView(view1);
                    } catch (Exception e) {
                        //Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            } catch (Exception e) {
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
            ItemOrdersBasket object = mList.get(position);
            if (object != null) {
                return 1;//object.getType();
            }
        }
        return 0;
    }

    public  List<ItemOrdersBasket> getOrderBasketModel()
    {
        return mList;
    }

    public void remove(ItemOrdersBasket city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }



    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lStockItems;
        private LinearLayout llAttribs;
        private TextView txtStockName;
        private TextView txtStockDescription;
        private TextView txtPrice;
        private TextView txtSumPriceItem;
        private TextView txtPriceOffer;
        private ImageView imgStockSmall;
        private Spinner spStockCount;
        private Button btnDelete;

        public EventViewHolder(View itemView) {
            super(itemView);

            lStockItems = (LinearLayout) itemView.findViewById(R.id.lStockItems);
            llAttribs = (LinearLayout) itemView.findViewById(R.id.llAttribss);
            txtStockName = (TextView) itemView.findViewById(R.id.txtStockName);
            txtStockDescription = (TextView) itemView.findViewById(R.id.txtStockShortDesc);
            txtSumPriceItem = (TextView) itemView.findViewById(R.id.txtSumPriceItem);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtPriceOffer = (TextView) itemView.findViewById(R.id.txtPriceOff);
            imgStockSmall = (ImageView) itemView.findViewById(R.id.imgGood);
            spStockCount = (Spinner) itemView.findViewById(R.id.spStockCount);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }

    }
}