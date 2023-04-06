package com.example.finalproject_abdallah.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject_abdallah.databinding.ActivityWeatherStackPageBinding;

public class WeatherStackPage extends AppCompatActivity {
    ActivityWeatherStackPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Weather Stack");

      binding=ActivityWeatherStackPageBinding.inflate(getLayoutInflater());




        setContentView(binding.getRoot());



        navigation();


    }









    void navigation() {

        binding.weatherNow.setOnClickListener(clk -> {

            Intent weatherNowPage = new Intent(this, WeatherNowPage.class);

            startActivity(weatherNowPage);

        });


        binding.savedWeather.setOnClickListener(clk -> {

            Intent savedWeatherPage = new Intent(this, SavedWeatherPage.class);

            startActivity(savedWeatherPage);


        });


    }
}