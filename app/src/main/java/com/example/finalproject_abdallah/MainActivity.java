package com.example.finalproject_abdallah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject_abdallah.UI.NasaPage;
import com.example.finalproject_abdallah.UI.NewYorkPage;
import com.example.finalproject_abdallah.UI.PlaceKittenPage;
import com.example.finalproject_abdallah.UI.WeatherNowPage;
import com.example.finalproject_abdallah.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        navigation();

    }




    void navigation(){



        binding.nasa.setOnClickListener(clk->{

            Intent NasaPage=new Intent(this, NasaPage.class);

            startActivity(NasaPage);


        });


        binding.newYork.setOnClickListener(clk->{
            Intent NewYorkPage=new Intent(this, NewYorkPage.class);

            startActivity(NewYorkPage);


        });


        binding.kitten.setOnClickListener(clk->{
    Intent PlaceKittenPage=new Intent(this, PlaceKittenPage.class);

    startActivity(PlaceKittenPage);
});


        binding.weather.setOnClickListener(clk->{

    Intent weatherNowPage=new Intent(this, WeatherNowPage.class);

    startActivity(weatherNowPage);

});







    }




}