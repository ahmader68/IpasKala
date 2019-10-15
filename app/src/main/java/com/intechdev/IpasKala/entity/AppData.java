package com.intechdev.IpasKala.entity;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.intechdev.IpasKala.webservicecall.InitProg;
import com.intechdev.IpasKala.webservicecall.ItemGroupStock;
import com.intechdev.IpasKala.webservicecall.ItemListStock;
import com.intechdev.IpasKala.webservicecall.Login;

/**
 * Created by HBM on 25/08/2017.
 */

public class AppData {
    public static final String PREF_FILE_NAME = "PrefFile";

    static final String USER = "USER";
    static final String SETTING = "SETTING";
    static final String GROUP_STOCK = "GROUP_STOCK";
    static final String STOCK_NEW = "STOCK_NEW";
    static final String STOCK_BEST_SALE = "STOCK_BEST_SALE";
    static final String STOCK_BEST_VIEW = "STOCK_BEST_VIEW";
    static final String STOCK_SPESIAL = "STOCK_SPESIAL";

    public enum ActionAD{
        USER_SAVE, USER_LOAD,
        SETTING_LOAD,SETTING_SAVE,
        GROUP_STOCK_LOAD,GROUP_STOCK_SAVE,

        STOCK_NEW_LOAD,STOCK_NEW_SAVE,
        STOCK_BEST_SALE_LOAD,STOCK_BEST_SALE_SAVE,
        STOCK_BEST_VIEW_LOAD,STOCK_BEST_VIEW_SAVE,
        STOCK_SPESIAL_LOAD,STOCK_SPESIAL_SAVE,
    }

    public synchronized static void saveData(AppCompatActivity activity, ActionAD actionAD, String jsonData) {
        SharedPreferences.Editor prefsEditor = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).edit();

        if (actionAD.equals(ActionAD.USER_SAVE)) {
            prefsEditor.putString(USER, jsonData);
        } else if (actionAD.equals(ActionAD.SETTING_SAVE)) {
            prefsEditor.putString(SETTING, jsonData);
        } else if (actionAD.equals(ActionAD.GROUP_STOCK_SAVE)) {
            prefsEditor.putString(GROUP_STOCK, jsonData);
        } else if (actionAD.equals(ActionAD.STOCK_NEW_SAVE)) {
            prefsEditor.putString(STOCK_NEW, jsonData);
        } else if (actionAD.equals(ActionAD.STOCK_BEST_SALE_SAVE)) {
            prefsEditor.putString(STOCK_BEST_SALE, jsonData);
        } else if (actionAD.equals(ActionAD.STOCK_BEST_VIEW_SAVE)) {
            prefsEditor.putString(STOCK_BEST_VIEW, jsonData);
        } else if (actionAD.equals(ActionAD.STOCK_SPESIAL_SAVE)) {
            prefsEditor.putString(STOCK_SPESIAL, jsonData);
        }
        prefsEditor.commit();
    }

    public static String getData(AppCompatActivity activity, ActionAD actionAD) {
        String json = "";

        if (actionAD.equals(ActionAD.USER_LOAD)) {
            json = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).getString(USER, "");
        } else if (actionAD.equals(ActionAD.SETTING_LOAD)) {
            json = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).getString(SETTING, "");
        } else if (actionAD.equals(ActionAD.GROUP_STOCK_LOAD)) {
            json = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).getString(GROUP_STOCK, "");
        } else if (actionAD.equals(ActionAD.STOCK_NEW_LOAD)) {
            json = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).getString(STOCK_NEW, "");
        } else if (actionAD.equals(ActionAD.STOCK_BEST_SALE_LOAD)) {
            json = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).getString(STOCK_BEST_SALE, "");
        } else if (actionAD.equals(ActionAD.STOCK_BEST_VIEW_LOAD)) {
            json = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).getString(STOCK_BEST_VIEW, "");
        } else if (actionAD.equals(ActionAD.STOCK_SPESIAL_LOAD)) {
            json = activity.getSharedPreferences(PREF_FILE_NAME, activity.MODE_PRIVATE).getString(STOCK_SPESIAL, "");
        }

        return json;
    }

    public static Login getUser(AppCompatActivity activity) {
        try {
            Gson gson = new Gson();
            String json = getData(activity, ActionAD.USER_LOAD);
            Login user = gson.fromJson(json, Login.class);

            return user;
        }catch (Exception e){
            return null;
        }
    }

    public static InitProg getSetting(AppCompatActivity activity) {
        Gson gson = new Gson();
        String json = getData(activity, ActionAD.SETTING_LOAD);
        InitProg setting  = gson.fromJson(json, InitProg.class);

        return setting;
    }

    public static ItemGroupStock.ItemGroupStockObject getGroupStock(AppCompatActivity activity) {
        Gson gson = new Gson();
        String json = getData(activity, ActionAD.GROUP_STOCK_LOAD);
        ItemGroupStock.ItemGroupStockObject setting  = (ItemGroupStock.ItemGroupStockObject) gson.fromJson(json,  ItemGroupStock.ItemGroupStockObject.class);

        return setting;
    }

    public static ItemGroupStock.ItemGroupStockObject getGroupStock(Context context) {
        Gson gson = new Gson();
        String json = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE).getString(GROUP_STOCK, "");
        ItemGroupStock.ItemGroupStockObject setting  = (ItemGroupStock.ItemGroupStockObject) gson.fromJson(json,  ItemGroupStock.ItemGroupStockObject.class);

        return setting;
    }

    public static ItemListStock.ItemListStockObject getStockNew(AppCompatActivity activity) {
        Gson gson = new Gson();
        String json = getData(activity, ActionAD.STOCK_NEW_LOAD);
        ItemListStock.ItemListStockObject setting  = (ItemListStock.ItemListStockObject) gson.fromJson(json,  ItemListStock.ItemListStockObject.class);

        return setting;
    }

    public static ItemListStock.ItemListStockObject getStockBestSale(AppCompatActivity activity) {
        Gson gson = new Gson();
        String json = getData(activity, ActionAD.STOCK_BEST_SALE_LOAD);
        ItemListStock.ItemListStockObject setting  = (ItemListStock.ItemListStockObject) gson.fromJson(json,  ItemListStock.ItemListStockObject.class);

        return setting;
    }

    public static ItemListStock.ItemListStockObject getStockBestView(AppCompatActivity activity) {
        Gson gson = new Gson();
        String json = getData(activity, ActionAD.STOCK_BEST_VIEW_LOAD);
        ItemListStock.ItemListStockObject setting  = (ItemListStock.ItemListStockObject) gson.fromJson(json,  ItemListStock.ItemListStockObject.class);

        return setting;
    }

    public static ItemListStock.ItemListStockObject getStockSpesial(AppCompatActivity activity) {
        Gson gson = new Gson();
        String json = getData(activity, ActionAD.STOCK_SPESIAL_LOAD);
        ItemListStock.ItemListStockObject setting  = (ItemListStock.ItemListStockObject) gson.fromJson(json,  ItemListStock.ItemListStockObject.class);

        return setting;
    }
}
