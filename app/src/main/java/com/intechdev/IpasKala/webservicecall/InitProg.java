package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HBM on 28/04/2018.
 */

public class InitProg {

    @SerializedName("apkExpire")
    public String apkExpire;
    @SerializedName("company")
    public String company;
    @SerializedName("align")
    public String align;
    @SerializedName("dir")
    public String dir;
    @SerializedName("lang")
    public String lang;
    @SerializedName("singleLang")
    public String singleLang;
    @SerializedName("apkVersion")
    public String apkVersion;
    @SerializedName("apkUrl")
    public String apkUrl;

    public InitProg(String apkExpire, String company, String align, String dir, String lang, String singleLang, String apkVersion, String apkUrl) {
        this.apkExpire = apkExpire;
        this.company = company;
        this.align = align;
        this.dir = dir;
        this.lang = lang;
        this.singleLang = singleLang;
        this.apkVersion = apkVersion;
        this.apkUrl = apkUrl;
    }

    public String getApkExpire() {
        return apkExpire;
    }

    public void setApkExpire(String apkExpire) {
        this.apkExpire = apkExpire;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSingleLang() {
        return singleLang;
    }

    public void setSingleLang(String singleLang) {
        this.singleLang = singleLang;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    @Override
    public String toString() {
        return  "getAlign:" + getAlign() + "," +
                "getApkExpire:" + getApkExpire() + "," +
                "getApkUrl:" + getApkUrl() + "," +
                "getApkVersion:" + getApkVersion() + "," +
                "getCompany:" + getCompany() + "," +
                "getDir:" + getDir() + "," +
                "getLang:" + getLang() + "," +
                "getSingleLang:" + getSingleLang() ;
    }
}
