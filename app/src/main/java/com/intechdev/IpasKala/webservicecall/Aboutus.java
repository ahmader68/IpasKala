package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 08/08/2018.
 */

public class Aboutus {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("order")
    public String order;
    @SerializedName("icon")
    public String icon;
    @SerializedName("fileName")
    public String fileName ;
    @SerializedName("fileExt")
    public String fileExt;
    @SerializedName("attachLink")
    public String attachLink;
    @SerializedName("attahIcon")
    public String attahIcon;
    @SerializedName("attachSize")
    public String attachSize;
    @SerializedName("fileaddress")
    public String fileaddress;
    @SerializedName("createDate")
    public String createDate;
    @SerializedName("lastVisitDate")
    public String lastVisitDate;
    @SerializedName("catId")
    public String catId;
    @SerializedName("visitCount")
    public String visitCount;
    @SerializedName("tags")
    public String tags;
    @SerializedName("brief")
    public String brief;
    @SerializedName("body")
    public String body;

    public Aboutus(String id, String name, String order, String icon, String fileName, String fileExt, String attachLink, String attahIcon, String attachSize, String fileaddress, String createDate, String lastVisitDate, String catId, String visitCount, String tags, String brief, String body) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.icon = icon;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.attachLink = attachLink;
        this.attahIcon = attahIcon;
        this.attachSize = attachSize;
        this.fileaddress = fileaddress;
        this.createDate = createDate;
        this.lastVisitDate = lastVisitDate;
        this.catId = catId;
        this.visitCount = visitCount;
        this.tags = tags;
        this.brief = brief;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getAttachLink() {
        return attachLink;
    }

    public void setAttachLink(String attachLink) {
        this.attachLink = attachLink;
    }

    public String getAttahIcon() {
        return attahIcon;
    }

    public void setAttahIcon(String attahIcon) {
        this.attahIcon = attahIcon;
    }

    public String getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize;
    }

    public String getFileaddress() {
        return fileaddress;
    }

    public void setFileaddress(String fileaddress) {
        this.fileaddress = fileaddress;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class AboutusObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<Aboutus> items;

        public AboutusObject(Settings settings, List<Aboutus> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<Aboutus> getItems() {
            return items;
        }

        public void setItems(List<Aboutus> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class AboutusResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<Aboutus> results;
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

        public List<Aboutus> getResults() {
            return results;
        }

        public void setResults(List<Aboutus> results) {
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
