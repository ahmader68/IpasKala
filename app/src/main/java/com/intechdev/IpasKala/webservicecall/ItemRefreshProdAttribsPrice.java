package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/06/2018.
 */

public class ItemRefreshProdAttribsPrice {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public String price;
    @SerializedName("Quantity")
    public String Quantity;

    public ItemRefreshProdAttribsPrice(String id, String name, String price, String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        Quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemRefreshProdAttribsPriceObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemRefreshProdAttribsPrice> items;

        public ItemRefreshProdAttribsPriceObject(Settings settings, List<ItemRefreshProdAttribsPrice> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemRefreshProdAttribsPrice> getItems() {
            return items;
        }

        public void setItems(List<ItemRefreshProdAttribsPrice> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemRefreshProdAttribsPriceResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemRefreshProdAttribsPriceObject> results;
        @SerializedName("total_results")
        public  int totalResults;
        @SerializedName("total_pages")
        public  int totalPages;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ItemRefreshProdAttribsPriceObject> getResults() {
            return results;
        }

        public void setResults(List<ItemRefreshProdAttribsPriceObject> results) {
            this.results = results;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

}
