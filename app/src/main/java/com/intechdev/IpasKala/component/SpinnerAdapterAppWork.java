package com.intechdev.IpasKala.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.intechdev.IpasKala.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapterAppWork extends ArrayAdapter<String> {

    private static final String TAG = "SendMthodAdapter";
    private List<String> cardList = new ArrayList<String>();

    static class CardViewHolder {
        TextView value;
    }

    public SpinnerAdapterAppWork(Context context, int textViewResourceId, List<String> cardList) {
        super(context, textViewResourceId);
        this.cardList = cardList;
    }

    @Override
    public void add(String object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public String getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SpinnerAdapterAppWork.CardViewHolder viewHolder;
        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_spinner, parent, false);
            viewHolder = new SpinnerAdapterAppWork.CardViewHolder();
            viewHolder.value = (TextView) row.findViewById(R.id.txtItemValue);

            row.setTag(viewHolder);
        } else {
            viewHolder = (SpinnerAdapterAppWork.CardViewHolder) row.getTag();
        }

        String card = getItem(position);
        viewHolder.value.setText(card);

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