package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.intechdev.IpasKala.webservicecall.ItemAdvanceSearch;
import com.intechdev.IpasKala.R;

import java.util.List;

public class AdvanceSearchMasterAdapter extends ArrayAdapter<ItemAdvanceSearch> {

    Context context;
    boolean first = false;

    public AdvanceSearchMasterAdapter(@NonNull Context context, int resource, List<ItemAdvanceSearch> itemAdvanceSearch) {
        super(context, resource, itemAdvanceSearch);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_search_advance_master, null);
            if (position == 0 )
                v.setBackgroundColor(Color.WHITE);
        }

        ItemAdvanceSearch item = getItem(position);

        if (item != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.txtItemMasterValue);
            if (tt1 != null) {
                tt1.setText(item.getName());

                if (position == 0)
                    tt1.setTextColor(Color.BLACK);
            }
        }

        return v;
    }


}
