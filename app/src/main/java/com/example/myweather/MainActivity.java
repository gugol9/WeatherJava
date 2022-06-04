package com.example.myweather;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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

    private EditText edtmiasto;
    private TextView windID;
    private TextView pressureID;
    private TextView miastoID;
    private TextView temperaturaID;
    private TextView humidityID;
    private TextView krajID;
    private TextView skyID;

    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "c0a45fe8d3bc6fe117176286714d793c";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtmiasto = findViewById(R.id.edtmiasto);
        windID = findViewById(R.id.windID);
        pressureID = findViewById(R.id.pressureID);
        miastoID = findViewById(R.id.miastoID);
        temperaturaID = findViewById(R.id.temperaturaID);
        humidityID = findViewById(R.id.humidityID);
        krajID = findViewById(R.id.krajID);
        skyID = findViewById(R.id.skyID);
    }

    public void getWeather(View view) {
        String tempUrl = "";
        String miasto = edtmiasto.getText().toString().trim();
        if (miasto.equals(""))
            edtmiasto.setText("Wprowadz miasto");
        else {
            tempUrl = url + "?q=" + miasto + "&appid=" + appid;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener < String > () {
                @Override
                public void onResponse(String response) {
                    String pressure_ = "";
                    String temp_ = "";
                    String humidity_ = "";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");

                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");

                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");

                        JSONObject jsonWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonWind.getString("speed");

                        String city = jsonResponse.getString("name");

                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String country = jsonObjectSys.getString("country");

                        humidity_ += humidity;
                        pressure_ += pressure;
                        temp_ += (int) temp;

                        skyID.setText(description);
                        krajID.setText(country);
                        humidityID.setText(humidity_ + " %");
                        temperaturaID.setText(temp_ +" °C");
                        miastoID.setText(city);
                        pressureID.setText(pressure_ + " hPa");
                        windID.setText(wind + " m/s");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}