package com.example.jason.jetpackdemo.network;

import android.annotation.SuppressLint;

import com.example.jason.jetpackdemo.model.PlantData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlantListService {

    String SEARCH_URI = "search";
    String LIMIT_PARAM = "limit";
    String FIELDS_PARAM = "fields";

    @SuppressLint("SpecifyRetrofitResponseWrapper")
    @GET(SEARCH_URI)
    Single<PlantData> fetchPlants(@Query(LIMIT_PARAM) int limit, @Query(FIELDS_PARAM) String fields);
}
