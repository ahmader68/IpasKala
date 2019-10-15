package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.intechdev.IpasKala.entity.GroupStockModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TabAdapter extends BaseAdapter {


    Context context;
    ArrayList<GroupStockModel> lstMainModel;
    private static LayoutInflater inflater=null;

    public TabAdapter(Context mainActivity, ArrayList<GroupStockModel> lstMainModel) {

        this.lstMainModel = lstMainModel;
        context=mainActivity;
        inflater = (LayoutInflater) context. getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lstMainModel.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView os_text;
        ImageView os_img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView = null;

//        rowView = inflater.inflate(R.layout.list_item_group_goods, null);
//        holder.os_text =(TextView) rowView.findViewById(R.id.txtGroupGoods);
//        holder.os_img =(ImageView) rowView.findViewById(R.id.imgGroupGoods);

        holder.os_text.setText(lstMainModel.get(position).getTITLE());
        //holder.os_img.setImageResource(lstMainModel.get(position).getTitle());
//        Picasso.with(context).load(lstMainModel.get(position).getIMAGE_URL() == null ? "file:///android_asset/aa.png" : SomeService.IMAGE_URL + lstMainModel.get(position).getIMAGE_URL())
//                .resize(150, 150)
//                .centerCrop()
//                .into(holder.os_img);

        Picasso.get().load(lstMainModel.get(position).getImageUrl().length() <= 0 ? "file:///android_asset/capture1.jpg" : lstMainModel.get(position).getImageUrl())
                .resize(50, 50)
                .centerCrop()
                .into(holder.os_img);

//        rowView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CLICK_ITEM, lstMainModel.get(position).getID() + "", lstMainModel.get(position).getTITLE());
//                //Toast.makeText(context, "You Clicked "+ lstMainModel.get(position).getTITLE(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return rowView;
    }

}