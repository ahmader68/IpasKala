package com.intechdev.IpasKala;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.InitProg;
import com.intechdev.IpasKala.webservicecall.ItemListStock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivityAppWork {

    boolean backPress = false;
    ApiInterface apiService;
    AppCompatActivity acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        acc = this;

        ImageView img = (ImageView) findViewById(R.id.imgsplash);
        img.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fadein));
        img.getAnimation().start();

        //demoStart();

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

//        Call<InitProg> call1 = apiService.getInitApplication();
//        call1.enqueue(new Callback<InitProg>() {
//            @Override
//            public void onResponse(Call<InitProg> call, Response<InitProg> response) {
//                InitProg user1 = response.body();
//
//                Toast.makeText(getApplicationContext(), user1.toString() , Toast.LENGTH_SHORT).show();
//
//                AppData.saveData(SplashActivity.this, AppData.ActionAD.SETTING_SAVE, response.message());
//
//                if(!backPress) {
//                    startActivity(new Intent(SplashActivity.this, WelcomActivity.class));
//                    finish();
//                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<InitProg> call, Throwable t) {
//                call.cancel();
//            }
//        });

        Call<ItemListStock.ItemListStockObject> cal = apiService.getListStockNew("","");
        cal.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    AppData.saveData(acc, AppData.ActionAD.STOCK_NEW_SAVE, user1.getJsonObject());

                   // Toast.makeText(getApplicationContext(), AppData.getStockNew(acc).getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();

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
            }
        });

        Call<ItemListStock.ItemListStockObject> cal2 = apiService.getListStockBestSale("","");
        cal2.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    AppData.saveData(acc, AppData.ActionAD.STOCK_BEST_SALE_SAVE, user1.getJsonObject());

                    //Toast.makeText(getApplicationContext(), AppData.getStockNew(acc).getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();

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
            }
        });

        Call<ItemListStock.ItemListStockObject> cal3 = apiService.getListStockBestView("","");
        cal3.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    AppData.saveData(acc, AppData.ActionAD.STOCK_BEST_VIEW_SAVE, user1.getJsonObject());

                    //Toast.makeText(getApplicationContext(), AppData.getStockNew(acc).getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();

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
            }
        });

        Call<ItemListStock.ItemListStockObject> cal4 = apiService.getListStockBestView("","");
        cal4.enqueue(new Callback<ItemListStock.ItemListStockObject>() {
            @Override
            public void onResponse(Call<ItemListStock.ItemListStockObject> call, Response<ItemListStock.ItemListStockObject> response) {
                try{
                    ItemListStock.ItemListStockObject user1 = response.body();

                    AppData.saveData(acc, AppData.ActionAD.STOCK_SPESIAL_SAVE, user1.getJsonObject());

                    //Toast.makeText(getApplicationContext(), AppData.getStockNew(acc).getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();

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
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        backPress = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        backPress = true;
    }

    public void demoStart(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 2500);

    }
    @Override
    protected void onResume() {
        super.onResume();
        backPress = false;
//        startActivity(new Intent(SplashActivity.this, WelcomActivity.class));
//        finish();
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);



        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        Call<InitProg> call1 = apiService.getInitApplication();
        call1.enqueue(new Callback<InitProg>() {
            @Override
            public void onResponse(Call<InitProg> call, Response<InitProg> response) {
                AppUtils.sendLog(acc,
                        apiService,
                        "Init",
                        "native=1&Action=Init",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                InitProg user1 = response.body();

                //Toast.makeText(getApplicationContext(), user1.toString() , Toast.LENGTH_SHORT).show();

                AppData.saveData(SplashActivity.this, AppData.ActionAD.SETTING_SAVE, response.body().getJsonObject());

                if(!backPress) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }

            }

            @Override
            public void onFailure(Call<InitProg> call, Throwable t) {
                call.cancel();

                if(!backPress) {
                    //Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }
        });
    }
}
