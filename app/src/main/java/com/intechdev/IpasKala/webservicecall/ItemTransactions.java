package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 01/08/2018.
 */

public class ItemTransactions {

    String RowNumber;
    String DateSend ;
    String TrackingCode;
    String Price;
    String PayType;
    String PardakhtBabate;
    String PaySerialNo;
    String IdOrder;
    String LinkDetailOrder;

    public ItemTransactions(String rowNumber, String dateSend, String trackingCode, String price, String payType, String pardakhtBabate, String paySerialNo, String idOrder, String linkDetailOrder) {
        RowNumber = rowNumber;
        DateSend = dateSend;
        TrackingCode = trackingCode;
        Price = price;
        PayType = payType;
        PardakhtBabate = pardakhtBabate;
        PaySerialNo = paySerialNo;
        IdOrder = idOrder;
        LinkDetailOrder = linkDetailOrder;
    }


    public String getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(String rowNumber) {
        RowNumber = rowNumber;
    }

    public String getDateSend() {
        return DateSend;
    }

    public void setDateSend(String dateSend) {
        DateSend = dateSend;
    }

    public String getTrackingCode() {
        return TrackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        TrackingCode = trackingCode;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getPardakhtBabate() {
        return PardakhtBabate;
    }

    public void setPardakhtBabate(String pardakhtBabate) {
        PardakhtBabate = pardakhtBabate;
    }

    public String getPaySerialNo() {
        return PaySerialNo;
    }

    public void setPaySerialNo(String paySerialNo) {
        PaySerialNo = paySerialNo;
    }

    public String getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(String idOrder) {
        IdOrder = idOrder;
    }

    public String getLinkDetailOrder() {
        return LinkDetailOrder;
    }

    public void setLinkDetailOrder(String linkDetailOrder) {
        LinkDetailOrder = linkDetailOrder;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemTransactionsObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemTransactions> items;

        public ItemTransactionsObject(Settings settings, List<ItemTransactions> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemTransactions> getItems() {
            return items;
        }

        public void setItems(List<ItemTransactions> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemTransactionsResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemTransactionsObject> results;
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

        public List<ItemTransactionsObject> getResults() {
            return results;
        }

        public void setResults(List<ItemTransactionsObject> results) {
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
