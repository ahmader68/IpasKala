package com.intechdev.IpasKala;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.intechdev.IpasKala.Controllers.ShopingBagAdapter;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemOrdersBasket;
import com.intechdev.IpasKala.webservicecall.ItemStockDetails;
import com.intechdev.IpasKala.webservicecall.Result;
import com.navdrawer.SimpleSideDrawer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingBagActivity extends AppCompatActivityAppWork implements ItemClicked{

    SimpleSideDrawer searchSlider;
    boolean isProductViewAsList;
    ShopingBagAdapter adapter;
    RecyclerView mRecyclerView;

    SliderLayout mDemoSlider = null;
    MenuSlider ac;
    AppCompatActivity acc;
    ApiInterface apiService;
    ItemStockDetails itemStockDetails;
    Dialog dialog, popup, progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_bag);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.SEARCH_HIDE, AppUtils.MenuBarStat.HOME_HIDE,AppUtils.MenuBarStat.MENU_HIDE,AppUtils.MenuBarStat.BACK_HIDE);
        AppUtils.setToolbarTitle(this, "سبد خرید");
        AppUtils.setToolbarBackButton(this, null);
        AppUtils.setBottomToolbarEvent(this);

        findViewById(R.id.btnPayment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingBagActivity.this, PaymentActivity.class);
                intent.putExtra("sumPrice", ((TextView) findViewById(R.id.txtSumPriceFactor)).getText().toString());
                startActivity(intent);
                finish();
            }
        });
        acc = this;
        progress = AppUtils.showProgressDialog(acc, "");

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        getOrdersBasket();
    }

    public void getOrdersBasket(){
        progress.show();
        if (AppData.getUser(acc) == null) {
            startActivity(new Intent(ShoppingBagActivity.this, LoginActivity.class));
            finish();
        }

        Call<ItemOrdersBasket.ItemOrdersBasketObject> call1 = apiService.showOrdersBasket(AppData.getUser(acc).getResult());

        call1.enqueue(new Callback<ItemOrdersBasket.ItemOrdersBasketObject>() {
            @Override
            public void onResponse(Call<ItemOrdersBasket.ItemOrdersBasketObject> call, Response<ItemOrdersBasket.ItemOrdersBasketObject> response) {
                try {
                    ItemOrdersBasket.ItemOrdersBasketObject user1 =  response.body();

                    if(user1.getSettings() != null) {
                        ((TextView) findViewById(R.id.txtSumPriceFactor)).setText(user1.getSettings().getFinalCost() + "تومان ");
                    }else {
                        ((TextView) findViewById(R.id.txtSumPriceFactor)).setText("");
                    }

                    fillData(user1.getItems());

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "ShowOrdersBasket",
                            "Action=ShowOrdersBasket&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ItemOrdersBasket.ItemOrdersBasketObject> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void fillData(List<ItemOrdersBasket> lstItemOrdersBasket){
        if(lstItemOrdersBasket == null || lstItemOrdersBasket.size() <= 0)
            return;

//        List<ShopingBagModel> list = new ArrayList<>();
//        for (int i=0; i< lstItemOrdersBasket.size(); i++){
//            list.add(new ShopingBagModel(Long.getLong(lstItemOrdersBasket.get(i).getID()),
//                    lstItemOrdersBasket.get(i).getProductTitle(),
//                    lstItemOrdersBasket.get(i).getProductBrief(),
//                    lstItemOrdersBasket.get(i).getUnitPrice(),
//                    lstItemOrdersBasket.get(i).getTotalPrice(),
//                    lstItemOrdersBasket.get(i).getProductimage(),
//                    ShopingBagModel.VIEW_REQTANGLE));
//        }

        adapter = new ShopingBagAdapter(lstItemOrdersBasket, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }
    public void demoData(){
//        List<ShopingBagModel> list = new ArrayList<>();
//        list.add(new ShopingBagModel((long) 1, "توضیحات", "قرمز", "2500", "1500", "url", ShopingBagModel.VIEW_REQTANGLE));
//        list.add(new ShopingBagModel((long) 1, "توضیحات", "قرمز", "2500", "1500", "url", ShopingBagModel.VIEW_REQTANGLE));
//        list.add(new ShopingBagModel((long) 1, "توضیحات", "قرمز", "2500", "1500", "url", ShopingBagModel.VIEW_REQTANGLE));
//        list.add(new ShopingBagModel((long) 1, "توضیحات", "قرمز", "2500", "1500", "url", ShopingBagModel.VIEW_REQTANGLE));
//        list.add(new ShopingBagModel((long) 1, "توضیحات", "قرمز", "2500", "1500", "url", ShopingBagModel.VIEW_REQTANGLE));
//        list.add(new ShopingBagModel((long) 1, "توضیحات", "قرمز", "2500", "1500", "url", ShopingBagModel.VIEW_REQTANGLE));
//        list.add(new ShopingBagModel((long) 1, "توضیحات", "قرمز", "2500", "1500", "url", ShopingBagModel.VIEW_REQTANGLE));
//
//
//        adapter = new ShopingBagAdapter(list, this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_DELETE_ITEM) {
            deleteOrdersBasket(id);

            int position = 0;

            for (ItemOrdersBasket item : adapter.getOrderBasketModel()) {
                if ((item.getID() + "").equals(id)) {
                    break;
                }
                position++;
            }

            adapter.remove(((ItemOrdersBasket) object));
            adapter.notifyItemRemoved(position);
        }else if(action == AppUtils.ACTION_CHANGE){
            // TODO Step 1 - SEND DATA TO SERVER
            // TODO Step 2 - Get Server Shaping Bag

            Toast.makeText(acc, "ارسال اطلاعات به سرور", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOrdersBasket(String orderId){
        progress.show();
        Call<Result> call1 = apiService.DelFromBasket(AppData.getUser(acc).getResult(), orderId);

        call1.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    Result user1 =  response.body();
                    getOrdersBasket();
                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "DelFromBasket",
                            "Action=DelFromBasket&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
