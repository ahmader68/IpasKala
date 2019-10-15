package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.intechdev.IpasKala.Controllers.SendMthodAdapter;
import com.intechdev.IpasKala.component.SpinnerAdapterAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemPostTypes;
import com.intechdev.IpasKala.webservicecall.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    ArrayList<String> strColor= new ArrayList<>();
    AppCompatActivity acc;
    ApiInterface apiService;
    Dialog dialog, popup, progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        acc = this;

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);
        AppUtils.setBottomToolbarEvent(this);

        progress = AppUtils.showProgressDialog(acc, "");

        ((TextView)findViewById(R.id.txtBuyingGoods)).setText(acc.getIntent().getStringExtra("sumPrice"));
        ((TextView)findViewById(R.id.txtSumPriceFactor)).setText(acc.getIntent().getStringExtra("sumPrice"));

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        strColor.add("نقدی");
        strColor.add("اینترنتی");

        Spinner spPaymentMethod = (Spinner) findViewById(R.id.spPaymentMethod);
        spPaymentMethod.setAdapter(new SpinnerAdapterAppWork(this,R.layout.list_item_spinner,strColor));

        getPostTypes();

        findViewById(R.id.btnPayment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitBasket();

            }
        });
    }

    public void submitBasket() {
        progress.show();

        Call<Result> call = apiService.submitBasket(
                AppData.getUser(acc).getResult(),
                "25",
                "2076",
                "online");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                progress.dismiss();
                AppUtils.sendLog(acc,
                        apiService,
                        "SubmitBasket",
                        "Action=SubmitBasket&native=1",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                Result movies = response.body();
                finish();
            }

            @Override
            public void onFailure(Call<Result>call, Throwable t) {
                progress.dismiss();
                // Log error here since request failed
                Log.e("TAG", t.toString());
                call.cancel();
            }
        });

    }

    public void getPostTypes() {
        progress.show();

        Call<ItemPostTypes.ItemPostTypesObject> call = apiService.getPostTypes(AppData.getUser(acc).getResult());

        call.enqueue(new Callback<ItemPostTypes.ItemPostTypesObject>() {
            @Override
            public void onResponse(Call<ItemPostTypes.ItemPostTypesObject>call, Response<ItemPostTypes.ItemPostTypesObject> response) {
                progress.dismiss();
                AppUtils.sendLog(acc,
                        apiService,
                        "Items",
                        "null",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                ItemPostTypes.ItemPostTypesObject movies = response.body();

                Spinner sp = (Spinner) findViewById(R.id.spSendMthod);
                sp.setAdapter(new SendMthodAdapter(acc, R.layout.list_item_send_stock_mothod, movies.getItems()));

                Log.d("TAG", "Number of movies received: " + movies.getItems().get(0).getBrief());
            }

            @Override
            public void onFailure(Call<ItemPostTypes.ItemPostTypesObject>call, Throwable t) {
                progress.dismiss();
                // Log error here since request failed
                Log.e("TAG", t.toString());
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
