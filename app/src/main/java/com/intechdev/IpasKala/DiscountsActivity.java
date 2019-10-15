package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.DiscountsAdapter;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.entity.DiscountsModel;
import com.intechdev.IpasKala.entity.TransactionsModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemDiscounts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountsActivity extends AppCompatActivity implements ItemClicked {

    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;

    DiscountsAdapter adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounts);

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

        //daumData();
        getDiscount(AppData.getUser(acc).getResult());
    }

    private void getDiscount(String userId) {
        try {
            progress.show();

            Call<ItemDiscounts.ItemDiscountsObject> cal = apiService.getDiscount(userId);

            cal.enqueue(new Callback<ItemDiscounts.ItemDiscountsObject>() {
                @Override
                public void onResponse(Call<ItemDiscounts.ItemDiscountsObject> call, Response<ItemDiscounts.ItemDiscountsObject> response) {
                    try {
                        loadTransactionsList(response.body());
                    } catch (Exception s) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "GetDiscount",
                                "Action=GetDiscount&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ItemDiscounts.ItemDiscountsObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadTransactionsList(ItemDiscounts.ItemDiscountsObject orders){
        progress.dismiss();
        if(orders.items == null)
            return;

        List<DiscountsModel> list = new ArrayList<>();

        for (ItemDiscounts item: orders.getItems()) {

            list.add(new DiscountsModel(
                    item.getCode(),
                    item.getExpireDate(),
                    item.getCreateDate(),
                    item.getTitle(),
                    item.getDescription(),
                    item.getDiscount(),
                    item.getHadaghalKharid(),
                    TransactionsModel.VIEW_REQTANGLE));
        }
        if(list.size() > 0) {
            adapter = new DiscountsAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void daumData(){
        List<DiscountsModel> list = new ArrayList<>();
        list.add(new DiscountsModel("1", "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "1","2","3","4", BlogListModel.VIEW_REQTANGLE));
        list.add(new DiscountsModel("1", "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "1","2","3","4", BlogListModel.VIEW_REQTANGLE));
        list.add(new DiscountsModel("1", "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "1","2","3","4", BlogListModel.VIEW_REQTANGLE));
        list.add(new DiscountsModel("1", "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "1","2","3","4", BlogListModel.VIEW_REQTANGLE));
        list.add(new DiscountsModel("1", "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "1","2","3","4", BlogListModel.VIEW_REQTANGLE));

        adapter = new DiscountsAdapter(list, this);
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
