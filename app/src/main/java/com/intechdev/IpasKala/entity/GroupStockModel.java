package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 12/08/2017.
 */

public class GroupStockModel {
    private double id;
    private String title;
    private String imageUrl;

    private long ID;
    private long PARENT_ID;
    private String TITLE;
    private String SDATE;
    private String EDATE;
    private int STATUS;
    private int FACTORY_ID;
    private String IMAGE_URL;
    private String DESCRIPTION;

    private int VALUE;
    private String DOMAIN_NAME;

    public GroupStockModel(long ID, long PARENT_ID, String TITLE, String SDATE, String EDATE, int STATUS, int FACTORY_ID, String IMAGE_URL, String DESCRIPTION) {
        this.ID = ID;
        this.PARENT_ID = PARENT_ID;
        this.TITLE = TITLE;
        this.SDATE = SDATE;
        this.EDATE = EDATE;
        this.STATUS = STATUS;
        this.FACTORY_ID = FACTORY_ID;
        this.IMAGE_URL = IMAGE_URL;
        this.DESCRIPTION = DESCRIPTION;
    }

    public GroupStockModel(long ID, long PARENT_ID, String TITLE, int VALUE, String DOMAIN_NAME, String IMAGE_URL, int STATUS_) {
        this.ID = ID;
        this.PARENT_ID = PARENT_ID;
        this.TITLE = TITLE;
        this.VALUE = VALUE;
        this.DOMAIN_NAME = DOMAIN_NAME;
        this.STATUS = STATUS_;
        this.IMAGE_URL = IMAGE_URL;

    }

    public GroupStockModel(double id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    ////////////////
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(long PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getSDATE() {
        return SDATE;
    }

    public void setSDATE(String SDATE) {
        this.SDATE = SDATE;
    }

    public String getEDATE() {
        return EDATE;
    }

    public void setEDATE(String EDATE) {
        this.EDATE = EDATE;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getFACTORY_ID() {
        return FACTORY_ID;
    }

    public void setFACTORY_ID(int FACTORY_ID) {
        this.FACTORY_ID = FACTORY_ID;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getVALUE() {
        return VALUE;
    }

    public void setVALUE(int VALUE) {
        this.VALUE = VALUE;
    }

    public String getDOMAIN_NAME() {
        return DOMAIN_NAME;
    }

    public void setDOMAIN_NAME(String DOMAIN_NAME) {
        this.DOMAIN_NAME = DOMAIN_NAME;
    }
}
