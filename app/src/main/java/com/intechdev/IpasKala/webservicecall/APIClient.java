package com.intechdev.IpasKala.webservicecall;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HBM on 28/04/2018.
 */

public class APIClient {
    //public static final String BASE_URL = "http://sarvnama.com/wservice/default.aspx?Action=Init&native=1";
    public static final String BASE_URL =  "http://tc.intechdev.com/";
    //public static final String BASE_URL =  "http://tc.intechserver.com/";
    //public static final String BASE_URL =  "http://ipaskala.com/";
    //public static final String BASE_URL =  "http://www.hamibazar.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory2.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
