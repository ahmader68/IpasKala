package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 01/08/2018.
 */

public class ItemOrders {
    String id;
    String BuyerId ;
    String BuyerMobile;
    String BuyerFullName;
    String BasketType;
    String PayType;
    String TotalToPay;
    String PostAmount;
    String PostType;
    String TotalAmount;
    String TotalItemCount;
    String TrackingCode;
    String ConfirmDate;
    String CreateDate;
    String DeliverDate;
    String ExpireDate;
    String OrderDate;
    String ReadyDate;
    String Status;
    String Unread;

    public ItemOrders(String id, String buyerId, String buyerMobile, String buyerFullName, String basketType, String payType, String totalToPay, String postAmount, String postType, String totalAmount, String totalItemCount, String trackingCode, String confirmDate, String createDate, String deliverDate, String expireDate, String orderDate, String readyDate, String status, String unread) {
        this.id = id;
        BuyerId = buyerId;
        BuyerMobile = buyerMobile;
        BuyerFullName = buyerFullName;
        BasketType = basketType;
        PayType = payType;
        TotalToPay = totalToPay;
        PostAmount = postAmount;
        PostType = postType;
        TotalAmount = totalAmount;
        TotalItemCount = totalItemCount;
        TrackingCode = trackingCode;
        ConfirmDate = confirmDate;
        CreateDate = createDate;
        DeliverDate = deliverDate;
        ExpireDate = expireDate;
        OrderDate = orderDate;
        ReadyDate = readyDate;
        Status = status;
        Unread = unread;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return BuyerId;
    }

    public void setBuyerId(String buyerId) {
        BuyerId = buyerId;
    }

    public String getBuyerMobile() {
        return BuyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        BuyerMobile = buyerMobile;
    }

    public String getBuyerFullName() {
        return BuyerFullName;
    }

    public void setBuyerFullName(String buyerFullName) {
        BuyerFullName = buyerFullName;
    }

    public String getBasketType() {
        return BasketType;
    }

    public void setBasketType(String basketType) {
        BasketType = basketType;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getTotalToPay() {
        return TotalToPay;
    }

    public void setTotalToPay(String totalToPay) {
        TotalToPay = totalToPay;
    }

    public String getPostAmount() {
        return PostAmount;
    }

    public void setPostAmount(String postAmount) {
        PostAmount = postAmount;
    }

    public String getPostType() {
        return PostType;
    }

    public void setPostType(String postType) {
        PostType = postType;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getTotalItemCount() {
        return TotalItemCount;
    }

    public void setTotalItemCount(String totalItemCount) {
        TotalItemCount = totalItemCount;
    }

    public String getTrackingCode() {
        return TrackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        TrackingCode = trackingCode;
    }

    public String getConfirmDate() {
        return ConfirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        ConfirmDate = confirmDate;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getDeliverDate() {
        return DeliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        DeliverDate = deliverDate;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getReadyDate() {
        return ReadyDate;
    }

    public void setReadyDate(String readyDate) {
        ReadyDate = readyDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemOrdersObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemOrders> items;

        public ItemOrdersObject(Settings settings, List<ItemOrders> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemOrders> getItems() {
            return items;
        }

        public void setItems(List<ItemOrders> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemOrdersResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemOrders.ItemOrdersObject> results;
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

        public List<ItemOrders.ItemOrdersObject> getResults() {
            return results;
        }

        public void setResults(List<ItemOrders.ItemOrdersObject> results) {
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
