package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HBM on 13/06/2018.
 */

public class Settings {

    @SerializedName("itemsCount")
    public int itemsCount;
    @SerializedName("pagesCount")
    public int pagesCount;
    @SerializedName("currPage")
    public int currPage;
    @SerializedName("TedadKhosoosiyat")
    public int tedadKhosoosiyat;
    @SerializedName("finalCost")
    public int finalCost;
    @SerializedName("finalWeight")
    public int finalWeight;

    public Settings(int itemsCount, int pagesCount, int currPage, int tedadKhosoosiyat, int finalCost, int finalWeight) {
        this.itemsCount = itemsCount;
        this.pagesCount = pagesCount;
        this.currPage = currPage;
        this.tedadKhosoosiyat = tedadKhosoosiyat;
        this.finalCost = finalCost;
        this.finalWeight = finalWeight;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTedadKhosoosiyat() {
        return tedadKhosoosiyat;
    }

    public void setTedadKhosoosiyat(int tedadKhosoosiyat) {
        this.tedadKhosoosiyat = tedadKhosoosiyat;
    }

    public int getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(int finalCost) {
        this.finalCost = finalCost;
    }

    public int getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(int finalWeight) {
        this.finalWeight = finalWeight;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }


}
