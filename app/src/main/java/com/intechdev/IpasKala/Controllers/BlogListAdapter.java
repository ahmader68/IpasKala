package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HBM on 06/05/2018.
 */

public class BlogListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BlogListModel> mList;
    private Context context;

    public BlogListAdapter(List<BlogListModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BlogListModel.VIEW_REQTANGLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_blog, parent, false);
                return new BlogListAdapter.EventViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BlogListModel object = mList.get(position);
        if (object != null) {
            switch (object.getType()) {
                case BlogListModel.VIEW_REQTANGLE:

                    ((BlogListAdapter.EventViewHolder) holder).txtBlogTitle.setText(object.getBlogTitle());
                    ((BlogListAdapter.EventViewHolder) holder).txtBlogDescription.setText(object.getBlogDescription());

                    Picasso.get().load(object.getBlogUrlImageSmall().length() < 4 ? "file:///android_asset/capture1.jpg" : object.getBlogUrlImageSmall())
                            .resize(126, 84)
                            .centerCrop()
                            .into(((EventViewHolder) holder).imgBlogSmall);

                    ((BlogListAdapter.EventViewHolder) holder).lBlogItems.setOnClickListener(new View.OnClickListener() {
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
            BlogListModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lBlogItems;
        private TextView txtBlogTitle;
        private TextView txtBlogDescription;
        private ImageView imgBlogSmall;

        public EventViewHolder(View itemView) {
            super(itemView);

            lBlogItems = (LinearLayout) itemView.findViewById(R.id.lBlogItems);
            txtBlogTitle = (TextView) itemView.findViewById(R.id.txtBlogTitle);
            txtBlogDescription = (TextView) itemView.findViewById(R.id.txtBlogDescription);
            imgBlogSmall = (ImageView) itemView.findViewById(R.id.imgBlogSmall);
        }
    }
}