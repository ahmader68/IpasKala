package com.intechdev.IpasKala;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.intechdev.IpasKala.Controllers.BlogListAdapter;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.entity.TransactionsModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemBlog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogListActivity extends AppCompatActivity implements ItemClicked {

    SliderLayout mDemoSlider = null;
    MenuSlider ac;

    BlogListAdapter adapter;
    RecyclerView mRecyclerView;

    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_list);

//        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE);
//        AppUtils.setToolbarShoppingButton(this, null, null);
        AppUtils.setBottomToolbarEvent(this);

        acc = this;
        ac = new MenuSlider(this);
        ac.setRightBehindContentView(R.layout.app_right_menu);
        ac.setClicks();

        findViewById(R.id.toolbar).findViewById(R.id.imgToolbarMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.toggleRightDrawer();
            }
        });

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE);
        AppUtils.setToolbarSearchButton(this, null, null);
        AppUtils.setToolbarShoppingButton(this, null, null);

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        progress = AppUtils.showProgressDialog(acc, "");
        dialog = new Dialog(acc);
        dialog.setContentView(R.layout.dialog_user_comment);

        //daumData();
        getListBlog(AppData.getUser(acc) == null ? "-1" : AppData.getUser(acc).getResult());

        //daumData();
    }

    private void getListBlog(String userId) {
        try {
            progress.show();

            Call<ItemBlog.ItemBlogObject> cal = apiService.getListBlog(userId);

            cal.enqueue(new Callback<ItemBlog.ItemBlogObject>() {
                @Override
                public void onResponse(Call<ItemBlog.ItemBlogObject> call, Response<ItemBlog.ItemBlogObject> response) {
                    try {
                        loadTransactionsList(response.body());
                    } catch (Exception s) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "Items",
                                "Action=Items&module=AdvCnt&type=Article&&showimage=0&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ItemBlog.ItemBlogObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadTransactionsList(ItemBlog.ItemBlogObject orders){
        progress.dismiss();
        List<BlogListModel> list = new ArrayList<>();

        for (ItemBlog item: orders.getItems()) {

            list.add(new BlogListModel(
                    Long.parseLong(item.getId()),
                    item.getName(),
                    item.getWriter(),
                    item.getIcon(),
                    TransactionsModel.VIEW_REQTANGLE));
        }

        if(list.size() > 0) {
            adapter = new BlogListAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void daumData(){
        List<BlogListModel> list = new ArrayList<>();
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));
        list.add(new BlogListModel((long) 1, "عنوان بلاگ", "توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات توضیحات", "url", BlogListModel.VIEW_REQTANGLE));


        adapter = new BlogListAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_CLICK_ITEM) {
            Intent arcive4 = new Intent(BlogListActivity.this, BlogPostActivity.class);
            arcive4.putExtra("postId", id);
            startActivity(arcive4);
        }
    }
    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
