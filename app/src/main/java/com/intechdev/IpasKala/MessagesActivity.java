package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.UserCommentsAdapter;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.UserCommentsModel;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemComments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {
    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;

    UserCommentsAdapter adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

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

        getListComments();


        //daumData();
    }

    public void getListComments(){
        try {
            progress.show();

            Call<ItemComments.ItemCommentsObject> cal = apiService.getListComment(AppData.getUser(acc).getResult());

            cal.enqueue(new Callback<ItemComments.ItemCommentsObject>() {
                @Override
                public void onResponse(Call<ItemComments.ItemCommentsObject> call, Response<ItemComments.ItemCommentsObject> response) {
                    try {
                        loadCommentList(response.body());
                        progress.dismiss();
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
                    UserCommentsModel.PROFILE_ITEM));
        }
        if(list.size() > 0) {
            adapter = new UserCommentsAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void daumData(){
        List<UserCommentsModel> list = new ArrayList<>();
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));
        list.add(new UserCommentsModel("هاشم بروشکی", "15 اسفند 1396", "2", "4", "عنوان", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", UserCommentsModel.DETILE_ITEM));

        UserCommentsAdapter adapter = new UserCommentsAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
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
