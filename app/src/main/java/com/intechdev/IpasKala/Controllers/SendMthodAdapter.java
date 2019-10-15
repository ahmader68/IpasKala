package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.intechdev.IpasKala.webservicecall.ItemPostTypes;
import com.intechdev.IpasKala.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HBM on 11/08/2018.
 */

public class SendMthodAdapter  extends ArrayAdapter<ItemPostTypes> {

    private static final String TAG = "SendMthodAdapter";
    private List<ItemPostTypes> cardList = new ArrayList<ItemPostTypes>();

    static class CardViewHolder {
        TextView name;
        TextView price;
    }

    public SendMthodAdapter(Context context, int textViewResourceId, List<ItemPostTypes> cardList) {
        super(context, textViewResourceId);
        this.cardList = cardList;
    }

    @Override
    public void add(ItemPostTypes object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public ItemPostTypes getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SendMthodAdapter.CardViewHolder viewHolder;
        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_send_stock_mothod, parent, false);
            viewHolder = new SendMthodAdapter.CardViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.txtItemName);
            viewHolder.price = (TextView) row.findViewById(R.id.txtItemValue);

            row.setTag(viewHolder);
        } else {
            viewHolder = (SendMthodAdapter.CardViewHolder) row.getTag();
        }

        ItemPostTypes card = getItem(position);
        viewHolder.name.setText(card.getName());
        viewHolder.price.setText("هزینه ارسال : " + card.getPrice() + " تومان");

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