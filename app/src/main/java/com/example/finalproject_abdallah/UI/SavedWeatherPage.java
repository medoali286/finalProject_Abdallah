package com.example.finalproject_abdallah.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject_abdallah.Data.WeatherDatabase;
import com.example.finalproject_abdallah.Data.WeatherItem;
import com.example.finalproject_abdallah.Data.WeatherItemDAO;
import com.example.finalproject_abdallah.Data.WeatherViewModel;
import com.example.finalproject_abdallah.R;
import com.example.finalproject_abdallah.databinding.ActivitySavedWeatherBinding;
import com.example.finalproject_abdallah.databinding.WeatherRowBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SavedWeatherPage extends AppCompatActivity {
    private RecyclerView.Adapter myAdapter;
    private ArrayList<WeatherItem> weatherItems;
    private WeatherItemDAO mDAO;
    private Executor thread;

    private WeatherViewModel weatherModel;
    private ActivitySavedWeatherBinding binding;






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.saved_weather_menu, menu);



        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);



        switch( item.getItemId() ) {
            case R.id.Item_1:

                Intent weatherNowPage = new Intent(this, WeatherNowPage.class);

                startActivity(weatherNowPage);




                break;


            case R.id.Item_2:



                AlertDialog.Builder builder = new AlertDialog.Builder(SavedWeatherPage.this);
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



        binding = ActivitySavedWeatherBinding.inflate(getLayoutInflater());
        setTitle("Saved Weather");
        setSupportActionBar(binding.toolbar);

        weatherModel = new ViewModelProvider(this).get(WeatherViewModel.class);


        weatherItems = weatherModel.weatherItems.getValue();

        if (weatherItems == null) {


            weatherModel.weatherItems.setValue(weatherItems = new ArrayList<WeatherItem>());


            thread = Executors.newSingleThreadExecutor();

            thread.execute(() ->
            {


                WeatherDatabase db = Room.databaseBuilder(getApplicationContext(), WeatherDatabase.class, "database-name").build();
                mDAO = db.cmDAO();


                weatherItems.addAll(mDAO.getAllWeatherItems()); //Once you get the data from database


                runOnUiThread(() -> {

                    binding.recycleView.setAdapter(myAdapter);

                    setContentView(binding.getRoot());


                    if (weatherItems.size() - 1 > 0) {
                        binding.recycleView.smoothScrollToPosition(weatherItems.size() - 1);
                    }

                }); //You can then load the RecyclerView
            });

        }


        weatherModel.selectedWeatherItem.observe(this, (newWeatherItemValue) -> {

            Log.i("tag", "onCreate: " + newWeatherItemValue.getName());


            WeatherDetailsFragment weatherFragment = new WeatherDetailsFragment(newWeatherItemValue);


            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation, weatherFragment).addToBackStack("").commit();


        });


        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                WeatherRowBinding weatherRowBinding = WeatherRowBinding.inflate(getLayoutInflater(), parent, false);
                View root = weatherRowBinding.getRoot();
                return new MyRowHolder(root);


            }


            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {


                holder.timeLocate.setText(weatherItems.get(position).getLocateTime());
                holder.temp.setText(weatherItems.get(position).getTemperature() + " c");
                holder.Image.setImageBitmap(BitmapFactory.decodeFile(weatherItems.get(position).getPathName()));
                holder.city.setText(weatherItems.get(position).getName());


            }

            @Override
            public int getItemCount() {
                return weatherItems.size();
            }

            //function to check what kind of ChatMessage object is at row position
            // If the isSend is true, then return 0
            // so that the onCreateViewHolder checks the viewType and inflates a send_message layout.
            // If isSend is false, then getItemViewType returns 1 and onCreateViewHolder checks
            // if the viewType is 1 and inflates a receive_message layout.


            @Override
            public int getItemViewType(int position) {
                return 0;
            }
        });


        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));


    }


    class MyRowHolder extends RecyclerView.ViewHolder {

        public TextView temp;
        public TextView city;
        public TextView timeLocate;
        public ImageView Image;

        public MyRowHolder(@NonNull View itemView) {

            super(itemView);

            itemView.setOnClickListener(clk -> {

                int position = getAbsoluteAdapterPosition();
                WeatherItem selected = weatherItems.get(position);

                weatherModel.selectedWeatherItem.postValue(selected);
            });


            itemView.setOnLongClickListener(click -> {


                AlertDialog.Builder builder = new AlertDialog.Builder(SavedWeatherPage.this);
                builder.setMessage("Do you want to Delete this message : " + city.getText()).
                        setTitle("Question").
                        setNegativeButton("no", (dialog, cl) -> {
                        })
                        .setPositiveButton("yes", (dialog, cl) -> {
                            int Position = getAbsoluteAdapterPosition();
                            WeatherItem removedItem = weatherItems.get(Position);

                            thread.execute(() ->
                            {
                                mDAO.deleteWeatherItem(removedItem);

                            });

                            runOnUiThread(() -> {


                                weatherItems.remove(Position);
                                myAdapter.notifyItemRemoved(Position);

                            });
                            Snackbar.make(itemView, "You deleted message # " + city.getText(), Snackbar.LENGTH_SHORT)
                                    .setAction("Undo", c -> {

                                        weatherItems.add(Position, removedItem);
                                        myAdapter.notifyItemInserted(Position);

                                    }).show();


                        }).create().show();

                return true;
            });


            Image = itemView.findViewById(R.id.rowImage);
            temp = itemView.findViewById(R.id.rowTemp);
            timeLocate = itemView.findViewById(R.id.locateTime);
            city = itemView.findViewById(R.id.rowCity);


        }
    }


}
