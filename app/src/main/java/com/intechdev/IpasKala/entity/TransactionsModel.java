package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 01/08/2018.
 */

public class TransactionsModel {

    public static final int VIEW_SQUARE = 0;
    public static final int VIEW_REQTANGLE = 1;

    String DateSend ;
    String TrackingCode;
    String Price;
    String PayType;
    String PardakhtBabate;
    String PaySerialNo;
    String IdOrder;
    String LinkDetailOrder;

    private int type;
    public int position;

    public TransactionsModel(String dateSend, String trackingCode, String price, String payType, String pardakhtBabate, String paySerialNo, String idOrder, String linkDetailOrder, int type) {
        DateSend = dateSend;
        TrackingCode = trackingCode;
        Price = price;
        PayType = payType;
        PardakhtBabate = pardakhtBabate;
        PaySerialNo = paySerialNo;
        IdOrder = idOrder;
        LinkDetailOrder = linkDetailOrder;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
