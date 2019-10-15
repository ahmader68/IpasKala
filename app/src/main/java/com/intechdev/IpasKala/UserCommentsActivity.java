package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.intechdev.IpasKala.Controllers.UserCommentsAdapter;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.StockPeropertiesModel;
import com.intechdev.IpasKala.entity.UserCommentsModel;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemComments;
import com.intechdev.IpasKala.webservicecall.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCommentsActivity extends AppCompatActivityAppWork {

    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;

    UserCommentsAdapter adapter;
    RecyclerView mRecyclerView;
    String stockId= "2";
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_comments);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);
        AppUtils.setBottomToolbarEvent(this);

        acc = this;

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

//        List<UserCommentsModel> list = new ArrayList<>();
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", StockPeropertiesModel.DETILE_ITEM));
//
//
//
//
//        UserCommentsAdapter adapter = new UserCommentsAdapter(list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(adapter);

        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.fb);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progress = AppUtils.showProgressDialog(acc, "");
        dialog = new Dialog(acc);
        dialog.setContentView(R.layout.dialog_user_comment);


        if(getIntent().getExtras().get("stockId") != null){
            stockId = getIntent().getExtras().getString("stockId");
        }

        getListComments(stockId);

        dialog.findViewById(R.id.btnConfirmComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                addComment(stockId);
            }
        });

        findViewById(R.id.btnBestComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });
    }

    public void getListComments(String stockId){
        try {
            progress.show();

            Call<ItemComments.ItemCommentsObject> cal = apiService.getListComment(AppData.getUser(acc).getResult(), stockId);

            cal.enqueue(new Callback<ItemComments.ItemCommentsObject>() {
                @Override
                public void onResponse(Call<ItemComments.ItemCommentsObject> call, Response<ItemComments.ItemCommentsObject> response) {
                    try {
                        loadCommentList(response.body());
                    } catch (Exception s) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "GetComments",
                                "Action=GetComments&withContent=true&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ItemComments.ItemCommentsObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadCommentList(ItemComments.ItemCommentsObject address){
        progress.dismiss();
        List<UserCommentsModel> list = new ArrayList<>();

        for (ItemComments item: address.getItems()) {
            list.add(new UserCommentsModel(item.getSenderName(),
                                            item.getSendDate(),
                                            "0",
                                            "0",
                                            item.getSubject(),
                                            item.getContent(),
                                            StockPeropertiesModel.DETILE_ITEM));
        }
        if(list.size() > 0) {
            adapter = new UserCommentsAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void addComment(final String stockId){

        progress.show();

        Call<Result> call = apiService.addComment(AppData.getUser(acc).getResult(),
                stockId,
                ((EditText) dialog.findViewById(R.id.etComment)).getText().toString(),
                "",
                ((EditText) dialog.findViewById(R.id.etTitle)).getText().toString(),
                "",
                "");


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                AppUtils.sendLog(acc,
                        apiService,
                        "SaveComment",
                        "Action=SaveComment&native=1",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                Result movies = response.body();
                progress.dismiss();
                getListComments(stockId);
                Log.d("TAG", "Number of movies received: " + movies.getResult());
            }

            @Override
            public void onFailure(Call<Result>call, Throwable t) {
                progress.dismiss();
                getListComments(stockId);
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
