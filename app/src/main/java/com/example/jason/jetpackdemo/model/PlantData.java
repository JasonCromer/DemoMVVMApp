package com.example.jason.jetpackdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantData {

    @SerializedName("data")
    public List<Plant> plantList;
}
