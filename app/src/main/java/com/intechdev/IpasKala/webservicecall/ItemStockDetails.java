package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/06/2018.
 */

public class ItemStockDetails {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public String price;
    @SerializedName("rate")
    public String rate;
    @SerializedName("ver")
    public String ver;
    @SerializedName("icon")
    public String icon;
    @SerializedName("createDate")
    public String createDate;
    @SerializedName("catId")
    public String catId;
    @SerializedName("scatId")
    public String scatId;
    @SerializedName("catName")
    public String catName;
    @SerializedName("scatName")
    public String scatName;
    @SerializedName("cmdCount")
    public String cmdCount;
    @SerializedName("Quantity")
    public String quantity;
    @SerializedName("visitCount")
    public String visitCount;
    @SerializedName("dnlCount")
    public String dnlCount;
    @SerializedName("tags")
    public String tags;
    @SerializedName("brief")
    public String brief;
    @SerializedName("body")
    public String body;
    @SerializedName("nav")
    public String nav;
    @SerializedName("AttribArray")
    public List<ItemStockProperies> Attributes;
    @SerializedName("album")
    public List<ItemStockAlbum> album;


    public ItemStockDetails(String id, String name, String price, String rate, String ver, String icon, String createDate, String catId, String scatId, String catName, String scatName, String cmdCount, String quantity, String visitCount, String dnlCount, String tags, String brief, String body, String nav, List<ItemStockProperies> attributes, List<ItemStockAlbum> album) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.ver = ver;
        this.icon = icon;
        this.createDate = createDate;
        this.catId = catId;
        this.scatId = scatId;
        this.catName = catName;
        this.scatName = scatName;
        this.cmdCount = cmdCount;
        this.quantity = quantity;
        this.visitCount = visitCount;
        this.dnlCount = dnlCount;
        this.tags = tags;
        this.brief = brief;
        this.body = body;
        this.nav = nav;
        Attributes = attributes;
        this.album = album;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
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

    public String getScatId() {
        return scatId;
    }

    public void setScatId(String scatId) {
        this.scatId = scatId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getScatName() {
        return scatName;
    }

    public void setScatName(String scatName) {
        this.scatName = scatName;
    }

    public String getCmdCount() {
        return cmdCount;
    }

    public void setCmdCount(String cmdCount) {
        this.cmdCount = cmdCount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }

    public String getDnlCount() {
        return dnlCount;
    }

    public void setDnlCount(String dnlCount) {
        this.dnlCount = dnlCount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public List<ItemStockProperies> getAttributes() {
        return Attributes;
    }

    public void setAttributes(List<ItemStockProperies> attributes) {
        Attributes = attributes;
    }

    public List<ItemStockAlbum> getAlbum() {
        return album;
    }

    public void setAlbum(List<ItemStockAlbum> album) {
        this.album = album;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemStockAlbum{
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("brief")
        public String brief;
        @SerializedName("icon")
        public String icon;
        @SerializedName("mainImage")
        public String mainImage;

        public ItemStockAlbum(String id, String name, String brief, String icon, String mainImage) {
            this.id = id;
            this.name = name;
            this.brief = brief;
            this.icon = icon;
            this.mainImage = mainImage;
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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }
    }

    public class ItemStockProperies{
        @SerializedName("title")
        public String title;
        @SerializedName("value")
        public String value;

        public ItemStockProperies(String title, String value) {
            this.title = title;
            this.value = value;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class ItemStockDetailsObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemStockDetails> items;

        public ItemStockDetailsObject(Settings settings, List<ItemStockDetails> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemStockDetails> getItems() {
            return items;
        }

        public void setItems(List<ItemStockDetails> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemStockDetailsResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemStockDetailsObject> results;
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

        public List<ItemStockDetailsObject> getResults() {
            return results;
        }

        public void setResults(List<ItemStockDetailsObject> results) {
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
