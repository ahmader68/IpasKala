package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.TransactionsAdapter;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.TransactionsModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemTransactions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsActivity extends AppCompatActivity implements ItemClicked {

    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;

    TransactionsAdapter adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);

        acc = this;

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        progress = AppUtils.showProgressDialog(acc, "");
        dialog = new Dialog(acc);
        dialog.setContentView(R.layout.dialog_user_comment);

        getListTransactions(AppData.getUser(acc).getResult());
        //getListTransactions("1");

        //daumData();
    }

    private void getListTransactions(String userId) {
        try {
            progress.show();

            Call<ItemTransactions.ItemTransactionsObject> cal = apiService.getTransactions(userId);

            cal.enqueue(new Callback<ItemTransactions.ItemTransactionsObject>() {
                @Override
                public void onResponse(Call<ItemTransactions.ItemTransactionsObject> call, Response<ItemTransactions.ItemTransactionsObject> response) {
                    try {
                        loadTransactionsList(response.body());
                    } catch (Exception s) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "GetMyTransaction",
                                "Action=GetMyTransaction&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ItemTransactions.ItemTransactionsObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadTransactionsList(ItemTransactions.ItemTransactionsObject orders){
        progress.dismiss();
        List<TransactionsModel> list = new ArrayList<>();

        for (ItemTransactions item: orders.getItems()) {

            list.add(new TransactionsModel(
                    item.getDateSend(),
                    item.getTrackingCode(),
                    item.getPrice(),
                    item.getPayType(),
                    item.getPardakhtBabate(),
                    item.getPaySerialNo(),
                    item.getIdOrder(),
                    item.getLinkDetailOrder(),
                    TransactionsModel.VIEW_REQTANGLE));
        }
        if(list.size() > 0) {
            adapter = new TransactionsAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_CLICK_ITEM) {
//            Intent arcive4 = new Intent(HistoryOrdersActivity.this, StockDetailsActivity.class);
//            arcive4.putExtra("stockId", id);
//            context.startActivity(arcive4);
        }
    }


    public void daumData(){
        List<TransactionsModel> list = new ArrayList<>();
        list.add(new TransactionsModel("1", "عنوان بلاگ", "123", "123", "23","asd","ad", "as",TransactionsModel.VIEW_REQTANGLE));
        list.add(new TransactionsModel("1", "عنوان بلاگ", "123", "123", "23","asd","ad", "as",TransactionsModel.VIEW_REQTANGLE));
        list.add(new TransactionsModel("1", "عنوان بلاگ", "123", "123", "23","asd","ad", "as",TransactionsModel.VIEW_REQTANGLE));
        list.add(new TransactionsModel("1", "عنوان بلاگ", "123", "123", "23","asd","ad", "as",TransactionsModel.VIEW_REQTANGLE));
        list.add(new TransactionsModel("1", "عنوان بلاگ", "123", "123", "23","asd","ad", "as",TransactionsModel.VIEW_REQTANGLE));


        adapter = new TransactionsAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
