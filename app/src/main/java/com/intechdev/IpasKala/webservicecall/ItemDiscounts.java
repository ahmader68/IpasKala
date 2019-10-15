package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/08/2018.
 */

public class ItemDiscounts {

    String RowNumber;
    String DiscountId ;
    String MaxUsableCount;
    String Code;
    String ExpireDate;
    String CreateDate;
    String PublishDate;
    String Title;
    String Description;
    String Discount;
    String HadaghalKharid;
    String HadaghalKharidWithLable;

    public ItemDiscounts(String rowNumber, String discountId, String maxUsableCount, String code, String expireDate, String createDate, String publishDate, String title, String description, String discount, String hadaghalKharid, String hadaghalKharidWithLable) {
        RowNumber = rowNumber;
        DiscountId = discountId;
        MaxUsableCount = maxUsableCount;
        Code = code;
        ExpireDate = expireDate;
        CreateDate = createDate;
        PublishDate = publishDate;
        Title = title;
        Description = description;
        Discount = discount;
        HadaghalKharid = hadaghalKharid;
        HadaghalKharidWithLable = hadaghalKharidWithLable;
    }

    public String getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(String rowNumber) {
        RowNumber = rowNumber;
    }

    public String getDiscountId() {
        return DiscountId;
    }

    public void setDiscountId(String discountId) {
        DiscountId = discountId;
    }

    public String getMaxUsableCount() {
        return MaxUsableCount;
    }

    public void setMaxUsableCount(String maxUsableCount) {
        MaxUsableCount = maxUsableCount;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getHadaghalKharid() {
        return HadaghalKharid;
    }

    public void setHadaghalKharid(String hadaghalKharid) {
        HadaghalKharid = hadaghalKharid;
    }

    public String getHadaghalKharidWithLable() {
        return HadaghalKharidWithLable;
    }

    public void setHadaghalKharidWithLable(String hadaghalKharidWithLable) {
        HadaghalKharidWithLable = hadaghalKharidWithLable;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemDiscountsObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemDiscounts> items;

        public ItemDiscountsObject(Settings settings, List<ItemDiscounts> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemDiscounts> getItems() {
            return items;
        }

        public void setItems(List<ItemDiscounts> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemDiscountsResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemDiscounts.ItemDiscountsObject> results;
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

        public List<ItemDiscounts.ItemDiscountsObject> getResults() {
            return results;
        }

        public void setResults(List<ItemDiscounts.ItemDiscountsObject> results) {
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
