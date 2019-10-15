package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 15/07/2018.
 */

public class ItemListStock {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public String price;
    @SerializedName("rate")
    public String rate;
    @SerializedName("code")
    public String code;
    @SerializedName("archiveDate")
    public String archiveDate;
    @SerializedName("brand")
    public String brand;
    @SerializedName("BuyPrice")
    public String BuyPrice;
    @SerializedName("CreateDate")
    public String CreateDate;
    @SerializedName("CreatorFullName")
    public String CreatorFullName;
    @SerializedName("Description")
    public String Description;
    @SerializedName("brief")
    public String brief;
    @SerializedName("tag")
    public String tag;
    @SerializedName("Language")
    public String Language;
    @SerializedName("CategoryId")
    public String CategoryId;
    @SerializedName("CategoryName")
    public String CategoryName;
    @SerializedName("SubCategoryId")
    public String SubCategoryId;
    @SerializedName("Code")
    public String Code;
    @SerializedName("Quality")
    public String Quality;
    @SerializedName("Quantity")
    public String Quantity;
    @SerializedName("Discount")
    public String Discount;
    @SerializedName("Brand")
    public String Brand;
    @SerializedName("Model")
    public String Model;
    @SerializedName("Series")
    public String Series;
    @SerializedName("PublishDate")
    public String PublishDate;
    @SerializedName("LastVisitDate")
    public String LastVisitDate;
    @SerializedName("createDateEN")
    public String createDateEN;
    @SerializedName("publishDateEN")
    public String publishDateEN;
    @SerializedName("LastVisitDateEN")
    public String LastVisitDateEN;
    @SerializedName("VisitCount")
    public String VisitCount;
    @SerializedName("IsNew")
    public String IsNew;
    @SerializedName("IsSpecific")
    public String IsSpecific;
    @SerializedName("IsImportant")
    public String IsImportant;
    @SerializedName("status")
    public String status;
    @SerializedName("icon")
    public String icon;
    @SerializedName("InformMe")
    public String InformMe;

    public ItemListStock(String id, String name, String price, String rate, String code, String archiveDate, String brand, String buyPrice, String createDate, String creatorFullName, String description, String brief, String tag, String language, String categoryId, String categoryName, String subCategoryId, String code1, String quality, String quantity, String discount, String brand1, String model, String series, String publishDate, String lastVisitDate, String createDateEN, String publishDateEN, String lastVisitDateEN, String visitCount, String isNew, String isSpecific, String isImportant, String status, String icon, String InformMe) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.code = code;
        this.archiveDate = archiveDate;
        this.brand = brand;
        BuyPrice = buyPrice;
        CreateDate = createDate;
        CreatorFullName = creatorFullName;
        Description = description;
        this.brief = brief;
        this.tag = tag;
        Language = language;
        CategoryId = categoryId;
        CategoryName = categoryName;
        SubCategoryId = subCategoryId;
        Code = code1;
        Quality = quality;
        Quantity = quantity;
        Discount = discount;
        Brand = brand1;
        Model = model;
        Series = series;
        PublishDate = publishDate;
        LastVisitDate = lastVisitDate;
        this.createDateEN = createDateEN;
        this.publishDateEN = publishDateEN;
        LastVisitDateEN = lastVisitDateEN;
        VisitCount = visitCount;
        IsNew = isNew;
        IsSpecific = isSpecific;
        IsImportant = isImportant;
        this.status = status;
        this.icon = icon;
        this.InformMe = InformMe;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(String archiveDate) {
        this.archiveDate = archiveDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getLastVisitDate() {
        return LastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        LastVisitDate = lastVisitDate;
    }

    public String getCreateDateEN() {
        return createDateEN;
    }

    public void setCreateDateEN(String createDateEN) {
        this.createDateEN = createDateEN;
    }

    public String getPublishDateEN() {
        return publishDateEN;
    }

    public void setPublishDateEN(String publishDateEN) {
        this.publishDateEN = publishDateEN;
    }

    public String getLastVisitDateEN() {
        return LastVisitDateEN;
    }

    public void setLastVisitDateEN(String lastVisitDateEN) {
        LastVisitDateEN = lastVisitDateEN;
    }

    public String getVisitCount() {
        return VisitCount;
    }

    public void setVisitCount(String visitCount) {
        VisitCount = visitCount;
    }

    public String getIsNew() {
        return IsNew;
    }

    public void setIsNew(String isNew) {
        IsNew = isNew;
    }

    public String getIsSpecific() {
        return IsSpecific;
    }

    public void setIsSpecific(String isSpecific) {
        IsSpecific = isSpecific;
    }

    public String getIsImportant() {
        return IsImportant;
    }

    public void setIsImportant(String isImportant) {
        IsImportant = isImportant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        BuyPrice = buyPrice;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getCreatorFullName() {
        return CreatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        CreatorFullName = creatorFullName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public String getInformMe() {
        return InformMe;
    }

    public void setInformMe(String informMe) {
        InformMe = informMe;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemListStockObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemListStock> items;

        public ItemListStockObject(Settings settings, List<ItemListStock> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemListStock> getItems() {
            return items;
        }

        public void setItems(List<ItemListStock> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemListStockResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemListStock.ItemListStockObject> results;
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

        public List<ItemListStock.ItemListStockObject> getResults() {
            return results;
        }

        public void setResults(List<ItemListStock.ItemListStockObject> results) {
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
