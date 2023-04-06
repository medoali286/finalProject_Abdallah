package com.example.finalproject_abdallah.Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherViewModel extends ViewModel {

    public MutableLiveData<ArrayList<WeatherItem>> weatherItems = new MutableLiveData<>();

    public MutableLiveData<WeatherItem> selectedWeatherItem = new MutableLiveData< >();





}
