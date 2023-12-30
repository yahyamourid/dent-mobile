package com.moujib.dents_mobile.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitStudent {
    private static final String BASE_URL = "http://192.168.1.106:5050/api/users/";
    private static final String BASE_URL2 = "http://192.168.1.106:5050/api/studentpws/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        };
        return  retrofit;
    }

    public static Retrofit getClient2(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL2).addConverterFactory(GsonConverterFactory.create()).build();
        };
        return  retrofit;
    }
}
