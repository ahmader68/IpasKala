package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.UserCommentsModel;

import java.util.List;

import static com.intechdev.IpasKala.entity.StockPeropertiesModel.DETILE_ITEM;

/**
 * Created by HBM on 22/04/2018.
 */

public class UserCommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UserCommentsModel> mList;
    Context context;

    public UserCommentsAdapter(List<UserCommentsModel> list) {
        this.mList = list;
    }
    public UserCommentsAdapter(List<UserCommentsModel> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DETILE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_comments_item, parent, false);
                return new UserCommentsAdapter.EventViewHolder(view);
            case UserCommentsModel.PROFILE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message, parent, false);
                return new UserCommentsAdapter.EventViewProfileHolder(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserCommentsModel object = mList.get(position);
        if (object != null) {
            switch (object.getType()) {
                case DETILE_ITEM:
                    ((EventViewHolder) holder).mName.setText(object.getmName());
                    ((EventViewHolder) holder).mDate.setText(object.getmDate());

                    ((EventViewHolder) holder).mLike.setText(object.getmLike());
                    ((EventViewHolder) holder).mDontLike.setText(object.getmDontLike());

                    ((EventViewHolder) holder).mTitle.setText(object.getmTitle());
                    ((EventViewHolder) holder).mDescription.setText(object.getmDescription());
                    break;
                case UserCommentsModel.PROFILE_ITEM:
                    ((EventViewProfileHolder) holder).mName.setText(object.getmName());
                    ((EventViewProfileHolder) holder).mDate.setText(object.getmDate());

                    ((EventViewProfileHolder) holder).mTitle.setText(object.getmTitle());
                    ((EventViewProfileHolder) holder).mDescription.setText(object.getmDescription());
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
            UserCommentsModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDate;
        private TextView mTitle;
        private TextView mDescription;
        private Button mLike;
        private Button mDontLike;
        public EventViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.txtName);
            mDate = (TextView) itemView.findViewById(R.id.txtDate);
            mLike = (Button) itemView.findViewById(R.id.btnLike);
            mDontLike = (Button) itemView.findViewById(R.id.btndontLike);

            mTitle = (TextView) itemView.findViewById(R.id.txtHeader);
            mDescription = (TextView) itemView.findViewById(R.id.txtDesc);
        }
    }

    public static class EventViewProfileHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDate;
        private TextView mTitle;
        private TextView mDescription;
        public EventViewProfileHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.txtName);
            mDate = (TextView) itemView.findViewById(R.id.txtDate);

            mTitle = (TextView) itemView.findViewById(R.id.txtHeader);
            mDescription = (TextView) itemView.findViewById(R.id.txtDesc);
        }
    }
}
