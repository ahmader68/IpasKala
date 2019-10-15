package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 11/08/2018.
 */

public class ItemPostTypes {
    @SerializedName("Id")
    String Id;
    @SerializedName("Price")
    String Price;
    @SerializedName("weight")
    String weight;
    @SerializedName("Name")
    String Name;
    @SerializedName("Brief")
    String Brief;
    @SerializedName("Child_Post")
    List<ChildPost> childPost;

    public ItemPostTypes(String id, String price, String weight, String name, String brief, List<ChildPost> childPost) {
        Id = id;
        Price = price;
        this.weight = weight;
        Name = name;
        Brief = brief;
        this.childPost = childPost;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrief() {
        return Brief;
    }

    public void setBrief(String brief) {
        Brief = brief;
    }

    public List<ChildPost> getChildPost() {
        return childPost;
    }

    public void setChildPost(List<ChildPost> childPost) {
        this.childPost = childPost;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ChildPost{
        @SerializedName("Child_ParentId")
        String childParentId;
        @SerializedName("Child_Id")
        String childId;
        @SerializedName("Child_CategoryName")
        String childCategoryName;
        @SerializedName("Child_Brief")
        String childBrief;
        @SerializedName("Child_LinkModuleId")
        String childLinkModuleId;

        public ChildPost(String childParentId, String childId, String childCategoryName, String childBrief, String childLinkModuleId) {
            this.childParentId = childParentId;
            this.childId = childId;
            this.childCategoryName = childCategoryName;
            this.childBrief = childBrief;
            this.childLinkModuleId = childLinkModuleId;
        }

        public String getChildParentId() {
            return childParentId;
        }

        public void setChildParentId(String childParentId) {
            this.childParentId = childParentId;
        }

        public String getChildId() {
            return childId;
        }

        public void setChildId(String childId) {
            this.childId = childId;
        }

        public String getChildCategoryName() {
            return childCategoryName;
        }

        public void setChildCategoryName(String childCategoryName) {
            this.childCategoryName = childCategoryName;
        }

        public String getChildBrief() {
            return childBrief;
        }

        public void setChildBrief(String childBrief) {
            this.childBrief = childBrief;
        }

        public String getChildLinkModuleId() {
            return childLinkModuleId;
        }

        public void setChildLinkModuleId(String childLinkModuleId) {
            this.childLinkModuleId = childLinkModuleId;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemPostTypesObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemPostTypes> items;

        public ItemPostTypesObject(Settings settings, List<ItemPostTypes> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemPostTypes> getItems() {
            return items;
        }

        public void setItems(List<ItemPostTypes> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemPostTypesResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemPostTypes.ItemPostTypesObject> results;
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

        public List<ItemPostTypes.ItemPostTypesObject> getResults() {
            return results;
        }

        public void setResults(List<ItemPostTypes.ItemPostTypesObject> results) {
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
