package com.intechdev.IpasKala.webservicecall;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HBM on 20/07/2018.
 */

public class Result {

    @SerializedName("result")
    public String result;

    public Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
