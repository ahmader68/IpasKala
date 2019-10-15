package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 20/04/2018.
 */

public class StockPeropertiesModel {
    public static final int HEARDER_GROUP = 0;
    public static final int DETILE_ITEM = 1;
    private String mName;
    private String mDescription;
    private int mType;

    public StockPeropertiesModel(String name, String description, int type) {
        this.mName = name;
        this.mDescription = description;
        this.mType = type;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        this.mName = name;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        this.mDescription = description;
    }
    public int getType() {
        return mType;
    }
    public void setType(int type) {
        this.mType = type;
    }
}
