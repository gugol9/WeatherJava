package com.example.myweather;

import android.widget.RelativeLayout;
import android.widget.TextView;
import  androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //private RelativeLayout Homerl;
    private TextView miasto, temperatura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miasto = findViewById(R.id.textMiasto);
        temperatura = findViewById(R.id.textTemperatura);

    }

}