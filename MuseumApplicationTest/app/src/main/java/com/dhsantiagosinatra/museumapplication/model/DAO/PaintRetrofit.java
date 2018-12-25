package com.dhsantiagosinatra.museumapplication.model.DAO;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaintRetrofit {

    protected Retrofit retrofit;

    public PaintRetrofit(String baseUrl) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(okHttpClient.build()).build();
    }
}
