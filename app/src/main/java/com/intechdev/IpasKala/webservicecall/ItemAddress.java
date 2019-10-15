package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 20/07/2018.
 */

public class ItemAddress {
    @SerializedName("id")
    public String id;
    @SerializedName("provinceId")
    public String provinceId;
    @SerializedName("title")
    public String title;
    @SerializedName("address")
    public String address;
    @SerializedName("postalCode")
    public String postalCode;
    @SerializedName("provinceTitle")
    public String provinceTitle;
    @SerializedName("city")
    public String city;
    @SerializedName("tel")
    public String tel;

    public ItemAddress(String id, String provinceId, String title, String address, String postalCode, String provinceTitle, String city, String tel) {
        this.id = id;
        this.provinceId = provinceId;
        this.title = title;
        this.address = address;
        this.postalCode = postalCode;
        this.provinceTitle = provinceTitle;
        this.city = city;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvinceTitle() {
        return provinceTitle;
    }

    public void setProvinceTitle(String provinceTitle) {
        this.provinceTitle = provinceTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }


    public class ItemAddressObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemAddress> items;

        public ItemAddressObject(Settings settings, List<ItemAddress> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemAddress> getItems() {
            return items;
        }

        public void setItems(List<ItemAddress> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemAddressResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemAddress.ItemAddressObject> results;
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

        public List<ItemAddress.ItemAddressObject> getResults() {
            return results;
        }

        public void setResults(List<ItemAddress.ItemAddressObject> results) {
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
