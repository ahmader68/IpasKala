package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 22/04/2018.
 */

public class UserCommentsModel {

    public static final int HEARDER_GROUP = 0;
    public static final int DETILE_ITEM = 1;
    public static final int PROFILE_ITEM = 2;

    private String mName;
    private String mDate;
    private String mLike;
    private String mDontLike;
    private String mTitle;
    private String mDescription;
    private int mType;

    public UserCommentsModel(String mName, String mDate, String mLike, String mDontLike, String mTitle, String mDescription, int mType) {
        this.mName = mName;
        this.mDate = mDate;
        this.mLike = mLike;
        this.mDontLike = mDontLike;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mType = mType;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmLike() {
        return mLike;
    }

    public void setmLike(String mLike) {
        this.mLike = mLike;
    }

    public String getmDontLike() {
        return mDontLike;
    }

    public void setmDontLike(String mDontLike) {
        this.mDontLike = mDontLike;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }


}
