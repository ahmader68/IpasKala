package com.intechdev.IpasKala.Controllers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.webservicecall.ItemAdvanceSearch;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.AddressModel;
import com.intechdev.IpasKala.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HBM on 06/05/2018.
 */

public class AdvanceSearchDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<ItemAdvanceSearch> mList;
    private List<ItemAdvanceSearch> mListMasterData;
    private Context context;
    private ValueFilter valueFilter;

    public AdvanceSearchDetailAdapter(List<ItemAdvanceSearch> list, Context context) {
        this.mList = list;
        this.mListMasterData = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ItemAdvanceSearch.VIEW_CHECK_BOX:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search_advance_detail, parent, false);
                return new EventViewHolder(view);
            case ItemAdvanceSearch.VIEW_FROM_TO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search_advance_detail_from_to, parent, false);
                return new EventViewPriceHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ItemAdvanceSearch object = mList.get(position);
        object.position = position;
        if (object != null) {
            switch (object.getItemType()) {
                case ItemAdvanceSearch.VIEW_CHECK_BOX:

                    ((EventViewHolder) holder).chkItemDetiel.setText(object.getName());
                    ((EventViewHolder) holder).chkItemDetiel.setChecked(object.getIsSelected() != null && !object.getIsSelected().equals("0"));

                    ((EventViewHolder) holder).chkItemDetiel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CLICK_ITEM, object.getId(), "", object);

                            if(object.getIsSelected() == null || object.getIsSelected().equals("0")){

                                for(int i = 0; i < mListMasterData.size(); i++){
                                    if(mListMasterData.get(i).getId().equals(object.getId())){
                                        mListMasterData.get(i).setIsSelected("1");
                                        break;
                                    }
                                }

                                for(int i = 0; i < mList.size(); i++){
                                    if(mList.get(i).getId().equals(object.getId())){
                                        mList.get(i).setIsSelected("1");
                                        break;
                                    }
                                }
                            }else {

                                for(int i = 0; i < mListMasterData.size(); i++){
                                    if(mListMasterData.get(i).getId().equals(object.getId())){
                                        mListMasterData.get(i).setIsSelected("0");
                                        break;
                                    }
                                }

                                for(int i = 0; i < mList.size(); i++){
                                    if(mList.get(i).getId().equals(object.getId())){
                                        mList.get(i).setIsSelected("0");
                                        break;
                                    }
                                }
                            }
                        }
                    });

                    break;
                case ItemAdvanceSearch.VIEW_FROM_TO:

                    ((EventViewPriceHolder) holder).etPrice1.setText(object.getPrice1());
                    ((EventViewPriceHolder) holder).etPrice2.setText(object.getPrice2());

                    ((EventViewPriceHolder) holder).etPrice1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            object.setPrice1(charSequence.toString());
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CHANGE, object.getId(), "", object);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                    ((EventViewPriceHolder) holder).etPrice2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            object.setPrice2(charSequence.toString());
                            ((ItemClicked) context).onItemClicked(AppUtils.ACTION_CHANGE, object.getId(), "", object);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

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
            ItemAdvanceSearch object = mList.get(position);
            if (object != null) {
                return object.getItemType();
            }
        }
        return 0;
    }

    public  List<ItemAdvanceSearch> getAddressModel()
    {
        return mList;
    }

    public void remove(AddressModel city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clearChecked(){
        for(ItemAdvanceSearch item : mList){
            item.setIsSelected("0");
            item.setPrice1("");
            item.setPrice2("");
        }

        for(ItemAdvanceSearch item : mListMasterData){
            item.setIsSelected("0");
            item.setPrice1("");
            item.setPrice2("");
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<ItemAdvanceSearch> filterList = new ArrayList<ItemAdvanceSearch>();
                for (int i = 0; i < mListMasterData.size(); i++) {
                    if ((mListMasterData.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        ItemAdvanceSearch bean = new ItemAdvanceSearch(
                                mListMasterData.get(i).getId(),
                                mListMasterData.get(i).getName(),
                                mListMasterData.get(i).getBrief(),
                                mListMasterData.get(i).getTag(),
                                mListMasterData.get(i).getParentId(),
                                mListMasterData.get(i).getIsImportant(),
                                mListMasterData.get(i).getIcon(),
                                mListMasterData.get(i).getType(),
                                mListMasterData.get(i).getColorCode(),
                                mListMasterData.get(i).getPrice1(),
                                mListMasterData.get(i).getPrice2(),
                                mListMasterData.get(i).getIsSelected(),
                                mListMasterData.get(i).getItemType());
                        filterList.add(bean);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mListMasterData.size();
                results.values = mListMasterData;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mList = (ArrayList<ItemAdvanceSearch>) results.values;
            notifyDataSetChanged();
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private CheckBox chkItemDetiel;

        public EventViewHolder(View itemView) {
            super(itemView);

            chkItemDetiel = (CheckBox) itemView.findViewById(R.id.chkItemDetiel);
        }
    }

    public static class EventViewPriceHolder extends RecyclerView.ViewHolder {

        private EditText etPrice1;
        private EditText etPrice2;

        public EventViewPriceHolder(View itemView) {
            super(itemView);

            etPrice1 = (EditText) itemView.findViewById(R.id.etPrice1);
            etPrice2 = (EditText) itemView.findViewById(R.id.etPrice2);
        }
    }
}
