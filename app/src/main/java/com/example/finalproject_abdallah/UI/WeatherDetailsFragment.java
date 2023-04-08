package com.example.finalproject_abdallah.UI;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.finalproject_abdallah.Data.WeatherItem;
import com.example.finalproject_abdallah.databinding.WeatherDetailsBinding;

public class WeatherDetailsFragment extends Fragment {


    WeatherItem selected;

    public WeatherDetailsFragment(WeatherItem w) {

        selected = w;


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);


        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        WeatherDetailsBinding binding = WeatherDetailsBinding.inflate(inflater);


        binding.getRoot().setBackgroundColor(Color.WHITE);
        binding.weatherDetailsId.setText("Id = " + selected.id);
        binding.weatherDetailsName.setText("City Name : " + selected.getName());
        binding.weatherDetailsLocateTime.setText("LocateTime : " + selected.getLocateTime());
        binding.weatherDetailsTemperature.setText("Temperature : " + selected.getTemperature()+"°C");
        binding.weatherDetailsIcon.setImageBitmap(BitmapFactory.decodeFile(selected.getPathName()));
        binding.weatherDetailsDescription.setText("Description : " + selected.getDescription());
        binding.weatherDetailsHumidity.setText("humidity : " + selected.getHumidity());


        return binding.getRoot();


    }
}



