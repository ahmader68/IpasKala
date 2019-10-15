package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 06/08/2018.
 */

public class ItemProdAttribs {

    String AttrPrice;
    String Assigned_parent_CatName;
    String Assigned_parent_CatId;
    String AttrDiscount;
    String ProductId;
    String AttrCount;
    String Id_Attribute;
    String topAttr_Language;
    String topAttr_showType;
    String Meghdar_Attribute;
    String ColorCode;

    public ItemProdAttribs(String attrPrice, String assigned_parent_CatName, String assigned_parent_CatId, String attrDiscount, String productId, String attrCount, String id_Attribute, String topAttr_Language, String topAttr_showType, String meghdar_Attribute, String ColorCode) {
        AttrPrice = attrPrice;
        Assigned_parent_CatName = assigned_parent_CatName;
        Assigned_parent_CatId = assigned_parent_CatId;
        AttrDiscount = attrDiscount;
        ProductId = productId;
        AttrCount = attrCount;
        Id_Attribute = id_Attribute;
        this.topAttr_Language = topAttr_Language;
        this.topAttr_showType = topAttr_showType;
        Meghdar_Attribute = meghdar_Attribute;
        this.ColorCode = ColorCode;
    }

    public String getAttrPrice() {
        return AttrPrice;
    }

    public void setAttrPrice(String attrPrice) {
        AttrPrice = attrPrice;
    }

    public String getAssigned_parent_CatName() {
        return Assigned_parent_CatName;
    }

    public void setAssigned_parent_CatName(String assigned_parent_CatName) {
        Assigned_parent_CatName = assigned_parent_CatName;
    }

    public String getAssigned_parent_CatId() {
        return Assigned_parent_CatId;
    }

    public void setAssigned_parent_CatId(String assigned_parent_CatId) {
        Assigned_parent_CatId = assigned_parent_CatId;
    }

    public String getAttrDiscount() {
        return AttrDiscount;
    }

    public void setAttrDiscount(String attrDiscount) {
        AttrDiscount = attrDiscount;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getAttrCount() {
        return AttrCount;
    }

    public void setAttrCount(String attrCount) {
        AttrCount = attrCount;
    }

    public String getId_Attribute() {
        return Id_Attribute;
    }

    public void setId_Attribute(String id_Attribute) {
        Id_Attribute = id_Attribute;
    }

    public String getTopAttr_Language() {
        return topAttr_Language;
    }

    public void setTopAttr_Language(String topAttr_Language) {
        this.topAttr_Language = topAttr_Language;
    }

    public String getTopAttr_showType() {
        return topAttr_showType;
    }

    public void setTopAttr_showType(String topAttr_showType) {
        this.topAttr_showType = topAttr_showType;
    }

    public String getMeghdar_Attribute() {
        return Meghdar_Attribute;
    }

    public void setMeghdar_Attribute(String meghdar_Attribute) {
        Meghdar_Attribute = meghdar_Attribute;
    }

    public String getColorCode() {
        return ColorCode;
    }

    public void setColorCode(String colorCode) {
        this.ColorCode = colorCode;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemProdAttribsObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemProdAttribs> items;

        public ItemProdAttribsObject(Settings settings, List<ItemProdAttribs> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemProdAttribs> getItems() {
            return items;
        }

        public void setItems(List<ItemProdAttribs> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemProdAttribsResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemProdAttribs.ItemProdAttribsResponse> results;
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

        public List<ItemProdAttribs.ItemProdAttribsResponse> getResults() {
            return results;
        }

        public void setResults(List<ItemProdAttribs.ItemProdAttribsResponse> results) {
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
