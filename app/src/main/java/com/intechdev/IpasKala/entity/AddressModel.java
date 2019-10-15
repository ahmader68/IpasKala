package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 21/07/2018.
 */

public class AddressModel {

    public static final int VIEW_SQUARE = 0;
    public static final int VIEW_REQTANGLE = 1;


    public String id;
    public String title;
    public String provinceTitle;
    public String city;
    public String tel;
    public String postalCode;
    public String address;
    private int type;
    public int position;

    public AddressModel(String id, String title, String provinceTitle, String city, String tel, String postalCode, String address, int type) {
        this.id = id;
        this.title = title;
        this.provinceTitle = provinceTitle;
        this.city = city;
        this.tel = tel;
        this.postalCode = postalCode;
        this.address = address;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvinceTitle() {
        return provinceTitle;
    }

    public void setProvinceTitle(String provinceTitle) {
        this.provinceTitle = provinceTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
