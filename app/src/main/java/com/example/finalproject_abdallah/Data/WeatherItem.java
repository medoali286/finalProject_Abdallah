package com.example.finalproject_abdallah.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class WeatherItem {



       @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate=true)
        public int id;

        @ColumnInfo(name="name")
        protected String name;


        @ColumnInfo(name="locateTime")
        protected String locateTime;


    @ColumnInfo(name="temperature")
    protected String temperature;


    @ColumnInfo(name="icon")
    protected String icon;



    @ColumnInfo(name="description")
    protected String description;


    public String getPathName() {
        return pathName;
    }

    @ColumnInfo(name="humidity")
    protected String humidity;



    @ColumnInfo(name="pathName")
    protected String pathName;


    public WeatherItem(String name, String locateTime, String temperature, String icon, String description, String humidity, String pathName) {
        this.name = name;
        this.locateTime = locateTime;
        this.temperature = temperature;
        this.icon = icon;
        this.description = description;
        this.humidity = humidity;
        this.pathName = pathName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocateTime() {
        return locateTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return humidity;
    }
}
