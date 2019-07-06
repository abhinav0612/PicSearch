package com.example.picsearch;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface API_Interface {
    @GET("api")
    Call<Pixabay> getImages(@QueryMap Map<String,String> parametres);
}
