package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 01/08/2018.
 */

public class OrderModel {
    public static final int VIEW_SQUARE = 0;
    public static final int VIEW_REQTANGLE = 1;


    public String id;
    public String BuyerFullName;
    public String TotalToPay;
    public String PostAmount;
    public String TrackingCode;
    public String CreateDate;
    public String OrderDate;
    public String TotalItemCount;
    public String Status;


    private int type;
    public int position;

    public OrderModel(String id, String buyerFullName, String totalToPay, String postAmount, String trackingCode, String createDate, String orderDate, String totalItemCount, String status, int type) {
        this.id = id;
        BuyerFullName = buyerFullName;
        TotalToPay = totalToPay;
        PostAmount = postAmount;
        TrackingCode = trackingCode;
        CreateDate = createDate;
        OrderDate = orderDate;
        TotalItemCount = totalItemCount;
        Status = status;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerFullName() {
        return BuyerFullName;
    }

    public void setBuyerFullName(String buyerFullName) {
        BuyerFullName = buyerFullName;
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

    public String getTrackingCode() {
        return TrackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        TrackingCode = trackingCode;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getTotalItemCount() {
        return TotalItemCount;
    }

    public void setTotalItemCount(String totalItemCount) {
        TotalItemCount = totalItemCount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
