package com.intechdev.IpasKala;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.BlogListAdapter;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class DownloadsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);
        //daumData();
    }

    BlogListAdapter adapter;
    RecyclerView mRecyclerView;
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
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
