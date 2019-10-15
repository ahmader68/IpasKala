package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 13/06/2018.
 */

public class ItemImageSlider {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("order")
    public String order;
    @SerializedName("url")
    public String url;
    @SerializedName("icon")
    public String icon;
    @SerializedName("createDate")
    public String createDate;
    @SerializedName("catId")
    public String catId;
    @SerializedName("catName")
    public String catName;
    @SerializedName("link")
    public String link;
    @SerializedName("brief")
    public String brief;
    @SerializedName("visitCount")
    public String visitCount;


    public ItemImageSlider(String id, String name, String order, String url, String icon, String createDate, String catId, String catName, String link, String brief, String visitCount) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.url = url;
        this.icon = icon;
        this.createDate = createDate;
        this.catId = catId;
        this.catName = catName;
        this.link = link;
        this.brief = brief;
        this.visitCount = visitCount;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }


    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemImageSliderObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemImageSlider> items;

        public ItemImageSliderObject(Settings settings, List<ItemImageSlider> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemImageSlider> getItems() {
            return items;
        }

        public void setItems(List<ItemImageSlider> items) {
            this.items = items;
        }
    }

    public class ItemImageSliderResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemImageSliderObject> results;
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

        public List<ItemImageSliderObject> getResults() {
            return results;
        }

        public void setResults(List<ItemImageSliderObject> results) {
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
