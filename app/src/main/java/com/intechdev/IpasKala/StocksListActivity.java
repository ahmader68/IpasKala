package com.intechdev.IpasKala;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.StocksListAdapter;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.entity.StockListModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemListStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.intechdev.IpasKala.utils.AppUtils.context;

public class StocksListActivity extends AppCompatActivityAppWork implements ItemClicked{

    Dialog dialog;
    boolean isProductViewAsList = true;
    StocksListAdapter adapter;
    RecyclerView mRecyclerView;
    AppCompatActivity acc;
    ApiInterface apiService;
    Dialog progress;

    String catId = "";
    String scatId = "";
    String sort = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks_list);


        findViewById(R.id.lAdvanceSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StocksListActivity.this, AdvanceSearchActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        acc = this;

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sorting);


        findViewById(R.id.lSorting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);
        AppUtils.setBottomToolbarEvent(this);

        progress = AppUtils.showProgressDialog(acc, "در حال دریافت اطلاعات");

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        //loadDemoData();
        catId = getIntent().getStringExtra("catId");
        scatId = getIntent().getStringExtra("scatId");
        ((TextView)findViewById(R.id.txtTitleStockList)).setText(getIntent().getStringExtra("groupTitle"));



        if(getIntent().getIntExtra("spesial", 0) == 1) {
            ((TextView)findViewById(R.id.txtTitleStockList)).setText("پیشنهاد ویژه");
            getSpesialStock();
        }else if(getIntent().getIntExtra("spesial", 0) == 2) {
            ((TextView)findViewById(R.id.txtTitleStockList)).setText("جدیدترینها");
            getNewStockStock();
            int i = getIntent().getIntExtra("filter", 0);
            if(i == 1){
                Intent intent = new Intent(StocksListActivity.this, AdvanceSearchActivity.class);
                startActivityForResult(intent, 1);
            }
        }else if(getIntent().getIntExtra("spesial", 0) == 3) {
            ((TextView)findViewById(R.id.txtTitleStockList)).setText("پرفروشها");
            getBestSaleStock();
        }else if(getIntent().getIntExtra("spesial", 0) == 4) {
            ((TextView)findViewById(R.id.txtTitleStockList)).setText("پربازدیدها");
            getBestViewStock();
        }else {
            //getListStock("1513", "1689", "2");
            getListStock(catId, scatId, sort);
        }

        findViewById(R.id.txtViewMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(adapter != null) {
                    isProductViewAsList = isProductViewAsList?false:true;
                    adapter.setType(isProductViewAsList? StockListModel.VIEW_REQTANGLE : StockListModel.VIEW_SQUARE);
                    ((TextView)findViewById(R.id.txtViewMode)).setText(isProductViewAsList? getString(R.string.fa_list_item) : getString(R.string.fa_grid_item));
                    mRecyclerView.setLayoutManager(isProductViewAsList ? new LinearLayoutManager(getApplicationContext()) : new GridLayoutManager(getApplicationContext(), 2));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        ((RadioGroup)dialog.findViewById(R.id.rbgSorting)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if(adapter != null) {
                    adapter.getmList().clear();
                    adapter.notifyDataSetChanged();
                }

                setPageStatrtSearchData();
                switch(checkedId)
                {
                    case R.id.rbVeryView:
                        sort = "2";
                        getListStock(catId, scatId, sort);
                        ((TextView)findViewById(R.id.txtFilterText)).setText(getString(R.string.porbazdid));
                        break;
                    case R.id.rbVerySale:
                        sort = "5";
                        getListStock(catId, scatId, sort);
                        ((TextView)findViewById(R.id.txtFilterText)).setText(getString(R.string.porforush));
                        break;
                    case R.id.rbSpecialOffer:
                        sort = "4";
                        getListStock(catId, scatId, sort);
                        ((TextView)findViewById(R.id.txtFilterText)).setText(getString(R.string.pishnahadvizhe));
                        break;
                    case R.id.rbPriceTooLow:
                        sort = "7";
                        getListStock(catId, scatId, sort);
                        ((TextView)findViewById(R.id.txtFilterText)).setText(getString(R.string.gheymatziyadbekam));
                        break;
                    case R.id.rbPriceLowToHigh:
                        sort = "8";
                        getListStock(catId, scatId, sort);
                        ((TextView)findViewById(R.id.txtFilterText)).setText(getString(R.string.gheymatkambezyad));
                        break;
                    case R.id.rbNew:
                        sort = "1";
                        getListStock(catId, scatId, sort);
                        ((TextView)findViewById(R.id.txtFilterText)).setText(getString(R.string.jadidtarinha));
                        break;
                }
                dialog.dismiss();
            }

        });

        AdvanceSearchActivity.lstItemAdvSearch = null;
        AdvanceSearchActivity.isexists = "";
     }

    public void loadDemoData() {
        List<StockListModel> list = new ArrayList<>();
        list.add(new StockListModel((long) 1, "سامسونگ", "توضیحات توضیحات", "2500", "1500", "url", "False", StockListModel.VIEW_REQTANGLE));
        list.add(new StockListModel((long) 1, "سامسونگ", "توضیحات توضیحات", "2500", "1500", "url", "True", StockListModel.VIEW_REQTANGLE));
        list.add(new StockListModel((long) 1, "سامسونگ", "توضیحات توضیحات", "2500", "1500", "url", "False", StockListModel.VIEW_REQTANGLE));
        list.add(new StockListModel((long) 1, "سامسونگ", "توضیحات توضیحات", "2500", "1500", "url", "False", StockListModel.VIEW_REQTANGLE));
        list.add(new StockListModel((long) 1, "سامسونگ", "توضیحات توضیحات", "2500", "1500", "url", "False", StockListModel.VIEW_REQTANGLE));
        list.add(new StockListModel((long) 1, "سامسونگ", "توضیحات توضیحات", "2500", "1500", "url", "True", StockListModel.VIEW_REQTANGLE));
        list.add(new StockListModel((long) 1, "سامسونگ", "توضیحات توضیحات", "2500", "1500", "url", "False", StockListModel.VIEW_REQTANGLE));


        adapter = new StocksListAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewstock);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    public void getListStock(String categoryId, String subcat, String sort) {
        setPageStatrtSearchData();
        Call<ItemListStock.ItemListStockObject> call1 = apiService.getListStock(categoryId, subcat, sort);
        //Call<ItemListStock.ItemListStockObject> call1 = apiService.getListStock(categoryId, sort);
        call1.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                AppUtils.sendLog(acc,
                        apiService,
                        "Items",
                        "native=1&Action=Items&module=Prod&type=Products&showimage=2",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                ItemListStock.ItemListStockObject obj = response.body();
                if(adapter != null) {
                    adapter.getmList().clear();
                    adapter.notifyDataSetChanged();
                }
                loadListStock(obj);
            }

            @Override
            public void onFailure(Call<ItemListStock.ItemListStockObject> call, Throwable t) {
                call.cancel();
                setNodatafound();
            }
        });

    }

    public void setPageStatrtSearchData(){
        progress.show();
    }

    public void setNodatafound(){
        Toast.makeText(acc, "موردی جهت نمایش یافت نشد",Toast.LENGTH_LONG).show();
        setPageEndSearchData();
    }

    public void setPageEndSearchData(){
        progress.dismiss();
    }

    public void loadListStock(ItemListStock.ItemListStockObject listItem) {
        try {

            if(listItem == null)
                setNodatafound();

            setPageEndSearchData();

            List<StockListModel> list = new ArrayList<>();

            for (ItemListStock item : listItem.getItems()) {
                list.add(new StockListModel(Long.parseLong(item.getId()),
                        item.getName(),
                        item.getBrief(),
                        item.getPrice(),
                        item.getBuyPrice(),
                        item.getIcon(),
                        item.getInformMe(),
                        StockListModel.VIEW_REQTANGLE));
            }


            adapter = new StocksListAdapter(list, this);
            adapter.setType(isProductViewAsList? StockListModel.VIEW_REQTANGLE : StockListModel.VIEW_SQUARE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewstock);
            //mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setLayoutManager(isProductViewAsList ? new LinearLayoutManager(getApplicationContext()) : new GridLayoutManager(getApplicationContext(), 2));

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void getSpesialStock(){
        setPageStatrtSearchData();
        Call<ItemListStock.ItemListStockObject> cal4 = apiService.getListStockBestView("","");
        cal4.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    if(adapter != null) {
                        adapter.getmList().clear();
                        adapter.notifyDataSetChanged();
                    }

                    loadListStock(user1);

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Items",
                            "Action=Items&module=Prod&type=Products&sort=2&native=1&showimage=2",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemListStock.ItemListStockObject> call, Throwable t) {
                call.cancel();
                setNodatafound();
            }
        });
    }

    public void getNewStockStock(){
        setPageStatrtSearchData();
        Call<ItemListStock.ItemListStockObject> cal4 = apiService.getListStockNew("","");
        cal4.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    if(adapter != null) {
                        adapter.getmList().clear();
                        adapter.notifyDataSetChanged();
                    }

                    loadListStock(user1);

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Items",
                            "Action=Items&module=Prod&type=Products&sort=1&native=1&showimage=2",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemListStock.ItemListStockObject> call, Throwable t) {
                call.cancel();
                setNodatafound();
            }
        });
    }

    public void getBestSaleStock(){
        setPageStatrtSearchData();
        Call<ItemListStock.ItemListStockObject> cal4 = apiService.getListStockBestSale("","");
        cal4.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    if(adapter != null) {
                        adapter.getmList().clear();
                        adapter.notifyDataSetChanged();
                    }

                    loadListStock(user1);

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Items",
                            "Action=Items&module=Prod&type=Products&sort=5&native=1&showimage=2",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemListStock.ItemListStockObject> call, Throwable t) {
                call.cancel();
                setNodatafound();
            }
        });
    }

    public void getBestViewStock(){
        setPageStatrtSearchData();
        Call<ItemListStock.ItemListStockObject> cal4 = apiService.getListStockBestView("","");
        cal4.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    if(adapter != null) {
                        adapter.getmList().clear();
                        adapter.notifyDataSetChanged();
                    }

                    loadListStock(user1);

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Items",
                            "Action=Items&module=Prod&type=Products&sort=2&native=1&showimage=2",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemListStock.ItemListStockObject> call, Throwable t) {
                call.cancel();
                setNodatafound();
            }
        });
    }
    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_CLICK_ITEM) {
            Intent arcive4 = new Intent(StocksListActivity.this, StockDetailsActivity.class);
            arcive4.putExtra("stockId", id);
            arcive4.putExtra("informMe", data);
            context.startActivity(arcive4);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String prodAttribs = data.getStringExtra("prodAttribs");
                String price1 = data.getStringExtra("price1");
                String price2 = data.getStringExtra("price2");
                String isexists = data.getStringExtra("isexists");

                Map<String, String> dataa = new HashMap<>();
                dataa.put("cat", catId);
                dataa.put("scat", scatId);
                dataa.put("sort", sort);

                if (!prodAttribs.equals(""))
                    dataa.put("prodAttribs", prodAttribs);
                if (!price1.equals(""))
                    dataa.put("price1", price1);
                if (!price2.equals(""))
                    dataa.put("price2", price2);
                if (!isexists.equals(""))
                    dataa.put("isexists", isexists);

                setPageStatrtSearchData();
                Call<ItemListStock.ItemListStockObject> call1 = apiService.getListStock(dataa);
                call1.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
                    @Override
                    public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "Items",
                                "native=1&Action=Items&module=Prod&type=Products&showimage=2",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        ItemListStock.ItemListStockObject obj = response.body();
                        if (adapter != null) {
                            adapter.getmList().clear();
                            adapter.notifyDataSetChanged();
                        }
                        loadListStock(obj);
                    }

                    @Override
                    public void onFailure(Call<ItemListStock.ItemListStockObject> call, Throwable t) {
                        if (adapter != null) {
                            adapter.getmList().clear();
                            adapter.notifyDataSetChanged();
                        }
                        call.cancel();
                        setNodatafound();
                    }
                });

            }

        }
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
