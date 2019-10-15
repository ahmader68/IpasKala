package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/08/2018.
 */

public class BlogPost {

    String id;
    String name ;
    String rate;
    String icon;
    String url;
    String createDate;
    String publishDate;
    String visitCount;
    String writer;
    String reference;
    String tags;
    String body;

    public BlogPost(String id, String name, String rate, String icon, String url, String createDate, String publishDate, String visitCount, String writer, String reference, String tags, String body) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.icon = icon;
        this.url = url;
        this.createDate = createDate;
        this.publishDate = publishDate;
        this.visitCount = visitCount;
        this.writer = writer;
        this.reference = reference;
        this.tags = tags;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public class BlogPostObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<BlogPost> items;

        public BlogPostObject(Settings settings, List<BlogPost> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<BlogPost> getItems() {
            return items;
        }

        public void setItems(List<BlogPost> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class BlogPostResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<BlogPost.BlogPostObject> results;
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

        public List<BlogPost.BlogPostObject> getResults() {
            return results;
        }

        public void setResults(List<BlogPost.BlogPostObject> results) {
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
