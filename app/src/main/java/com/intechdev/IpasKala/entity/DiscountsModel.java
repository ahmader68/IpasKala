package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 01/08/2018.
 */

public class DiscountsModel {

    public static final int VIEW_SQUARE = 0;
    public static final int VIEW_REQTANGLE = 1;

    String Code ;
    String ExpireDate;
    String CreateDate;
    String Title;
    String Description;
    String Discount;
    String HadaghalKharid;

    private int type;
    public int position;

    public DiscountsModel(String code, String expireDate, String createDate, String title, String description, String discount, String hadaghalKharid, int type) {
        Code = code;
        ExpireDate = expireDate;
        CreateDate = createDate;
        Title = title;
        Description = description;
        Discount = discount;
        HadaghalKharid = hadaghalKharid;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
