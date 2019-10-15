package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 01/08/2018.
 */

public class ItemFavorites {

    String FavId;
    String ProductId;
    String LinkProduct;
    String Price;
    String ProductName;
    String Status;
    String TypeOfFav;

    public ItemFavorites(String favId, String productId, String linkProduct, String price, String productName, String status, String typeOfFav) {
        FavId = favId;
        ProductId = productId;
        LinkProduct = linkProduct;
        Price = price;
        ProductName = productName;
        Status = status;
        TypeOfFav = typeOfFav;
    }

    public String getFavId() {
        return FavId;
    }

    public void setFavId(String favId) {
        FavId = favId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getLinkProduct() {
        return LinkProduct;
    }

    public void setLinkProduct(String linkProduct) {
        LinkProduct = linkProduct;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTypeOfFav() {
        return TypeOfFav;
    }

    public void setTypeOfFav(String typeOfFav) {
        TypeOfFav = typeOfFav;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemFavoritesObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemFavorites> items;

        public ItemFavoritesObject(Settings settings, List<ItemFavorites> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemFavorites> getItems() {
            return items;
        }

        public void setItems(List<ItemFavorites> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemFavoritesResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemFavorites.ItemFavoritesObject> results;
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

        public List<ItemFavorites.ItemFavoritesObject> getResults() {
            return results;
        }

        public void setResults(List<ItemFavorites.ItemFavoritesObject> results) {
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
