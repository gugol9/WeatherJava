package com.example.myweather;

import android.annotation.SuppressLint;
import android.widget.TextView;
import  androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //private RelativeLayout Homerl;

    //private Text wiatr;
    //private Text miasto;

    private final String url = "http://api.weatherapi.com/v1/forecast.json?key=f5294d8b4e6545018b1184018220106&q=London&days=1&aqi=yes&alerts=yes";
    private final String appid = "f5294d8b4e6545018b1184018220106";
    //DecimalFormat df = new DecimalFormat("#.##");

    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //wiatr = (Text)findViewById(R.id.wiatr);
   // miasto = (Text)findViewById(R.id.miasto);


    }

}