package com.intechdev.IpasKala;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.FavoritesAdapter;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.FavoriteModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemFavorites;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.intechdev.IpasKala.utils.AppUtils.context;

public class FavoritesActivity extends AppCompatActivity implements ItemClicked{

    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;

    FavoritesAdapter adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

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

        getListFavorites(AppData.getUser(acc).getResult());
        //daumData();
    }

    private void getListFavorites(String userId) {
        try {
            progress.show();

            Call<ItemFavorites.ItemFavoritesObject> cal = apiService.getFavorites(userId);

            cal.enqueue(new Callback<ItemFavorites.ItemFavoritesObject>() {
                @Override
                public void onResponse(Call<ItemFavorites.ItemFavoritesObject> call, Response<ItemFavorites.ItemFavoritesObject> response) {
                    try {
                        loadFavoritesList(response.body());
                    } catch (Exception s) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "GetMyFav",
                                "Action=GetMyFav&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ItemFavorites.ItemFavoritesObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadFavoritesList(ItemFavorites.ItemFavoritesObject favorites){
        progress.dismiss();
        List<FavoriteModel> list = new ArrayList<>();

        for (ItemFavorites item: favorites.getItems()) {
            list.add(new FavoriteModel(Long.parseLong(item.getFavId()),
                    item.getProductId(),
                    item.getPrice(),
                    item.getProductName(),
                    FavoriteModel.VIEW_REQTANGLE));
        }
        if(list.size() > 0) {
            adapter = new FavoritesAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void daumData(){
        List<FavoriteModel> list = new ArrayList<>();
        list.add(new FavoriteModel((long) 1, "1", "5000", "تست", FavoriteModel.VIEW_REQTANGLE));


        adapter = new FavoritesAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_CLICK_ITEM) {
            Intent arcive4 = new Intent(FavoritesActivity.this, StockDetailsActivity.class);
            arcive4.putExtra("stockId", id);
            context.startActivity(arcive4);
        }
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
