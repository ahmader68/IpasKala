package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.OrderListAdapter;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.OrderModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemOrders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOrdersActivity extends AppCompatActivity implements ItemClicked {

    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;

    OrderListAdapter adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_orders);

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

        getListOrders(AppData.getUser(acc).getResult());
        //getListOrders("91");

        //daumData();
    }

    private void getListOrders(String userId) {
        try {
            progress.show();

            Call<ItemOrders.ItemOrdersObject> cal = apiService.getOrders(userId);

            cal.enqueue(new Callback<ItemOrders.ItemOrdersObject>() {
                @Override
                public void onResponse(Call<ItemOrders.ItemOrdersObject> call, Response<ItemOrders.ItemOrdersObject> response) {
                    try {
                        loadOrdersList(response.body());
                    } catch (Exception s) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "GetOrders",
                                "Action=GetOrders&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ItemOrders.ItemOrdersObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadOrdersList(ItemOrders.ItemOrdersObject orders){
        progress.dismiss();
        List<OrderModel> list = new ArrayList<>();

        for (ItemOrders item: orders.getItems()) {

            list.add(new OrderModel(
                    item.getId(),
                    item.getBuyerFullName(),
                    item.getTotalToPay(),
                    item.getPostAmount(),
                    item.getTrackingCode(),
                    item.getCreateDate(),
                    item.getOrderDate(),
                    item.getTotalItemCount(),
                    item.getStatus(),
                    OrderModel.VIEW_REQTANGLE));
        }
        if(list.size() > 0) {
            adapter = new OrderListAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }


    public void daumData(){
        List<OrderModel> list = new ArrayList<>();
        list.add(new OrderModel("1", "عنوان بلاگ", "2000", "5000", "3213453", "13970101", "13970101", "4", "1", OrderModel.VIEW_REQTANGLE));
        list.add(new OrderModel("1", "عنوان بلاگ", "2000", "5000", "3213453", "13970101", "13970101", "4", "1", OrderModel.VIEW_REQTANGLE));
        list.add(new OrderModel("1", "عنوان بلاگ", "2000", "5000", "3213453", "13970101", "13970101", "4", "1", OrderModel.VIEW_REQTANGLE));
        list.add(new OrderModel("1", "عنوان بلاگ", "2000", "5000", "3213453", "13970101", "13970101", "4", "1", OrderModel.VIEW_REQTANGLE));
        list.add(new OrderModel("1", "عنوان بلاگ", "2000", "5000", "3213453", "13970101", "13970101", "4", "1", OrderModel.VIEW_REQTANGLE));
        list.add(new OrderModel("1", "عنوان بلاگ", "2000", "5000", "3213453", "13970101", "13970101", "4", "1", OrderModel.VIEW_REQTANGLE));


        adapter = new OrderListAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_CLICK_ITEM) {
//            Intent arcive4 = new Intent(HistoryOrdersActivity.this, StockDetailsActivity.class);
//            arcive4.putExtra("stockId", id);
//            context.startActivity(arcive4);
        }
    }


    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
