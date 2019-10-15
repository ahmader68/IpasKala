package com.intechdev.IpasKala.utils;

/**
 * Created by HBM on 26/08/2017.
 */

public class DataReciveException extends Exception {

    private int exceptionCode;
    private String messeage;

    public DataReciveException(int exceptionCode, String messeage) {
        this.exceptionCode = exceptionCode;
        this.messeage = messeage;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

    public String getMesseage() {
        return messeage;
    }

    @Override
    public String toString() {
        return getMesseage();
    }
}
