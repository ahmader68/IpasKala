package com.intechdev.IpasKala.entity;

/**
 * Created by HBM on 06/05/2018.
 */

public class BlogListModel {
    public static final int VIEW_SQUARE = 0;
    public static final int VIEW_REQTANGLE = 1;

    private Long id;
    private String blogTitle;
    private String blogDescription;
    private String blogUrlImageSmall;
    private int type;

    public BlogListModel(Long id, String blogTitle, String blogDescription, String blogUrlImageSmall, int type) {
        this.id = id;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.blogUrlImageSmall = blogUrlImageSmall;
        this.type = type;
    }

    public static int getViewSquare() {
        return VIEW_SQUARE;
    }

    public static int getViewReqtangle() {
        return VIEW_REQTANGLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public String getBlogUrlImageSmall() {
        return blogUrlImageSmall;
    }

    public void setBlogUrlImageSmall(String blogUrlImageSmall) {
        this.blogUrlImageSmall = blogUrlImageSmall;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
