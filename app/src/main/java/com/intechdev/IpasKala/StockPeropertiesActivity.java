package com.intechdev.IpasKala;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.intechdev.IpasKala.Controllers.StockPeropertiesAdapter;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.entity.StockPeropertiesModel;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.ItemStockDetails;

import java.util.ArrayList;
import java.util.List;

public class StockPeropertiesActivity extends AppCompatActivityAppWork {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_peroperties);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);
        AppUtils.setBottomToolbarEvent(this);

//http://alexzh.com/tutorials/android-testing-unit-testing/
        List<StockPeropertiesModel> list = new ArrayList<>();
//        list.add(new StockPeropertiesModel("صفحه نمایش", null, StockPeropertiesModel.HEARDER_GROUP));
//        list.add(new StockPeropertiesModel("سایزه صفحه", "50 اینچ", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("تکنولوژی صفحه", "LED", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نوع صفحه", "تخت", StockPeropertiesModel.DETILE_ITEM));
//
//        list.add(new StockPeropertiesModel("تصویر", null, StockPeropertiesModel.HEARDER_GROUP));
//        list.add(new StockPeropertiesModel("کیفیت تصویر", "Ultera HD 4k", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("رزولوشن", "3840 x 2160", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("HDR", "true", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نسبت تصویر", "16.9", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("قابلیت ارتقاء کیفیت تصویر", "true", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("3D", "false", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نوع نمایش تصویر 3D", "ندارد", StockPeropertiesModel.DETILE_ITEM));
//
//        list.add(new StockPeropertiesModel("صفحه نمایش", null, StockPeropertiesModel.HEARDER_GROUP));
//        list.add(new StockPeropertiesModel("سایزه صفحه", "50 اینچ", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("تکنولوژی صفحه", "LED", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نوع صفحه", "تخت", StockPeropertiesModel.DETILE_ITEM));
//
//        list.add(new StockPeropertiesModel("تصویر", null, StockPeropertiesModel.HEARDER_GROUP));
//        list.add(new StockPeropertiesModel("کیفیت تصویر", "Ultera HD 4k", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("رزولوشن", "3840 x 2160", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("HDR", "true", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نسبت تصویر", "16.9", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("قابلیت ارتقاء کیفیت تصویر", "true", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("3D", "false", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نوع نمایش تصویر 3D", "ندارد", StockPeropertiesModel.DETILE_ITEM));
//
//        list.add(new StockPeropertiesModel("صفحه نمایش", null, StockPeropertiesModel.HEARDER_GROUP));
//        list.add(new StockPeropertiesModel("سایزه صفحه", "50 اینچ", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("تکنولوژی صفحه", "LED", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نوع صفحه", "تخت", StockPeropertiesModel.DETILE_ITEM));
//
//        list.add(new StockPeropertiesModel("تصویر", null, StockPeropertiesModel.HEARDER_GROUP));
//        list.add(new StockPeropertiesModel("کیفیت تصویر", "Ultera HD 4k", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("رزولوشن", "3840 x 2160", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("HDR", "true", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نسبت تصویر", "16.9", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("قابلیت ارتقاء کیفیت تصویر", "true", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("3D", "false", StockPeropertiesModel.DETILE_ITEM));
//        list.add(new StockPeropertiesModel("نوع نمایش تصویر 3D", "ندارد", StockPeropertiesModel.DETILE_ITEM));


        StockPeropertiesAdapter adapter = new StockPeropertiesAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        list.add(new StockPeropertiesModel("مشخصات کالا", null, StockPeropertiesModel.HEARDER_GROUP));

        if(getIntent().getExtras().get("stockJson") != null){

            ItemStockDetails itemStockDetails = new Gson().fromJson(getIntent().getExtras().get("stockJson").toString(), ItemStockDetails.class);

            ((TextView)findViewById(R.id.txtTitle)).setText(itemStockDetails.getName());
            for (ItemStockDetails.ItemStockProperies item : itemStockDetails.getAttributes()){
                list.add(new StockPeropertiesModel(item.getTitle(), item.getValue(), StockPeropertiesModel.DETILE_ITEM));
            }
        }

    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}
