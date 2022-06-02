package com.example.myweather;

public class WeatherRVMmode {
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public WeatherRVMmode(String time, String temp) {
        this.time = time;
        this.temp = temp;
    }

    private String time;
    private String temp;

}
