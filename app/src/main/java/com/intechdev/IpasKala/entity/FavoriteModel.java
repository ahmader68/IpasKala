package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 01/08/2018.
 */

public class FavoriteModel {

    public static final int VIEW_SQUARE = 0;
    public static final int VIEW_REQTANGLE = 1;

    private Long FavId;
    private String ProductId;
    private String Price;
    private String ProductName;
    private int type;
    public int position;

    public FavoriteModel(Long favId, String productId, String price, String productName, int type) {
        FavId = favId;
        ProductId = productId;
        Price = price;
        ProductName = productName;
        this.type = type;
    }

    public Long getFavId() {
        return FavId;
    }

    public void setFavId(Long favId) {
        FavId = favId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
