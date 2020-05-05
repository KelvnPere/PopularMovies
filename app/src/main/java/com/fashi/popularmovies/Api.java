package com.fashi.popularmovies;

import androidx.annotation.RestrictTo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    // base URL
    public static String BASE_URL = "https://api.themoviedb.org/";

    // retrofit
    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

           return retrofit;
    }

    public static ApiInterface apiInterface(){
        return getClient().create(ApiInterface.class);
    }
}
