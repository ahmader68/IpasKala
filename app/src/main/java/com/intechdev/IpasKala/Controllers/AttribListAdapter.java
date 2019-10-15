package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intechdev.IpasKala.webservicecall.ItemProdAttribs;
import com.intechdev.IpasKala.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HBM on 07/08/2018.
 */

public class AttribListAdapter extends ArrayAdapter<ItemProdAttribs> {

    private static final String TAG = "CardArrayAdapter";
    private List<ItemProdAttribs> cardList = new ArrayList<ItemProdAttribs>();
    private boolean colorBox = true;
    private Context context;

    static class CardViewHolder {
        TextView line1;
        LinearLayout linearLayout;
    }

    public AttribListAdapter(Context context, int textViewResourceId, List<ItemProdAttribs> cardList, boolean colorBox) {
        super(context, textViewResourceId);
        this.cardList = cardList;
        this.colorBox = colorBox;
        this.context = context;
    }

    @Override
    public void add(ItemProdAttribs object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public ItemProdAttribs getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_stock_detail, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.txtItemValue);

            if(colorBox) {
                viewHolder.linearLayout = (LinearLayout) row.findViewById(R.id.llColor);

            }else {
                viewHolder.linearLayout = (LinearLayout) row.findViewById(R.id.llHColor);
                viewHolder.linearLayout.setVisibility(View.GONE);
            }

            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }

        ItemProdAttribs card = getItem(position);
        viewHolder.line1.setText(card.getMeghdar_Attribute());

        if(colorBox) {
            if(card.getColorCode() == null || card.getColorCode().equals(""))
                viewHolder.linearLayout.setBackgroundColor(Color.RED);
            else
                viewHolder.linearLayout.setBackgroundColor(Color.parseColor(card.getColorCode()));
        }else {
            viewHolder.linearLayout.setVisibility(View.GONE);
        }



        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}