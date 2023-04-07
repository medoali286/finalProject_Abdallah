package com.example.finalproject_abdallah.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.finalproject_abdallah.Data.WeatherItem;
import com.example.finalproject_abdallah.R;
import com.example.finalproject_abdallah.databinding.ActivityWeatherStackPageBinding;
import com.google.android.material.snackbar.Snackbar;

public class WeatherStackPage extends AppCompatActivity {
    ActivityWeatherStackPageBinding binding;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.weather_menu, menu);



        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);



        switch( item.getItemId() ) {



            case R.id.Item_2:

                AlertDialog.Builder builder = new AlertDialog.Builder(WeatherStackPage.this);
                builder.setMessage("•\tClick on “Weather now”:\n" +
                                "   o\tType in the city you would like to \n       retrieve the weather details.\n" +
                                "   o\tHit search.\n" +
                                "   o\tYou may save your research for future \n       retrieval, by hitting on save button.  \n\n" +
                                "•\tClick on “Saved weather”:\n" +
                                "   o\tYou may navigate between the saved \n       destination to display the respective \n       weather.\n" ).
                        setTitle("How to use the WeatherStack?").
                        setNegativeButton("ok", (dialog, cl) -> {
                        }).create().show();
                break;



        }
        return true;


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Weather Stack");

      binding=ActivityWeatherStackPageBinding.inflate(getLayoutInflater());

        setSupportActionBar(binding.toolbar);


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