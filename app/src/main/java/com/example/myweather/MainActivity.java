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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    EditText edtmiasto;
    TextView temperatura; //wiatr;

    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "c0a45fe8d3bc6fe117176286714d793c";


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
            tempUrl = url + "?q=" + miasto + "&appid=" + appid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

              //  Log.d("response", response);
                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp");
                    double feelslike = jsonObjectMain.getDouble("feels_like");
                    int pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                   // JSONObject jsonWind = jsonResponse.getJSONObject("wind");
                   // String wind = jsonWind.getString("wind");

                    //windd += wind;
                    output += pressure;
                    edtmiasto.setText(output);
                   // wiatr.setText(windd);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

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