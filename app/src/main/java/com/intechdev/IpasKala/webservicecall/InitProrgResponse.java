package com.intechdev.IpasKala.webservicecall;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 28/04/2018.
 */


public class InitProrgResponse {
    @SerializedName("page")
    public  int page;
    @SerializedName("results")
    public  List<InitProg> results;
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

    public List<InitProg> getResults() {
        return results;
    }

    public void setResults(List<InitProg> results) {
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
