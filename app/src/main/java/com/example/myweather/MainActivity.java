package com.example.myweather;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import  androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    EditText edtmiasto;
    TextView temperatura;

    private final String url = "http://api.weatherapi.com/v1/forecast.json?key=7c42071cd2ae4e228e3114233220206&q=";
    private final String appid = "7c42071cd2ae4e228e3114233220206";
    DecimalFormat df = new DecimalFormat("#.##");


    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtmiasto = findViewById(R.id.edtmiasto);
        temperatura = findViewById(R.id.temperatura);



    }

    public void getWeather(View view) {
        String tempUrl = "";
        String miasto = edtmiasto.getText().toString().trim();
        if (miasto.equals(""))
            temperatura.setText("Wprowadz miasto");
        else{
            tempUrl = url + miasto + "&days=1&aqi=no&alerts=no" ;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString().trim() ,Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        }
    }
}