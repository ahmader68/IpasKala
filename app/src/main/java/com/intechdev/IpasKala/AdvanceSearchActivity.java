package com.intechdev.IpasKala;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.AdvanceSearchDetailAdapter;
import com.intechdev.IpasKala.Controllers.AdvanceSearchMasterAdapter;
import com.intechdev.IpasKala.component.ActivityAppWork;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemAdvanceSearch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvanceSearchActivity extends ActivityAppWork implements ItemClicked {

    AppCompatActivity acc;
    ApiInterface apiService;
    Dialog dialog, popup, progress;
    ArrayList<ItemAdvanceSearch> lstMasterItem = new ArrayList<ItemAdvanceSearch>();
    AdvanceSearchMasterAdapter customAdapterMaster;

    ArrayList<ItemAdvanceSearch> lstDetileItem = new ArrayList<ItemAdvanceSearch>();
    AdvanceSearchMasterAdapter customAdapterDetile;

    ListView lvMaster;
    AdvanceSearchDetailAdapter adapter;
    RecyclerView mRecyclerView;
    public static List<ItemAdvanceSearch> lstItemAdvSearch;
    public static String isexists = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_advance_search);

        acc = this;
        progress = AppUtils.showProgressDialog(acc, "");
        lvMaster = findViewById(R.id.lstSearchItem);
        mRecyclerView  = findViewById(R.id.recyclerView);

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.llClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();
            }
        });

        findViewById(R.id.btnFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                String prodAttribs = "";
                String price1 = "";
                String price2 = "";


                for (ItemAdvanceSearch item : lstItemAdvSearch){
                    if(item.getParentId().length() > 0){

                        if(item.getParentId().equals("-1")) {
                            price1 = item.getPrice1();
                            price2 = item.getPrice2();
                        }else if(item.getIsSelected() != null && item.getIsSelected().equals("1")){
                            prodAttribs += item.getId() + ",";
                        }
                    }
                }

                if(((Switch)findViewById(R.id.switch1)).isChecked()){
                    isexists = "1";
                }else {
                    isexists = "";
                }

                returnIntent.putExtra("prodAttribs",prodAttribs);
                returnIntent.putExtra("price1",price1);
                returnIntent.putExtra("price2",price2);
                returnIntent.putExtra("isexists",isexists);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        if(lstItemAdvSearch != null){
            createSearchFild();
            if(isexists.equals("1")){
                ((Switch)findViewById(R.id.switch1)).setChecked(true);
            }
        }else{
            lstItemAdvSearch = new ArrayList<ItemAdvanceSearch>();
            getAdvanceSearchItem();
        }

        EditText inputSearch = (EditText) findViewById(R.id.etSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }

            @Override
            public void afterTextChanged(Editable arg0) {}
        });

        findViewById(R.id.llClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clearChecked();
                for(ItemAdvanceSearch item : lstItemAdvSearch){
                    item.setIsSelected("0");
                    item.setPrice1("");
                    item.setPrice2("");
                }
                ((Switch)findViewById(R.id.switch1)).setChecked(false);
                isexists = "";
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void getAdvanceSearchItem(){
        progress.show();
        Call<ItemAdvanceSearch.ItemAdvanceSearchObject> call1 = apiService.getAdvanceSearchItem();

        call1.enqueue(new Callback<ItemAdvanceSearch.ItemAdvanceSearchObject>() {
            @Override
            public void onResponse(Call<ItemAdvanceSearch.ItemAdvanceSearchObject> call, Response<ItemAdvanceSearch.ItemAdvanceSearchObject> response) {
                try {
                    ItemAdvanceSearch.ItemAdvanceSearchObject user1 =  response.body();
                    //Toast.makeText(getApplicationContext(), user1.getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();
                    fillData(user1.getItems());


                }catch (Exception s){
                    AppUtils.sendLog(acc,
                                     apiService,
                                     "Items",
                                     "Action=Items&module=CmsCat&type=ProductAttributes&showimage=0&native=1",
                                     response.body().toString(),
                                     AppUtils.getUserId(acc),
                                     "");

                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ItemAdvanceSearch.ItemAdvanceSearchObject> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();

            }
        });
    }

    public void fillData(List<ItemAdvanceSearch> lstItemAdvanceSearch) {
//        if (lstItemAdvanceSearch == null || lstItemAdvanceSearch.size() <= 0)
//            return;

        lstItemAdvSearch.add(new ItemAdvanceSearch(
                "-1",
                "قیمت",
                "",
                "",
                "0",
                "",
                "",
                "",
                "",
                "",
                "",
                "0",
                ItemAdvanceSearch.VIEW_CHECK_BOX
        ));

        lstItemAdvSearch.add(new ItemAdvanceSearch(
                "-20",
                "قیمت از قیمت تا",
                "",
                "",
                "-1",
                "",
                "",
                "",
                "",
                "",
                "",
                "0",
                ItemAdvanceSearch.VIEW_FROM_TO
        ));

        for (ItemAdvanceSearch item : lstItemAdvanceSearch) {
            lstItemAdvSearch.add(item);
        }


        createSearchFild();
    }

    public void createSearchFild(){

        for (ItemAdvanceSearch item : lstItemAdvSearch) {
            if (item.getParentId().equals("0")) {
                lstMasterItem.add(new ItemAdvanceSearch(
                        item.getId(),
                        item.getName(),
                        item.getBrief(),
                        item.getTag(),
                        item.getParentId(),
                        item.getIsImportant(),
                        item.getIcon(),
                        item.getType(),
                        item.getColorCode(),
                        item.getPrice1(),
                        item.getPrice2(),
                        item.getIsSelected(),
                        ItemAdvanceSearch.VIEW_FROM_TO
                ));
            }
        }

        for (ItemAdvanceSearch item : lstItemAdvSearch) {
            if (lstMasterItem.get(0).getId().equals(item.getParentId())) {
                lstDetileItem.add(new ItemAdvanceSearch(
                        item.getId(),
                        item.getName(),
                        item.getBrief(),
                        item.getTag(),
                        item.getParentId(),
                        item.getIsImportant(),
                        item.getIcon(),
                        item.getType(),
                        item.getColorCode(),
                        item.getPrice1(),
                        item.getPrice2(),
                        item.getIsSelected(),
                        ItemAdvanceSearch.VIEW_FROM_TO
                ));
            }
        }

        customAdapterMaster = new AdvanceSearchMasterAdapter(this, R.layout.list_item_search_advance_master, lstMasterItem);
        lvMaster.setAdapter(customAdapterMaster);


        adapter = new AdvanceSearchDetailAdapter(lstDetileItem, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);


        lvMaster.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lstDetileItem.clear();
                adapter.notifyDataSetChanged();
                for (ItemAdvanceSearch item : lstItemAdvSearch){
                    if(customAdapterMaster.getItem(i).getId().equals(item.getParentId())){
                        lstDetileItem.add(new ItemAdvanceSearch(
                                item.getId(),
                                item.getName(),
                                item.getBrief(),
                                item.getTag(),
                                item.getParentId(),
                                item.getIsImportant(),
                                item.getIcon(),
                                item.getType(),
                                item.getColorCode(),
                                item.getPrice1(),
                                item.getPrice2(),
                                item.getIsSelected(),
                                item.getItemType()
                        ));
                    }
                }

                adapter = new AdvanceSearchDetailAdapter(lstDetileItem, acc);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(acc, RecyclerView.VERTICAL, false);
                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);

                try {
                    for (int ii = 0; ii < adapterView.getCount(); ii++) {
                        adapterView.getChildAt(ii).setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));
                        ((TextView) adapterView.getChildAt(ii).findViewById(R.id.txtItemMasterValue)).setTextColor(getResources().getColor(R.color.White));
                    }

                    ((TextView) view.findViewById(R.id.txtItemMasterValue)).setTextColor(Color.BLACK);
                    view.setBackgroundColor(Color.WHITE);
                }catch (Exception e){
                    Log.e("error set color", e.getMessage());
                }
            }
        });


    }

    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_CLICK_ITEM) {

            for (ItemAdvanceSearch item : lstItemAdvSearch){
                if(item.getId().equals(id)){
                    if(item.getIsSelected() == null || item.getIsSelected().equals("0")){
                        item.setIsSelected("1");
                    }else {
                        item.setIsSelected("0");
                    }

                }
            }
        }else if(action == AppUtils.ACTION_CHANGE){
            ItemAdvanceSearch selectItem = (ItemAdvanceSearch)object;
            for (ItemAdvanceSearch item : lstItemAdvSearch){
                if(item.getId().equals(id)){
                    item.setPrice1(selectItem.getPrice1());
                    item.setPrice2(selectItem.getPrice2());
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED,returnIntent);
        super.onBackPressed();
    }
}
