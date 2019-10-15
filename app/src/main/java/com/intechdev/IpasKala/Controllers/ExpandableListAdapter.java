package com.intechdev.IpasKala.Controllers;

/**
 * Created by HBM on 20/04/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.intechdev.IpasKala.webservicecall.ItemGroupStock;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.AppData;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public ExpandableListAdapter(Context context, List<String> expandableListTitle,
                                 HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }


    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }


    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expand_item_group_stock, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }


    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }


    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }


    public int getGroupCount() {
        return this.expandableListTitle.size();
    }


    public long getGroupId(int listPosition) {
        return listPosition;
    }


    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expand_header_group_stock, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        ImageView imgHeaderGroup = (ImageView) convertView.findViewById(R.id.imgHeaderGroup);

        String icon = "";
        for (ItemGroupStock item : AppData.getGroupStock(context).getItems()) {
            if(item.getName().equals(listTitle)){
                icon = item.getIcon();
            }
        }

        Picasso.get().load(icon.length() <= 0 ? "file:///android_asset/capture1.jpg" : icon)
                .resize(50, 50)
                .centerCrop()
                .into(imgHeaderGroup);

        //listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        TextView txtexpand = (TextView) convertView.findViewById(R.id.txtexpand);

        if (isExpanded) {
            txtexpand.setText(context.getResources().getString(R.string.fa_sort_up));
        } else {
            txtexpand.setText(context.getResources().getString(R.string.fa_sort_down));
        }
        return convertView;
    }


    public boolean hasStableIds() {
        return false;
    }


    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
